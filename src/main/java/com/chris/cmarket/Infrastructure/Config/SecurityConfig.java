package com.chris.cmarket.Infrastructure.Config;

import com.chris.cmarket.Auth.Converter.JwtToUserConverter;
import com.chris.cmarket.Common.Constant.CmarketLoadOrderConstant;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(CmarketLoadOrderConstant.DEFAULT_PRIORITY)
@EnableWebSecurity()
@PropertySource({
        "classpath:configs/auth/github.properties"
})
@AllArgsConstructor
public class SecurityConfig {

    private final RouteConfig routeConfig;

    private final JwtToUserConverter jwtToUserConverter;

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
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtToUserConverter)
                        )
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
