package com.chris.cmarket.Services;

import java.time.LocalDateTime;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Dtos.Responses.AuthTokenResponse;
import com.chris.cmarket.Models.UserModel;

@Service
public class AuthService {

    BCryptPasswordEncoder passwordEncoder;

    UserService userService;

    PasswordService passwordService;

    JwtService jwtService;

    public AuthService(
            JwtService jwtService,
            UserService userService,
            PasswordService passwordService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordService = passwordService;
    }

    /**
     * Authenticates a user and generates JWT tokens.
     *
     * @param email
     * @param password
     * @return
     */
    public AuthTokenResponse login(String email, String password) {
        UserModel user = userService.findOrFailByEmail(email);

        if (!passwordService.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String accessToken = jwtService.generateJwtToken(user);
        String refreshToken = jwtService.generateJwtRefreshToken(user);

        return new AuthTokenResponse(accessToken, refreshToken, LocalDateTime.now()
                .plusSeconds(jwtService.getExpiryTimeSeconds()));
    }

}
