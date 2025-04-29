package com.hms.patient.dtos.accounts;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String location;
}
