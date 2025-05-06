package com.hms.auth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@IdClass(RoleId.class)
@Table(name = "role")  
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleName;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
