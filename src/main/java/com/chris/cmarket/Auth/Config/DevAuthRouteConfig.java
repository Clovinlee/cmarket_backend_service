package com.chris.cmarket.Auth.Config;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevAuthRouteConfig implements RouteConfigInterface {
    @Override
    public void configureRoute(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/dev/auth/github").permitAll());
    }
}