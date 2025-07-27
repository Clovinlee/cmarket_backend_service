package com.chris.cmarket.Auth.Client.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubAccessTokenRequest(
        @JsonProperty(value = "client_id", access = JsonProperty.Access.READ_ONLY) String clientId,
        @JsonProperty(value = "client_secret", access = JsonProperty.Access.READ_ONLY) String clientSecret,
        String code,
        String scope,
        @JsonProperty(value = "redirect_uri", access = JsonProperty.Access.READ_ONLY) String redirectUri,
        @JsonProperty(value = "code_verifier", access = JsonProperty.Access.READ_ONLY) String codeVerifier
) {
    @Override
    public String toString() {
        return "GithubAccessTokenRequest{" +
                ", redirectUri='" + redirectUri + '\'' +
                ", codeVerifier='" + codeVerifier + '\'' +
                '}';
    }
}

