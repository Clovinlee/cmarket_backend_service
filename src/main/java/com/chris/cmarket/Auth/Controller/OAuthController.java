package com.chris.cmarket.Auth.Controller;

import com.chris.cmarket.Auth.Contract.OAuthServiceInterface;
import com.chris.cmarket.User.Dto.OAuthUserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthServiceInterface githubOauthService;
    private final UserService userService;

    public OAuthController(@Qualifier("github") OAuthServiceInterface githubOauthService, UserService userService) {
        this.githubOauthService = githubOauthService;
        this.userService = userService;
    }

    @GetMapping("/github/callback")
    public String githubOauthCallback(@RequestParam("code") String code, @RequestParam(value = "code_verifier", required = false) String codeVerifier) {
        String accessCode = (null != codeVerifier)
                ? githubOauthService.exchangeAccessToken(code, codeVerifier)
                : githubOauthService.exchangeAccessToken(code);

        OAuthUserDTO oauthUserData = githubOauthService.fetchUserInfo(accessCode);
        UserModel userData = userService.createUserFromOAuth(oauthUserData);

        return userData.toString();
    }
}
