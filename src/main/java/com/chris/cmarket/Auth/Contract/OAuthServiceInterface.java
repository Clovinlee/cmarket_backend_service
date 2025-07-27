package com.chris.cmarket.Auth.Contract;

import com.chris.cmarket.User.Dto.OAuthUserDTO;
import com.chris.cmarket.User.Dto.UserDTO;

public interface OAuthServiceInterface {
    /**
     * @return Return base oauth url
     */
    String getOauthUrl();

    /**
     * Exchange access token, will default to PKCE Store on Concurrent Hash Map {@link com.chris.cmarket.Auth.Store.PkceStore}
     *
     * @param code token code to be exchanged into access token
     * @return access token to be exchanged with user information
     */
    String exchangeAccessToken(String code);

    /**
     * @param code         token code to be exchanged into access token
     * @param codeVerifier code verifier to be sent to the GitHub OAuth server (for PKCE flow)
     * @return access token to be exchanged with user information
     */
    String exchangeAccessToken(String code, String codeVerifier);

    /**
     * @param accessToken the OAuth 2.0 access token obtained after authorization
     * @return a {@link UserDTO} containing user general information
     * @throws com.chris.cmarket.Auth.Exception.OAuthException if the user information cannot be fetched or parsed
     */
    OAuthUserDTO fetchUserInfo(String accessToken);
}
