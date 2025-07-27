package com.chris.cmarket.Auth.Client.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class GithubUserResponse {
    private int id;
    private String login;
    private String location;
    private String name;
    @Nullable
    private String email;
    private String bio;
    @JsonProperty("avatar_url")
    private String avatarUrl;
}
