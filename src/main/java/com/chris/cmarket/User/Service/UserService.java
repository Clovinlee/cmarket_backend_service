package com.chris.cmarket.User.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chris.cmarket.Auth.Exception.EmailAlreadyExistException;
import com.chris.cmarket.Common.Exception.NotFoundException;
import com.chris.cmarket.User.Dto.CreateUserDTO;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    /**
     * Creates a new user in the database.
     * 
     * @param userDTO user input dto
     * @return new created user
     */
    public UserModel createUser(CreateUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistException(userDTO.getEmail());
        }

        String encodedPassword = this.passwordService.encodePassword(userDTO.getPassword());

        UserModel user = new UserModel(userDTO.getName(), userDTO.getEmail(),
                encodedPassword);

        return userRepository.save(user);
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

    /**
     * Retrieves a user by their email address.
     * 
     * @param email user email
     * @return nullable user model
     */
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
