package com.chris.cmarket.Auth.Service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chris.cmarket.User.Dto.LoginUserDTO;
import com.chris.cmarket.User.Dto.UserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.PasswordService;
import com.chris.cmarket.User.Service.UserService;

@Service
public class AuthService {

    BCryptPasswordEncoder passwordEncoder;

    UserService userService;

    PasswordService passwordService;

    public AuthService(
            UserService userService,
            PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    /**
     * Authenticates a user and generates JWT tokens.
     *
     * @param email
     * @param password
     * @return
     */
    public UserDTO login(LoginUserDTO loginUserDTO) {
        UserModel userModel = userService.findOrFailByEmail(loginUserDTO.getEmail());

        if (!passwordService.matches(loginUserDTO.getPassword(), userModel.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }

        return new UserDTO(userModel);
    }
}
