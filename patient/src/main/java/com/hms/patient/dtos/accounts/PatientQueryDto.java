package com.hms.patient.dtos.accounts;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PatientQueryDto {
    private String email;
    private String phone;

    public PatientQueryDto(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
