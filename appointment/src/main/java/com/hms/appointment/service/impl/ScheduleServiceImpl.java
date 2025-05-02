package com.hms.appointment.service.impl;

import com.hms.appointment.constant.ScheduleStatus;
import com.hms.appointment.constant.ShiftType;
import com.hms.appointment.constant.TypeOfWork;
import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;
import com.hms.appointment.dtos.schedule.RequestScheduleDto;
import com.hms.appointment.entity.schedule.ScheduleEntity;
import com.hms.appointment.entity.workload.WorkloadEntity;
import com.hms.appointment.mapper.EntityMapper;
import com.hms.appointment.repository.ScheduleRepository;
import com.hms.appointment.repository.WorkloadRepository;
import com.hms.appointment.service.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ScheduleServiceImpl là lớp triển khai của IScheduleService.
 * Nó cung cấp các phương thức để xử lý các yêu cầu liên quan đến lịch hẹn.
 * @ scheduleRepository là một đối tượng truy cập dữ liệu cho lịch hẹn.
 * @ workloadRepository là một đối tượng truy cập dữ liệu cho ca làm việc.
 */
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements IScheduleService {
    private final WorkloadRepository workloadRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * Lấy danh sách các ca làm việc còn trống dựa trên thông tin truy vấn.
     *
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return danh sách các ca làm việc còn trống
     */
    @Override
    public List<AvailableTimeSlotDto> getAvailableSlots(QueryScheduleDto queryScheduleDto) {
        String dateExam = queryScheduleDto.getDateExam();
        String shift = queryScheduleDto.getShift();
        String typeExam = queryScheduleDto.getTypeExam();
        String startTime = queryScheduleDto.getStartTime();
        String endTime = queryScheduleDto.getEndTime();

        List<WorkloadEntity> listWorkload = workloadRepository.findAvailableWorkload(
                handleDateFromDateExam(dateExam),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                ShiftType.valueOf(shift).getShiftName(),
                TypeOfWork.valueOf(typeExam).getTypeOfWork()
        );

        if (listWorkload.isEmpty()) {
            return null;
        }
        List<AvailableTimeSlotDto> availTimeSlot = getAvailableTimeSlotDto(
                listWorkload,
                handleDateFromDateExam(dateExam),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
        );
        return availTimeSlot;
    }

    /**
     * Chuyển đổi chuỗi ngày tháng sang LocalDate.
     *
     * @param dateExam chuỗi ngày tháng
     * @return LocalDate
     */
    private LocalDate handleDateFromDateExam(String dateExam) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateExam, dateTimeFormatter);
    }
    /**
     * Lấy danh sách các ca làm việc còn trống.
     *
     * @param listWorkload danh sách các ca làm việc
     * @param dateExam ngày khám
     * @param startTime thời gian bắt đầu
     * @param endTime thời gian kết thúc
     * @return danh sách các ca làm việc còn trống
     */
    private List<AvailableTimeSlotDto> getAvailableTimeSlotDto(
            List<WorkloadEntity> listWorkload,
            LocalDate dateExam,
            LocalTime startTime,
            LocalTime endTime
    ) {
        return listWorkload.stream()
                .filter(workloadEntity -> {
                    int doctorId = workloadEntity.getDoctorId();
                    ScheduleEntity scheduleEntity = scheduleRepository.findScheduleByDoctorIdAndDate(
                            dateExam, startTime, endTime, doctorId
                    );
                    return scheduleEntity == null;
                })
                .map(EntityMapper::workloadEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean requestSchedule(RequestScheduleDto requestScheduleDto) {
        WorkloadEntity listWorkload = workloadRepository.findAvailableWorkloadByDoctorId(
                requestScheduleDto.getDoctorId(),
                requestScheduleDto.getDateExam(),
                requestScheduleDto.getStartTime(),
                requestScheduleDto.getEndTime(),
                TypeOfWork.valueOf(requestScheduleDto.getTypeOfWork()).getTypeOfWork()
        ).orElseThrow(() -> new RuntimeException("Ca làm việc không tồn tại"));

        ScheduleEntity scheduleEntity = scheduleRepository.findScheduleByDoctorIdAndDate(
                requestScheduleDto.getDateExam(),
                requestScheduleDto.getStartTime(),
                requestScheduleDto.getEndTime(),
                requestScheduleDto.getDoctorId()
        );
        if (scheduleEntity != null) {
            throw new RuntimeException("Lịch hẹn đã tồn tại");
        } else {
            ScheduleEntity newSchedule = new ScheduleEntity();
            newSchedule.setDoctorId(requestScheduleDto.getDoctorId());
            newSchedule.setPatientId(requestScheduleDto.getPatientId());
            newSchedule.setStartTime(requestScheduleDto.getStartTime());
            newSchedule.setEndTime(requestScheduleDto.getEndTime());
            newSchedule.setDate(requestScheduleDto.getDateExam());
            newSchedule.setStatus(ScheduleStatus.PENDING);
            scheduleRepository.save(newSchedule);
            return true;
        }
    }
}
