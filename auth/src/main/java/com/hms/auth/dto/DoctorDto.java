package com.hms.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DoctorDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String specialized;
    private Long salary;
}
