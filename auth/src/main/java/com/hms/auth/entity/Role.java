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
@Table(name = "roles")
public class Role {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}