package com.chris.cmarket.Auth.Property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    /**
     * Private key location of JWT key
     */
    private RSAPrivateKey privateKey;

    /**
     * Public key location of JWT key
     */
    private RSAPublicKey publicKey;

    /**
     * Expiry time in seconds for the JWT token.
     */
    private int expiryTimeSeconds;

    /**
     * Expiry time in seconds for the JWT refresh token.
     */
    private int refreshExpiryTimeSeconds;
}
