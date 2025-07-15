package com.chris.cmarket.Infrastructure.Config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chris.cmarket.Common.Constant.CmarketLoadOrderConstant;

import lombok.AllArgsConstructor;

@Configuration
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
@EnableWebSecurity(debug = true)
@AllArgsConstructor
public class SecurityConfig {

    private final RouteConfig routeConfig;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // DEFAULT CONFIGURATION
        // http
        // .authorizeHttpRequests(auth -> auth
        // .anyRequest().authenticated())
        // .formLogin(withDefaults()) // use default login page
        // .httpBasic(withDefaults());

        this.routeConfig.initializeRoutes(http);

        http.securityMatcher("/**") // catch-all for other paths
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
