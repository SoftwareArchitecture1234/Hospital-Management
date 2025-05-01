package com.hms.auth.dto;

import com.hms.auth.entity.User;
// import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

// import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private User user;

    private String message;

//    private Long role;

}