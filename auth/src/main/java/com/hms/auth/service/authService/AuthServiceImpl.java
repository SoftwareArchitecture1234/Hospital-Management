package com.hms.auth.service.authService;

import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.Doctor;
import com.hms.auth.entity.Patient;
import com.hms.auth.entity.Role;
import com.hms.auth.entity.RoleType;
import com.hms.auth.entity.User;
import com.hms.auth.exception.EmailAreadyExisted;
import com.hms.auth.exception.InvalidException;
import com.hms.auth.exception.UserNotFound;
import com.hms.auth.exception.UsernameAlreadyExisted;
import com.hms.auth.repository.DoctorRepository;
import com.hms.auth.repository.PatientRepository;
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

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;

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
            Doctor doctor = Doctor.builder()
            .user(savedUser)
            .build();
            doctorRepository.save(doctor);
        } else if (roles.stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_PATIENT)) {
            Patient patient = Patient.builder()
                    .user(savedUser)
                    .build();
            patientRepository.save(patient);
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
                .password(user.get().getPassword())
                .roles(user.get().getRoles())
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with ID: " + id));

        existingUser.setName(userDto.getName());
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setLocation(userDto.getLocation());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setPassword(userDto.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with ID: " + id));
        doctorRepository.findByUser(user).ifPresent(doctorRepository::delete);
        patientRepository.findByUser(user).ifPresent(patientRepository::delete);

        userRepository.delete(user);
    }

}