package org.example.lms;

import org.example.lms.controllers.AuthenticationController;
import org.example.lms.dto.inbound.user.UserLoginRequest;
import org.example.lms.dto.inbound.user.UserRegisterRequest;
import org.example.lms.dto.outbound.user.UserLoginResponse;
import org.example.lms.dto.outbound.user.UserRegisterResponse;
import org.example.lms.exceptions.user.UserDoesNotExistException;
import org.example.lms.exceptions.user.UserIsAlreadyRegisteredException;
import org.example.lms.models.User;
import org.example.lms.services.impl.AuthenticationService;
import org.example.lms.services.impl.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticate_Success() throws Exception {
        // Prepare
        UserLoginRequest loginRequest = new UserLoginRequest("validemail@example.com", "validpassword");
        User authenticatedUser = new User();
        when(authenticationService.authenticate(loginRequest)).thenReturn(authenticatedUser);
        when(jwtService.generateToken(any())).thenReturn("dummyToken");

        // Execute
        ResponseEntity<UserLoginResponse> responseEntity = authenticationController.authenticate(loginRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        // Prepare
        UserLoginRequest loginRequest = new UserLoginRequest("nonexistent@example.com", "invalidpassword");
        when(authenticationService.authenticate(loginRequest)).thenThrow(new UserDoesNotExistException());

        // Execute and Verify
        assertThrows(UserDoesNotExistException.class, () -> authenticationController.authenticate(loginRequest));
    }

    @Test
    public void testRegister_Success() throws Exception {
        // Prepare
        UserRegisterRequest registerRequest = new UserRegisterRequest("John", "Doe", "1234567890", "test@example.com", "password");
        when(authenticationService.signup(registerRequest)).thenReturn(new UserRegisterResponse());

        // Execute
        ResponseEntity<UserRegisterResponse> responseEntity = authenticationController.register(registerRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        // Prepare
        UserRegisterRequest registerRequest = new UserRegisterRequest("John", "Doe", "1234567890", "existing@example.com", "password");
        when(authenticationService.signup(registerRequest)).thenThrow(new UserIsAlreadyRegisteredException());

        // Execute and Verify
        assertThrows(UserIsAlreadyRegisteredException.class, () -> authenticationController.register(registerRequest));
    }

}

