package com.chris.cmarket.Auth.Controller;

import com.chris.cmarket.Auth.Contract.OAuthServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@ConditionalOnBean(OAuthServiceInterface.class)
@RestController
@RequestMapping("/dev/auth")
public class DevAuthController {

    private final OAuthServiceInterface githubOauthService;

    public DevAuthController(@Qualifier("githubOAuthService") OAuthServiceInterface githubOauthService) {
        this.githubOauthService = githubOauthService;
    }

    @GetMapping("/redirect")
    public String devAuthRedirect() {
        return String.format("<a href=\"%s\" target=\"_blank\">Github Auth Redirect</a>",
                githubOauthService.getOauthUrl());
    }

}
