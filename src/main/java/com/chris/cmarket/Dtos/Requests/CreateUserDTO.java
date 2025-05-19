package com.chris.cmarket.Dtos.Requests;

import com.chris.cmarket.Dtos.UserDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateUserDTO extends UserDTO {
    @NotBlank
    private String password;
}
