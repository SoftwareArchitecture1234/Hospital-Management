package com.hms.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "PatientDto",
        description = "Đối tượng này đại diện cho thông tin bệnh nhân trong hệ thống."
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PatientDto {
    @Schema(
        name = "userId",
        description = "ID của người dùng",
        example = "1"
)
private int userId;

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
        example = "Male"
)
private String gender;
}
