package com.hms.patient.dtos.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDto extends AccountDto{
    private int age;
    private float weight;
    private float height;
    private String gender;
}
