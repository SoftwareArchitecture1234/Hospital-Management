//package com.hms.patient.entity.role;
//
//import com.hms.patient.constant.RoleType;
//import com.hms.patient.entity.user.UserEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Table(name = "Role")
//@Entity
//@IdClass(RoleId.class)
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class RoleEntity {
//    @Id
//    @Column(name = "user_id")
//    private int userId;
//
//    @Id
//    @Column(name = "role_name")
//    @Enumerated(EnumType.STRING)
//    private RoleType roleName;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
//    private UserEntity userEntity;
//}
