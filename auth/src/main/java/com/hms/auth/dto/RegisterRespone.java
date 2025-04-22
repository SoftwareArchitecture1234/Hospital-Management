package com.hms.auth.dto;

import com.hms.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterRespone {
    String message;
    User user;
}
