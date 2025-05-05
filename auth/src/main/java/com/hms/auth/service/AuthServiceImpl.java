package com.hms.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.auth.dto.DoctorDto;
import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.PatientDto;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.Doctor;
import com.hms.auth.entity.Patient;
import com.hms.auth.entity.Role;
import com.hms.auth.entity.RoleType;
import com.hms.auth.entity.User;
import com.hms.auth.exception.EmailAreadyExisted;
import com.hms.auth.exception.InvalidException;
import com.hms.auth.exception.UsernameAlreadyExisted;
import com.hms.auth.repository.DoctorRepository;
import com.hms.auth.repository.PatientRepository;
import com.hms.auth.repository.RoleRepository;
import com.hms.auth.repository.UserRepository;
import com.hms.auth.security.JwtTokenProvider;
import com.hms.auth.service.authService.AuthService;
import com.hms.auth.service.patientService.PatientServiceClient;
import com.hms.auth.service.staffService.StaffServiceClient;
import com.netflix.discovery.converters.Auto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StaffServiceClient staffServiceClient;

    @Autowired
    private final PatientServiceClient patientServiceClient;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UsernameAlreadyExisted("Username already exists");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAreadyExisted("Email already exists");
        }
        
        User user = User.builder()
            .name(signupRequest.getName())
            .username(signupRequest.getUsername())
            .email(signupRequest.getEmail())
            .password(passwordEncoder.encode(signupRequest.getPassword()))
            .phone(signupRequest.getPhone())
            .location(signupRequest.getLocation())
            .build();

        List<Role> roles = signupRequest.getRoles().stream()
                .map(roleName -> Role.builder()
                        .roleName(RoleType.valueOf(roleName))
                        .user(user)
                        .build())
                .toList();

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        if(roles.stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_DOCTOR)){
            DoctorDto doctorDto = DoctorDto.builder()
                .userId(savedUser.getId())
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .phone(signupRequest.getPhone())
                .location(signupRequest.getLocation())
                .build();

            staffServiceClient.createDoctor(doctorDto);
        } else if (roles.stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_PATIENT)) {
            PatientDto patientDto = PatientDto.builder()
                    .userId(savedUser.getId())
                    .age(signupRequest.getAge())
                    .weight(signupRequest.getWeight())
                    .height(signupRequest.getHeight())
                    .gender(signupRequest.getGender())
                    .build();
            patientServiceClient.createPatient(patientDto);
        }

        return savedUser;
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

}