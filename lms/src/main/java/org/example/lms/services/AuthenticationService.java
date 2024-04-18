package org.example.lms.services;

import java.util.Optional;

import org.example.lms.dto.inbound.user.UserLoginRequest;
import org.example.lms.dto.inbound.user.UserRegisterRequest;
import org.example.lms.dto.outbound.user.UserLoginResponse;
import org.example.lms.dto.outbound.user.UserRegisterResponse;
import org.example.lms.exceptions.user.UserDoesNotExistException;
import org.example.lms.exceptions.user.UserIsAlreadyRegisteredException;
import org.example.lms.models.User;
import org.example.lms.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponse signup(UserRegisterRequest input) {
        Optional<User> userEntity = userRepository.findByEmail(input.getEmail());
        if(userEntity.isPresent()){
            throw new UserIsAlreadyRegisteredException();
        }
        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepository.save(user);
        return new UserRegisterResponse();

    }

    public User authenticate(UserLoginRequest input) {
        Optional<User> userEntity = userRepository.findByEmail(input.getEmail());
        if(userEntity.isEmpty()){
            throw new UserDoesNotExistException();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userEntity.get();
    }
}
