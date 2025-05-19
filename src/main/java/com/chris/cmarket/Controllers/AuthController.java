package com.chris.cmarket.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chris.cmarket.Dtos.Requests.CreateUserDTO;
import com.chris.cmarket.Dtos.Requests.LoginUserDTO;
import com.chris.cmarket.Dtos.Responses.AuthTokenResponse;
import com.chris.cmarket.Responses.APIResponse;
import com.chris.cmarket.Services.AuthService;
import com.chris.cmarket.Services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthTokenResponse>> loginUser(@Validated @RequestBody LoginUserDTO loginUserDTO) {
        AuthTokenResponse authToken = this.authService.login(
                loginUserDTO.getEmail(),
                loginUserDTO.getPassword());

        return ResponseEntity.ok(APIResponse.success(authToken));
    }
}
