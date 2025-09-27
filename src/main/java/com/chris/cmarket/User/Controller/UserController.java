package com.chris.cmarket.User.Controller;

import com.chris.cmarket.User.Model.UserModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal(expression = "user") UserModel user) {
        return "Welcome" + " " + user.getName() + "! You are authenticated.";
    }
}