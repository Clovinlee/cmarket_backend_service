package com.chris.cmarket.User.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateUserDTO extends UserDTO {
    @NotBlank
    private String password;
}
