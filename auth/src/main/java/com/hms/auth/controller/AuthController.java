package com.hms.auth.controller;

import com.hms.auth.dto.JwtAuthResponse;
import com.hms.auth.dto.LoginDto;
import com.hms.auth.dto.RegisterRespone;
import com.hms.auth.dto.UserDto;
import com.hms.auth.entity.User;
import com.hms.auth.exception.EmailAreadyExisted;
import com.hms.auth.exception.InvalidException;
import com.hms.auth.exception.UsernameAlreadyExisted;
import com.hms.auth.service.authService.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Login", description = "Authenticates user credentials and returns JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                    content = @Content(schema = @Schema(implementation = JwtAuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        try {
            String token = authService.login(loginDto);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setAccessToken(token);
            jwtAuthResponse.setMessage("Login Successfully");

            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
        }
        catch (InvalidException e){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .body(
                            JwtAuthResponse.builder()
                                    .message(e.getMessage())
                                    .accessToken(null)
                                    .build()
                    );
        }
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Username or email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterRespone> registerUser(@RequestBody UserDto signupRequest) {
        try{
            return ResponseEntity.ok(
                    RegisterRespone.builder()
                            .message("Register Successfully !!")
                            .user(authService.registerUser(signupRequest))
                            .build()
            );
        } catch (UsernameAlreadyExisted | EmailAreadyExisted e) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .body(
                            RegisterRespone.builder()
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

}