package com.hms.staffmanagement.entity;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DOCTOR("ROLE_DOCTOR"),
    ROLE_PATIENT("ROLE_PATIENT");

    private final String code;

    RoleType(String code) {
        this.code = code;
    }

}