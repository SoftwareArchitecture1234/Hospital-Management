package com.hms.auth.service.authService;

import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.User;

public interface AuthService {
    String login(LoginDto loginDto);
    User registerUser(UserDto signupRequest);
}
