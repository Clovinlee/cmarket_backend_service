package com.chris.cmarket.Auth.Service;

import com.chris.cmarket.Auth.Dto.AuthJwtDto;
import com.chris.cmarket.User.Dto.CreateUserDTO;
import com.chris.cmarket.User.Dto.LoginUserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.PasswordService;
import com.chris.cmarket.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    /**
     * Register user
     *
     * @param registerUserDTO register user DTO
     */
    public void register(CreateUserDTO registerUserDTO) {
        String encodedPassword = this.passwordService.encodePassword(registerUserDTO.getPassword());

        userService.createUserFromAuth(registerUserDTO, encodedPassword);
    }

    /**
     * Login user
     *
     * @param loginUserDTO Login user DTO
     * @return JWT access & refresh token
     */
    public AuthJwtDto login(LoginUserDTO loginUserDTO) {
        UserModel userModel = userService.findOrFailByEmail(loginUserDTO.getEmail());
        if (!passwordService.matches(loginUserDTO.getPassword(), userModel.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }

        String accessToken = jwtService.generateJwtToken(userModel.getEmail());
        String refreshToken = jwtService.generateRefreshJwtToken(userModel.getEmail());

        return new AuthJwtDto(accessToken, refreshToken);
    }
}
