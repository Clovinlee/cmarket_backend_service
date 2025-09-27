package com.chris.cmarket.Auth.Controller;

import com.chris.cmarket.Auth.Dto.AuthJwtDto;
import com.chris.cmarket.Auth.Service.AuthService;
import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.User.Dto.CreateUserDTO;
import com.chris.cmarket.User.Dto.LoginUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> registerUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        authService.register(createUserDTO);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse<AuthJwtDto>> loginUser(@Validated @RequestBody LoginUserDTO loginUserDTO) {
        AuthJwtDto authJwtDto = authService.login(loginUserDTO);

        return ResponseEntity.ok(APIResponse.success(authJwtDto));
    }
}
