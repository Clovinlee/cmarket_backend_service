package com.chris.cmarket.User.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chris.cmarket.Common.Response.APIResponse;
import com.chris.cmarket.User.Dto.UserDTO;
import com.chris.cmarket.User.Model.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<APIResponse<UserDTO>> me(@AuthenticationPrincipal UserModel user) {
        return ResponseEntity.ok(APIResponse.success(new UserDTO(user)));
    }
}
