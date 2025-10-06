package com.chris.cmarket.Filter.Route;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class FilterRouteConfig implements RouteConfigInterface {
    @Override
    public void configureRoute(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/filters/**").permitAll()
                );
    }
}
