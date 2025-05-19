package com.chris.cmarket.Services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Configs.Properties.JwtProperties;
import com.chris.cmarket.Models.UserModel;

@Service
public class JwtService {

    @Value("${spring.application.name}")
    private String applicationName;

    private final JwtEncoder jwtEncoder;

    private final JwtProperties jwtProperties;

    public JwtService(JwtEncoder jwtEncoder, JwtProperties jwtProperties) {
        this.jwtEncoder = jwtEncoder;
        this.jwtProperties = jwtProperties;
    }

    /**
     * Generates a JWT token for the user.
     *
     * @param user
     * @return
     */
    public String generateJwtToken(UserModel user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(applicationName)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.jwtProperties.expiryTimeSeconds()))
                .subject(user.getEmail())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Generates a refresh token for the user.
     *
     * @param user
     * @return
     */
    public String generateJwtRefreshToken(UserModel user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(applicationName)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.jwtProperties.refreshExpiryTimeSeconds()))
                .subject(user.getEmail())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Get expiry time in seconds.
     * 
     * @return
     */
    public Integer getExpiryTimeSeconds() {
        return this.jwtProperties.expiryTimeSeconds();
    }

    /**
     * Get refresh expiry time in seconds.
     * 
     * @return
     */
    public Integer getRefreshExpiryTimeSeconds() {
        return this.jwtProperties.refreshExpiryTimeSeconds();
    }
}
