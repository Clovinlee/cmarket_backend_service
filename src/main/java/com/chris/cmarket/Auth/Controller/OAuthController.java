package com.chris.cmarket.Auth.Controller;

import com.chris.cmarket.Auth.Contract.OAuthServiceInterface;
import com.chris.cmarket.Auth.Dto.AuthJwtDto;
import com.chris.cmarket.Auth.Enum.OAuthProviderEnum;
import com.chris.cmarket.Auth.Service.JwtService;
import com.chris.cmarket.User.Dto.OAuthUserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnBean(OAuthServiceInterface.class)
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthServiceInterface githubOauthService;
    private final UserService userService;
    private final JwtService jwtService;

    public OAuthController(@Qualifier("githubOAuthService") OAuthServiceInterface githubOauthService, UserService userService, JwtService jwtService) {
        this.githubOauthService = githubOauthService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/github/callback")
    public AuthJwtDto githubOauthCallback(@RequestParam("code") String code, @RequestParam(value = "code_verifier", required = false) String codeVerifier) {
        String accessCode = (null != codeVerifier)
                ? githubOauthService.exchangeAccessToken(code, codeVerifier)
                : githubOauthService.exchangeAccessToken(code);

        OAuthUserDTO oauthUserData = githubOauthService.fetchUserInfo(accessCode);
        UserModel userData = userService.createUserFromOAuth(oauthUserData);

        String accessToken = jwtService.generateJwtToken(userData.getUuid(), OAuthProviderEnum.GITHUB.getRegistrationId());
        String refreshToken = jwtService.generateRefreshJwtToken(userData.getUuid(), OAuthProviderEnum.GITHUB.getRegistrationId());

        return new AuthJwtDto(accessToken, refreshToken);
    }
}
