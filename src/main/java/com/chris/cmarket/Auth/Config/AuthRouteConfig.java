package com.chris.cmarket.Auth.Config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;

@Component
public class AuthRouteConfig implements RouteConfigInterface {
    @Override
    public void configureRoute(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/auth/**") // only apply to /auth endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/me").authenticated());
    }
}