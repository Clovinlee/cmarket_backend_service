package com.chris.cmarket.User.Service;

import com.chris.cmarket.Auth.Exception.EmailAlreadyExistException;
import com.chris.cmarket.Auth.Exception.OAuthUserAlreadyExistException;
import com.chris.cmarket.Common.Exception.NotFoundException;
import com.chris.cmarket.User.Dto.CreateUserDTO;
import com.chris.cmarket.User.Dto.OAuthUserDTO;
import com.chris.cmarket.User.Dto.UserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * @param userDTO         user input DTO
     * @param encodedPassword encoded password
     * @return created user
     */
    public UserModel createUserFromAuth(CreateUserDTO userDTO, String encodedPassword) {
        if (userRepository.existsByEmailAndProviderIsNull(userDTO.getEmail())) {
            throw new EmailAlreadyExistException(userDTO.getEmail());
        }

        UserModel user = new UserModel(
                userDTO.getName(),
                userDTO.getEmail(),
                encodedPassword
        );

        return userRepository.save(user);
    }

    /**
     * @param oAuthUserDTO oAuthUserDTO from oauth verification
     * @return created UserModel
     */
    public UserModel createUserFromOAuth(OAuthUserDTO oAuthUserDTO) {
        if (userRepository.existsByProviderAndProviderId(oAuthUserDTO.getProvider(), oAuthUserDTO.getProviderId())) {
            throw new OAuthUserAlreadyExistException(oAuthUserDTO.getName(), oAuthUserDTO.getProvider(), oAuthUserDTO.getProviderId());
        }

        UserModel user = new UserModel(oAuthUserDTO);

        return userRepository.save(user);
    }

    /**
     *
     * @param oAuthUserDTO oAuthUserDTO from oauth verification
     * @return optional user model
     */
    public Optional<UserModel> getUserFromOAuth(OAuthUserDTO oAuthUserDTO) {
        return userRepository.findByProviderAndProviderId(oAuthUserDTO.getProvider(), oAuthUserDTO.getProviderId());
    }

    /**
     * Retrieves a user by their email address.
     * OR fails
     *
     * @param email user email
     * @return user email
     * @throws NotFoundException email not found
     */
    public UserModel findOrFailByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("User with email %s not found", email), null));
    }

    public UserModel findOrFailByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("User with uuid %s not found", uuid), null));
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email user email
     * @return nullable user model
     */
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Convert user model to claims (jwt)
     *
     * @param user auth user
     * @return map of user claims
     */
    public Map<String, Object> modelToClaims(UserModel user) {
        UserDTO userDto = new UserDTO(user);

        return userDto.toClaims();
    }
}
