package com.chris.cmarket.User.Dto;

import com.chris.cmarket.User.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {
    Long id;

    String uuid;

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

        this.id = user.getId();
        this.uuid = user.getUuid();
    }

    /**
     * User for JWT Claims map
     *
     * @return user DTO map
     */
    public Map<String, Object> toClaims() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("name", this.name);

        return map;
    }
}
