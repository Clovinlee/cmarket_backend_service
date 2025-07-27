package com.chris.cmarket.Auth.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OAuthProviderEnum {
    GITHUB("github"),
    GOOGLE("google"),
    FACEBOOK("facebook");

    private final String registrationId;
}