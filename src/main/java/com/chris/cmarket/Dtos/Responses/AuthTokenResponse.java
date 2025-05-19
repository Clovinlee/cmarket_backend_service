package com.chris.cmarket.Dtos.Responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthTokenResponse {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;
}
