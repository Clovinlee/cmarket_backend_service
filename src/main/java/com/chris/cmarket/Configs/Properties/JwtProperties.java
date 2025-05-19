package com.chris.cmarket.Configs.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
                /**
                 * Property for the JWT key store file location.
                 */
                String keyStore,

                /**
                 * Property for the JWT key store password.
                 */
                String keyStorePassword,

                /**
                 * Property for the JWT key store type.
                 */
                String storePassword,

                /**
                 * Property for the JWT key alias.
                 */
                String keyAlias,

                /**
                 * Expiry time in seconds for the JWT token.
                 */
                int expiryTimeSeconds,

                /**
                 * Expiry time in seconds for the JWT refresh token.
                 */
                int refreshExpiryTimeSeconds) {
}
