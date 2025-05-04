package com.hms.auth.service.authService;

import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.Role;
import com.hms.auth.entity.RoleType;
import com.hms.auth.entity.User;
import com.hms.auth.exception.EmailAreadyExisted;
import com.hms.auth.exception.InvalidException;
import com.hms.auth.exception.UserNotFound;
import com.hms.auth.exception.UsernameAlreadyExisted;
import com.hms.auth.repository.RoleRepository;
import com.hms.auth.repository.UserRepository;
import com.hms.auth.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto signupRequest){
        // Check if username is already taken
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UsernameAlreadyExisted("Username have already existed");
        }

        // Check if email is already in use
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAreadyExisted("Emai have already existed");
        }
        Set<Role> roles  = new HashSet<>();
        roles.add(getRole(RoleType.ROLE_USER));

        if(signupRequest.getRoles() != null){
          signupRequest.getRoles().forEach(role -> {
              Role newRole = getRole(RoleType.valueOf(role));
              roles.add(newRole);
          });
        }


        User user = User.builder()
                .name(signupRequest.getName())
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles(roles)
                .build();

        return userRepository.save(user);
    }

    @Override
    public String login(LoginDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsernameOrEmail(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenProvider.generateToken(authentication);

        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            throw new InvalidException("Invalid username or password");
        }
    }

    @Override
    public User getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsernameOrEmail(username, username);
        if (user.isEmpty()) {
            throw new UserNotFound("User not found");
        }

        return User.builder()
                .id(user.get().getId())
                .name(user.get().getName())
                .username(user.get().getUsername())
                .email(user.get().getEmail())
                .roles(user.get().getRoles())
                .build();
    }

    private Role getRole(RoleType roleType) {
        return roleRepository.findByName(roleType.getCode())
                .orElseThrow(() -> new RuntimeException("Role " + roleType + " not found"));
    }
}