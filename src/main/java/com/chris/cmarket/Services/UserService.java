package com.chris.cmarket.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chris.cmarket.Dtos.Requests.CreateUserDTO;
import com.chris.cmarket.Exceptions.NotFoundException;
import com.chris.cmarket.Exceptions.Auth.EmailAlreadyExistException;
import com.chris.cmarket.Models.UserModel;
import com.chris.cmarket.Repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    /**
     * Creates a new user in the database.
     * 
     * @param userDTO
     * @return
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
     * 
     * @param email
     * @return
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
     * @param email
     * @return
     */
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
