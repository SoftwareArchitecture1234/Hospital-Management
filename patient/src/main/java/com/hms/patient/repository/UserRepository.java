package com.hms.patient.repository;

import com.hms.patient.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u WHERE u.name = ?1 AND u.phone = ?2")
    UserEntity findByNameAndPhone(String name, String phone);
}
