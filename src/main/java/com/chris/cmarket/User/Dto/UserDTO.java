package com.chris.cmarket.User.Dto;

import com.chris.cmarket.User.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDTO(UserModel user) {
        this(user.getName(), user.getEmail());
    }
}
