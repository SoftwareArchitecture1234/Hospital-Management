package com.hms.auth.controller;

import com.hms.auth.dto.AuthRequest;
import com.hms.auth.dto.AuthResponse;
import com.hms.auth.dto.LoginRequest;
import com.hms.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "APIs for user authentication")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
        summary = "Authenticate user", 
        description = "Authenticate a user by validating the provided username and password. Returns a JWT token upon successful authentication."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "401", description = "Invalid username or password", 
                     content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(
        summary = "Register user", 
        description = "Register a new user by providing necessary details."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully registered", 
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid input data", 
                     content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest authRequest) {
        String token = authService.register(authRequest.getUsername(), authRequest.getPassword(), authRequest.getEmail());
        return ResponseEntity.status(201).body(new AuthResponse(token));
    }

}
