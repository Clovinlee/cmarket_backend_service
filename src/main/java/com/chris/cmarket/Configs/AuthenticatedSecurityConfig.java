package com.chris.cmarket.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chris.cmarket.Constants.CmarketLoadOrderConstant;

@Order(CmarketLoadOrderConstant.SECURITY_ORDER_AUTHENTICATED)
@Configuration
public class AuthenticatedSecurityConfig {
    private final JwtDecoder jwtDecoder;

    public AuthenticatedSecurityConfig(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Bean
    SecurityFilterChain authenticatedRoutes(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/user/**")
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));

        return http.build();
    }
}