package com.userTest.app.ws.service;


import com.userTest.app.ws.constants.GenderEnum;
import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.repository.UserRepository;
import com.userTest.app.ws.service.mapper.UserMapper;
import com.userTest.app.ws.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImp(userRepository, userMapper);
    }

    @Test
    void shouldSaveUserThenReturnRegisteredUser() {

        String username = "Eva";
        LocalDate birthday = LocalDate.of(1990, 10, 10);
        String country = "France";
        String phone = "003358513411";
        GenderEnum gender = GenderEnum.FEMALE;
        UserEntity user = new UserEntity(1L,username, birthday, country, phone, gender);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        userDTO.setCountry(country);
        userDTO.setPhone(phone);
        userDTO.setGender(gender);

        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.mapToDTO(any())).thenReturn(userDTO);
        when(userMapper.mapToEntity(any())).thenReturn(user);

        UserDTO savedUser = userService.createUser(userDTO);

        assertNotNull(savedUser);
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getBirthday()).isEqualTo(birthday);
        assertThat(savedUser.getCountry()).isEqualTo(country);
        assertThat(savedUser.getPhone()).isEqualTo(phone);
        assertThat(savedUser.getGender()).isEqualTo(gender);
    }

    @Test
    void shouldGetUserThenReturnUserDetails() {
        Long userId = 1L;
        String username = "Eva";
        LocalDate birthday = LocalDate.of(1990, 10, 10);
        String country = "France";
        String phone = "003358513411";
        GenderEnum gender = GenderEnum.FEMALE;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        userDTO.setCountry(country);
        userDTO.setPhone(phone);
        userDTO.setGender(gender);

        when(userMapper.mapToDTO(any())).thenReturn(userDTO);

        UserDTO retrievedUser = userService.findById(userId);

        assertNotNull(retrievedUser);
        assertThat(retrievedUser.getId()).isEqualTo(userId);
        assertThat(retrievedUser.getUsername()).isEqualTo(username);
        assertThat(retrievedUser.getBirthday()).isEqualTo(birthday);
        assertThat(retrievedUser.getCountry()).isEqualTo(country);
        assertThat(retrievedUser.getPhone()).isEqualTo(phone);
        assertThat(retrievedUser.getGender()).isEqualTo(gender);
    }



}
