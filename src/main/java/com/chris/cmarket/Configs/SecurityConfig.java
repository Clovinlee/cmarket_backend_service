package com.chris.cmarket.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chris.cmarket.Configs.Converter.EmailJwtAuthenticationConverter;
import com.chris.cmarket.Constants.CmarketLoadOrderConstant;

@Configuration
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtDecoder jwtDecoder;

    private final EmailJwtAuthenticationConverter emailJwtAuthenticationConverter;

    public SecurityConfig(JwtDecoder jwtDecoder, EmailJwtAuthenticationConverter emailJwtAuthenticationConverter) {
        this.jwtDecoder = jwtDecoder;
        this.emailJwtAuthenticationConverter = emailJwtAuthenticationConverter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .formLogin(login -> login.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((config) -> config.jwt(
                        jwt -> jwt.decoder(this.jwtDecoder)
                                .jwtAuthenticationConverter(this.emailJwtAuthenticationConverter)));

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
