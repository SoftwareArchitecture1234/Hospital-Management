package com.hms.auth.service.staffService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hms.auth.dto.DoctorDto;

@FeignClient(name = "staff-service", url = "${staff-service.url:http://localhost:8050}")
public interface StaffServiceClient {

    @PostMapping("/api/v1/doctors")
    void createDoctor(@RequestBody DoctorDto doctorDto);
}