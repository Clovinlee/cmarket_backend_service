package com.chris.cmarket.Auth.Service;

import com.chris.cmarket.Auth.Property.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    private final JwtEncoder jwtEncoder;

    /**
     * Generate JWT Token
     *
     * @param subject user email
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject) {
        return buildJwtToken(jwtProperties.getExpiryTimeSeconds(), subject, "auth");
    }

    /**
     * @param subject  user uuid
     * @param provider auth provider to be provided. Default is auth
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject, String provider) {
        return buildJwtToken(jwtProperties.getExpiryTimeSeconds(), subject, provider);
    }

    /**
     * Generate refresh JWT Token (longer expiry)
     *
     * @param subject user email
     * @return user signed jwt token
     */
    public String generateRefreshJwtToken(String subject) {
        return buildJwtToken(jwtProperties.getRefreshExpiryTimeSeconds(), subject, "auth");
    }

    /**
     * @param subject  user uuid
     * @param provider auth provider to be provided. Default is auth
     * @return user signed jwt token
     */
    public String generateRefreshJwtToken(String subject, String provider) {
        return buildJwtToken(jwtProperties.getRefreshExpiryTimeSeconds(), subject, provider);
    }

    /**
     * @param expirySeconds expiry in seconds
     * @param subject user email
     * @return JWT Token
     */
    private String buildJwtToken(int expirySeconds, String subject, String provider) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expirySeconds);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .subject(subject)
                .claim("provider", provider)
                .expiresAt(expiry)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
