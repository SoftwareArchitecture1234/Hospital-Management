package com.hms.patient.dtos.accounts;


import com.hms.patient.constant.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "PatientInfoDto",
        description = "Đối tượng này đại diện cho thông tin bệnh nhân trong hệ thống."
)
public class PatientInfoDto {
    @Schema(
            name = "name",
            description = "Tên bệnh nhân",
            example = "Nguyen Van A"
    )
    private String name;
    @Schema(
            name = "email",
            description = "Email của bệnh nhân",
            example = "example@gmail.com"
    )
    private String email;
    @Schema(
            name = "phone",
            description = "Số điện thoại của bệnh nhân",
            example = "0123456789"
    )
    private String phone;
    @Schema(
            name = "location",
            description = "Địa chỉ của bệnh nhân",
            example = "123 Đường ABC, Quận 1, TP.HCM"
    )
    private String location;
    @Schema(
            name = "age",
            description = "Tuổi của bệnh nhân",
            example = "25"
    )
    private int age;
    @Schema(
            name = "weight",
            description = "Cân nặng của bệnh nhân",
            example = "70.5"
    )
    private float weight;
    @Schema(
            name = "height",
            description = "Chiều cao của bệnh nhân",
            example = "175.0"
    )
    private float height;
    @Schema(
            name = "gender",
            description = "Giới tính của bệnh nhân",
            example = "MALE"
    )
    private String gender;

    public PatientInfoDto(String name,
                          String email,
                          String phone,
                          String location,
                          int age,
                          float weight,
                          float height,
                          Gender gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender.toString();
    }
}

