package com.chris.cmarket.User.Controller;

import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.User.Dto.UserDTO;
import com.chris.cmarket.User.Model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<UserDTO> me(@AuthenticationPrincipal(expression = "user") UserModel user) {
        UserDTO userDto = new UserDTO(user);

        return APIResponse.success(userDto);
    }
}