package com.hms.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hms.auth.entity.Doctor;
import com.hms.auth.entity.Patient;
import com.hms.auth.entity.User;
// import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

// import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private User user;

    private String message;

    private Doctor doctor;

    private Patient patient;

//    private Long role;

}