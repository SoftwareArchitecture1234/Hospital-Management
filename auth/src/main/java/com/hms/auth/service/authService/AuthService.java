package com.hms.auth.service.authService;

import java.util.List;

import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.User;

public interface AuthService {
    String login(LoginDto loginDto);
    User registerUser(UserDto signupRequest);
    User getProfile();
    List<User> getAllUsers();
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
