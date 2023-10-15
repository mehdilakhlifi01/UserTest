package com.userTest.app.ws.service;

import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.repository.UserRepository;
import com.userTest.app.ws.shared.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("elmehdi");
        userDto.setBirthdate(new Date());
        userDto.setCountry("France");

        when(userRepository.findByUsername(userDto.getUsername())).thenReturn(null);

        UserEntity savedUserEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, savedUserEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        UserDto createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
    }

    @Test
    public void testGetUserById() {
        String userId = "123";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);

        when(userRepository.findByUserId(userId)).thenReturn(userEntity);

        UserDto retrievedUser = userService.getUserById(userId);

        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getUserId());
    }
}