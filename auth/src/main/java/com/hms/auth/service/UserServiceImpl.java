package com.hms.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hms.auth.dto.UserDto;
import com.hms.auth.dto.UserProfileResponse;
import com.hms.auth.entity.Doctor;
import com.hms.auth.entity.Patient;
import com.hms.auth.entity.Role;
import com.hms.auth.entity.RoleType;
import com.hms.auth.entity.User;
import com.hms.auth.exception.UserNotFound;
import com.hms.auth.repository.DoctorRepository;
import com.hms.auth.repository.PatientRepository;
import com.hms.auth.repository.UserRepository;
import com.hms.auth.service.patientService.PatientServiceClient;
import com.hms.auth.service.staffService.StaffServiceClient;
import com.hms.auth.service.userService.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffServiceClient staffServiceClient;

    @Autowired
    private PatientServiceClient patientServiceClient;

    UserServiceImpl(PatientRepository patientRepository,DoctorRepository doctorRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with ID: " + id));

        existingUser.getRoles().clear();

        // existingUser.setEmail(userDto.getEmail());
        existingUser.setName(userDto.getName());
        // existingUser.setUsername(userDto.getUsername());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setLocation(userDto.getLocation());
        // existingUser.setPassword(userDto.getPassword());

        List<Role> roles = userDto.getRoles().stream()
                .map(roleName -> Role.builder()
                        .roleName(RoleType.valueOf(roleName))
                        .user(existingUser)
                        .build())
                .toList();

        existingUser.getRoles().addAll(roles);
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with ID: " + id));
                
        // if (user.getRoles().stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_DOCTOR)) {
        //     staffServiceClient.deleteDoctor(user.getId());
        // } else if (user.getRoles().stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_PATIENT)) {
        //     patientServiceClient.deletePatient(user.getId());
        // }
        // userRepository.delete(user);
    }

    @Override
    public UserProfileResponse getProfileWithDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Optional<User> userOptional = userRepository.findByUsernameOrEmail(username, username);
    if (userOptional.isEmpty()) {
        throw new UserNotFound("User not found");
    }

    User user = userOptional.get();
    Doctor doctor = null;
    Patient patient = null;

    UserProfileResponse res = null;

    if (user.getRoles().stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_DOCTOR)) {
        doctor = doctorRepository.findByDoctorId(user.getId());
            res = UserProfileResponse.builder()
                .message("Get user successfully !!")
                .doctor(doctor)
                .user(user)
                .build();
    } else if (user.getRoles().stream().anyMatch(role -> role.getRoleName() == RoleType.ROLE_PATIENT)) {
        patient = patientRepository.findByPatientId(user.getId());
            res = UserProfileResponse.builder()
                .message("Get user successfully !!")
                .patient(patient)
                .user(user)
                .build();
    }
    else res = UserProfileResponse.builder()
            .message("Get user successfully !!")
            .user(user)
            .build();

    return res;
    }
}
