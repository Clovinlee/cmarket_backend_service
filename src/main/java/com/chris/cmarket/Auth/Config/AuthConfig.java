package com.chris.cmarket.Auth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.chris.cmarket.Common.Constant.CmarketLoadOrderConstant;

@Order(CmarketLoadOrderConstant.SECURITY_ORDER_AUTHENTICATED) // has lower priority than the default security config
@Configuration
public class AuthConfig {
    @Bean
    SecurityFilterChain authenticatedRoutes(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/auth/**") // only apply to /auth endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/me").authenticated())
                .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
                .httpBasic(basic -> basic.disable()); // disable basic auth

        return http.build();
    }
}