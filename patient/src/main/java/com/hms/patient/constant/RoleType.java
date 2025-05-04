package com.hms.patient.constant;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DOCTOR("ROLE_DOCTOR");

    private final String code;

    RoleType(String code){
        this.code = code;
    }
}
