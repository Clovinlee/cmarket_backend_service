package com.chris.cmarket.Configs.Converter;

import java.util.Collection;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.chris.cmarket.Models.UserModel;
import com.chris.cmarket.Services.UserService;

@Component
public class EmailJwtAuthenticationConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserService userService;

    public EmailJwtAuthenticationConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        String email = jwt.getSubject();
        if (email == null) {
            throw new IllegalArgumentException("JWT token does not contain 'sub' claim");
        }

        UserModel user = userService.findOrFailByEmail(email);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, jwt, authorities);
    }
}