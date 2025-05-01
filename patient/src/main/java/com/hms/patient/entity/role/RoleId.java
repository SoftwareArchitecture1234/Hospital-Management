//package com.hms.patient.entity.role;
//
//
//import com.hms.patient.constant.RoleType;
//import jakarta.persistence.Column;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@NoArgsConstructor
//@AllArgsConstructor
//public class RoleId implements Serializable {
//    @Column(name = "user_id")
//    private int userId;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "role_name")
//    private RoleType roleName;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RoleId roleId)) return false;
//        return userId == roleId.userId && roleName.equals(roleId.roleName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, roleName);
//    }
//}
