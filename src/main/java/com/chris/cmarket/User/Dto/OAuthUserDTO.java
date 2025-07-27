package com.chris.cmarket.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OAuthUserDTO {
    private String name;
    private String email;
    private String provider;
    private String providerId;
}
