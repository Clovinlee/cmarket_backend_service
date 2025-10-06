package com.chris.cmarket.Auth.Service;

import com.chris.cmarket.Auth.Property.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private final JwtEncoder jwtEncoder;

    /**
     * Generate JWT Token
     *
     * @param subject user uuid
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject) {
        return generateJwtToken(subject, "auth", null);
    }

    /**
     * Generate JWT Token
     *
     * @param subject user uuid
     * @param mapClaims additional claims to be included
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject,  Map<String, Object> mapClaims) {
        return generateJwtToken(subject, "auth", mapClaims);
    }

    /**
     * Generate JWT Token
     *
     * @param subject  user uuid
     * @param provider auth provider to be provided. Default is auth
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject, String provider) {
        return generateJwtToken(subject, provider, null);
    }

    /**
     * Generate JWT Token
     *
     * @param subject   user uuid
     * @param provider  auth provider to be provided. Default is auth
     * @param mapClaims additional claims to be included
     * @return user signed jwt token
     */
    public String generateJwtToken(String subject, String provider, Map<String, Object> mapClaims) {
        return buildJwtToken(jwtProperties.getExpiryTimeSeconds(), subject, provider, mapClaims);
    }

    /**
     * Generate refresh JWT Token (longer expiry)
     *
     * @param subject user uuid
     * @return user signed jwt token
     */
    public String generateRefreshJwtToken(String subject) {
        return generateRefreshJwtToken(subject, "auth", null);
    }

    /**
     * Generate refresh JWT Token (longer expiry)
     *
     * @param subject  user uuid
     * @param provider auth provider to be provided. Default is auth
     * @return user signed jwt token
     */
    public String generateRefreshJwtToken(String subject, String provider) {
        return generateRefreshJwtToken(subject, provider, null);
    }

    /**
     * Generate refresh JWT Token (longer expiry)
     *
     * @param subject   user uuid
     * @param provider  auth provider to be provided. Default is auth
     * @param mapClaims additional claims to be included
     * @return user signed jwt token
     */
    public String generateRefreshJwtToken(String subject, String provider, Map<String, Object> mapClaims) {
        return buildJwtToken(jwtProperties.getRefreshExpiryTimeSeconds(), subject, provider, mapClaims);
    }

    /**
     * @param expirySeconds expiry in seconds
     * @param subject       user uuid
     * @param mapClaims     mapped claims
     * @return JWT Token
     */
    private String buildJwtToken(int expirySeconds, String subject, String provider, Map<String, Object> mapClaims) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expirySeconds);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .subject(subject)
                .claim("provider", provider)
                .claims(c -> {
                    if (mapClaims != null) {
                        c.putAll(mapClaims);
                    }
                })
                .expiresAt(expiry)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
