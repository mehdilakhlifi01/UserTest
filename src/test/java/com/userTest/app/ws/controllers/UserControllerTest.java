package com.userTest.app.ws.controllers;

import com.userTest.app.ws.service.UserService;
import com.userTest.app.ws.shared.dto.UserDto;
import com.userTest.app.ws.userRequest.UserRequest;
import com.userTest.app.ws.userResponse.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testRegisterUser() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("elmehdi");
        userRequest.setBirthdate(new Date());
        userRequest.setCountry("France");

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setUsername(userRequest.getUsername());
        expectedUserDto.setBirthdate(userRequest.getBirthdate());
        expectedUserDto.setCountry(userRequest.getCountry());

        when(userService.createUser(any(UserDto.class))).thenReturn(expectedUserDto);

        ResponseEntity<UserResponse> response = userController.registerUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        UserResponse userResponse = response.getBody();
        assertNotNull(userResponse);
        assertEquals(expectedUserDto.getUsername(), userResponse.getUsername());
        assertEquals(expectedUserDto.getBirthdate(), userResponse.getBirthdate());
        assertEquals(expectedUserDto.getCountry(), userResponse.getCountry());
    }

    @Test
    public void testGetUserDetails() {
        String userId = "123";

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setUserId(userId);
        expectedUserDto.setUsername("elmehdi");
        expectedUserDto.setBirthdate(new Date());
        expectedUserDto.setCountry("France");

        when(userService.getUserById(userId)).thenReturn(expectedUserDto);

        ResponseEntity<UserResponse> response = userController.getUserDetails(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserResponse userResponse = response.getBody();
        assertNotNull(userResponse);
        assertEquals(expectedUserDto.getUserId(), userResponse.getUserId());
        assertEquals(expectedUserDto.getUsername(), userResponse.getUsername());
        assertEquals(expectedUserDto.getBirthdate(), userResponse.getBirthdate());
        assertEquals(expectedUserDto.getCountry(), userResponse.getCountry());
    }
}