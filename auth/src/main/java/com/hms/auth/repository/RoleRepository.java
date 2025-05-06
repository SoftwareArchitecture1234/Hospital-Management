package com.hms.auth.repository;

import com.hms.auth.entity.Role;
import com.hms.auth.entity.RoleId;
import com.hms.auth.entity.RoleType;
import com.hms.auth.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, RoleId> {
        Optional<Role> findByUserAndRoleName(User user, RoleType roleName);
}
