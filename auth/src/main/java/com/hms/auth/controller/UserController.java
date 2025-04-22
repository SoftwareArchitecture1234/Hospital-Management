package com.hms.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.auth.dto.UserProfileResponse;
import com.hms.auth.exception.UserNotFound;
import com.hms.auth.service.authService.AuthService;
// import org.springframework.http.HttpStatusCode;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {

    private final AuthService authService;


    @Operation(summary = "Get user profile", description = "Returns the authenticated user's profile information",
               security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully",
                     content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile() {
        try {
            return ResponseEntity.ok(
                UserProfileResponse.builder()
                    .message("Get user successfully !!")
                    .user(authService.getProfile())
                    .build()
            );
        } catch (UserNotFound e){
            return ResponseEntity
                .status(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()))
                .body(
                    UserProfileResponse.builder()
                        .message(e.getMessage())
                        .user(null)
                        .build()
                );
        }
    }
}
