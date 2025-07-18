package com.chris.cmarket.Auth.Model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chris.cmarket.User.Model.UserModel;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String email;
    private final boolean enabled;

    public UserDetailsImpl(UserModel user) {
        this(user.getEmail(), true);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
