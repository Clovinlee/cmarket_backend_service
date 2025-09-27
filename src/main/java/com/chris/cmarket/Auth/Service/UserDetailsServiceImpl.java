package com.chris.cmarket.Auth.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chris.cmarket.Auth.Model.UserDetailsImpl;
import com.chris.cmarket.User.Model.UserModel;
import com.chris.cmarket.User.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with uuid: " + uuid));

        return new UserDetailsImpl(user);
    }
}
