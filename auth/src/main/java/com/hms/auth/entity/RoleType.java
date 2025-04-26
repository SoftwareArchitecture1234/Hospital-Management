package com.hms.auth.entity;

public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DOCTOR("ROLE_DOCTOR");

    private final String code;

    RoleType(String code){
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
