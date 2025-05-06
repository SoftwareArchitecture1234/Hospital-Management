package com.hms.auth.entity;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {
    private Integer user;
    private RoleType roleName;

    public RoleId() {}

    public RoleId(Integer user, RoleType roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId roleId = (RoleId) o;
        return Objects.equals(user, roleId.user) && roleName == roleId.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, roleName);
    }
}
