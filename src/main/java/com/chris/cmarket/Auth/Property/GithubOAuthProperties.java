package com.chris.cmarket.Auth.Property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.github")
@Data
public class GithubOAuthProperties {
    /**
     * Oauth Github Client ID
     */
    private String clientId;

    /**
     * Oauth Github Client Secret
     */
    private String clientSecret;

    /**
     * Oauth Github Redirect URI
     */
    private String redirectUri;
}
