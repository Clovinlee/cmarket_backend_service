package com.chris.cmarket.Auth.Client.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubAccessTokenResponse(
        @JsonProperty("access_token") String accessToken,
        String scope,
        @JsonProperty("token_type") String tokenType
) {
}
