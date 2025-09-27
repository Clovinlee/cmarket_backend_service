package com.chris.cmarket.User.Config;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class UserRouteConfig implements RouteConfigInterface {
    @Override
    public void configureRoute(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/ user/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/user/me").authenticated());
    }
}
