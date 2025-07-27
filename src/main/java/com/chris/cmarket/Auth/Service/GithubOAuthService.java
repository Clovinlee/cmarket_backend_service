package com.chris.cmarket.Auth.Service;

import com.chris.cmarket.Auth.Client.GithubOAuthClient;
import com.chris.cmarket.Auth.Client.Request.GithubAccessTokenRequest;
import com.chris.cmarket.Auth.Client.Response.GithubAccessTokenResponse;
import com.chris.cmarket.Auth.Client.Response.GithubUserResponse;
import com.chris.cmarket.Auth.Contract.OAuthServiceInterface;
import com.chris.cmarket.Auth.Enum.OAuthProviderEnum;
import com.chris.cmarket.Auth.Exception.OAuthException;
import com.chris.cmarket.Auth.Property.GithubOAuthProperties;
import com.chris.cmarket.Auth.Store.PkceStore;
import com.chris.cmarket.Common.Util.PkceGeneratorUtil;
import com.chris.cmarket.User.Dto.OAuthUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Service("github")
@AllArgsConstructor
public class GithubOAuthService implements OAuthServiceInterface {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final PkceStore pkceStore;
    private final GithubOAuthClient githubOauthClient;
    private final GithubOAuthProperties githubOauthProperties;

    @Override
    public String getOauthUrl() {
        ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(
                OAuthProviderEnum.GITHUB.getRegistrationId()
        );

        String authorizationUri = registration.getProviderDetails().getAuthorizationUri();
        String clientId = githubOauthProperties.getClientId();
        String redirectUri = githubOauthProperties.getRedirectUri();
        String codeChallenge = getCodeChallenge();

        return UriComponentsBuilder.fromUriString(authorizationUri)
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code_challenge", codeChallenge)
                .queryParam("code_challenge_method", "S256")
                .build(true) // true = encode URL
                .toUriString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String exchangeAccessToken(String code) {
        String codeVerifier = pkceStore.get(OAuthProviderEnum.GITHUB.getRegistrationId(), false);

        return exchangeAccessToken(code, codeVerifier);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String exchangeAccessToken(String code, String codeVerifier) {
        String uri = githubOauthProperties.getRedirectUri();

        GithubAccessTokenRequest request = new GithubAccessTokenRequest(
                githubOauthProperties.getClientId(),
                githubOauthProperties.getClientSecret(),
                code,
                "user:email read:user",
                githubOauthProperties.getRedirectUri(),
                codeVerifier
        );

        try {
            GithubAccessTokenResponse response = githubOauthClient.exchangeCodeForAccessToken(request);

            return response.accessToken();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // 4xx 5xx code from github
            throw new OAuthException(
                    "Failed to exchange GitHub code for access token",
                    ex,
                    ex.getStatusCode(),
                    uri,
                    ex.getResponseBodyAsString()
            );
        } catch (RestClientException ex) {
            // Rest client errors
            throw new OAuthException(
                    "Unexpected error during token exchange",
                    ex,
                    HttpStatus.SERVICE_UNAVAILABLE,
                    uri,
                    ex.toString()
            );
        } catch (Exception ex) {
            // else
            throw new OAuthException(
                    "Unexpected error during token exchange",
                    ex,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    uri,
                    ex.toString()
            );
        }
    }

    @Override
    public OAuthUserDTO fetchUserInfo(String accessToken) {
        GithubUserResponse response = githubOauthClient.fetchUserInformation(String.format("Bearer %s", accessToken));

        return new OAuthUserDTO(
                response.getName(),
                response.getEmail(),
                String.valueOf(response.getId()),
                OAuthProviderEnum.GITHUB.getRegistrationId()
        );
    }

    /**
     * Generate & store code verifier
     *
     * @return random generated code challenge
     */
    private String getCodeChallenge() {
        String codeVerifier = PkceGeneratorUtil.generateCodeVerifier();
        pkceStore.save(OAuthProviderEnum.GITHUB.getRegistrationId(), codeVerifier);

        return PkceGeneratorUtil.generateCodeChallenge(codeVerifier);
    }
}
