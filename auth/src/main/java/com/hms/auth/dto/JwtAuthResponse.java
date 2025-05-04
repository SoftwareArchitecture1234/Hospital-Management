package com.hms.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponse {
    private  String message;
    private String accessToken;
    private String tokenType = "Bearer";
}