package com.chris.cmarket.Auth.Controller;

import com.chris.cmarket.Auth.Contract.OAuthServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev/auth")
public class DevAuthController {

    private final OAuthServiceInterface githubOauthService;

    public DevAuthController(@Qualifier("github") OAuthServiceInterface githubOauthService) {
        this.githubOauthService = githubOauthService;
    }

    /**
     * @return list of oauth redirect url for test purpose
     */
    @GetMapping("/redirect")
    public String devAuthRedirect() {
        return String.format("<a href=\"%s\" target=\"_blank\">Github Auth Redirect</a>", githubOauthService.getOauthUrl());
    }

}
