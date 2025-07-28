package com.chris.cmarket.User.Model;

import com.chris.cmarket.User.Dto.OAuthUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@JsonIgnoreProperties({ "password" })
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(unique = true)
    @Nullable
    private String email;

    @Setter(AccessLevel.NONE)
    private String password;

    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserModel(OAuthUserDTO oAuthUserDTO) {
        this(oAuthUserDTO.getName(), oAuthUserDTO.getEmail(), null);

        this.provider = oAuthUserDTO.getProvider();
        this.providerId = oAuthUserDTO.getProviderId();
    }
}