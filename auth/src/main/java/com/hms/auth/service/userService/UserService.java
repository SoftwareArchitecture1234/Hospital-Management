package com.hms.auth.service.userService;

import java.util.List;

import com.hms.auth.dto.UserDto;
import com.hms.auth.dto.UserProfileResponse;
import com.hms.auth.entity.User;

public interface UserService {
    UserProfileResponse getProfileWithDetails();
    List<User> getAllUsers();
    User updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
