package com.chris.cmarket.Product.Config;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class ProductRouteConfig implements RouteConfigInterface {

    @Override
    public void configureRoute(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/*").permitAll()
                );
    }
}
