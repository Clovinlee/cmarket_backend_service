package com.chris.cmarket.Auth.Converter;

import com.chris.cmarket.Auth.Model.UserDetailsImpl;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserService userService;

    @Override
    @NonNull
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        String subject = source.getSubject();
        UserModel user = userService.findOrFailByEmail(subject);
        UserDetails userPrincipal = new UserDetailsImpl(user);

        return new UsernamePasswordAuthenticationToken(userPrincipal, source, Collections.emptyList());
    }
}
