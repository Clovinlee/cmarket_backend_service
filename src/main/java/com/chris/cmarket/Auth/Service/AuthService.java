package com.chris.cmarket.Auth.Service;

import com.chris.cmarket.User.Dto.LoginUserDTO;
import com.chris.cmarket.User.Dto.UserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.PasswordService;
import com.chris.cmarket.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    final UserService userService;
    final PasswordService passwordService;

    /**
     * @param loginUserDTO the login credentials
     * @return user dto
     * @throws BadCredentialsException invalid credential
     */
    public UserDTO login(LoginUserDTO loginUserDTO) {
        UserModel userModel = userService.findOrFailByEmail(loginUserDTO.getEmail());

        if (!passwordService.matches(loginUserDTO.getPassword(), userModel.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }

        return new UserDTO(userModel);
    }
}
