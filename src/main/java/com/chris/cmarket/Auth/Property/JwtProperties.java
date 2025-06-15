package com.chris.cmarket.Auth.Property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "jwt")
@SuppressWarnings("unused")
@Data
public class JwtProperties {
    /**
     * Property for the JWT key store file location.
     */
    private String keyStore;

    /**
     * Property for the JWT key store password.
     */
    private String keyStorePassword;

    /**
     * Property for the JWT key store type.
     */
    private String storePassword;

    /**
     * Property for the JWT key alias.
     */
    private String keyAlias;

    /**
     * Expiry time in seconds for the JWT token.
     */
    private int expiryTimeSeconds;

    /**
     * Expiry time in seconds for the JWT refresh token.
     */
    private int refreshExpiryTimeSeconds;
}
