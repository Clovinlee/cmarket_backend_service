package com.chris.cmarket.Auth.Client;

import com.chris.cmarket.Auth.Client.Request.GithubAccessTokenRequest;
import com.chris.cmarket.Auth.Client.Response.GithubAccessTokenResponse;
import com.chris.cmarket.Auth.Client.Response.GithubUserResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface GithubOAuthClient {
    @PostExchange(value = "https://github.com/login/oauth/access_token", contentType = "application/json", accept = "application/json")
    GithubAccessTokenResponse exchangeCodeForAccessToken(@RequestBody GithubAccessTokenRequest request);

    @GetExchange(url = "https://api.github.com/user", accept = "application/json")
    GithubUserResponse fetchUserInformation(@RequestHeader("Authorization") String accessToken);
}
