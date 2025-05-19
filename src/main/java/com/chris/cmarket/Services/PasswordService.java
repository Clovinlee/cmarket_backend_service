package com.chris.cmarket.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Encodes a password using BCrypt.
     * 
     * @param password
     * @return
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Checks if a raw password matches an encoded password.
     * 
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
