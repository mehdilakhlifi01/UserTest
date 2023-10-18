package com.userTest.app.ws.controllers;

import com.userTest.app.ws.constants.GenderEnum;
import com.userTest.app.ws.service.UserService;
import com.userTest.app.ws.shared.dto.UserDTO;
import com.userTest.app.ws.util.exception.JsonUtils;
import com.userTest.app.ws.util.exception.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_API;
import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_DETAILS_API;
import static com.userTest.app.ws.constants.ErrorMessageConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void makeUserRegistrationShouldRegisterUser() throws Exception {
        Long userId = 1L;
        String username = "john";
        LocalDate birthday = LocalDate.of(1990, 2, 2);
        String country = "France";
        String phone = "0033143485548";
        GenderEnum gender = GenderEnum.MALE;

        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        given(userService.createUser(any())).willReturn(user);
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername(username);
        userToSave.setBirthday(birthday);
        userToSave.setCountry(country);
        userToSave.setPhone(phone);
        userToSave.setGender(gender);
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.birthday").value(birthday.toString()))
                .andExpect(jsonPath("$.country").value(country))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.gender").value(gender.name()));
    }

    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenMissingRequiredAttributes() throws Exception {
        UserDTO userToSave = new UserDTO();
        userToSave.setPhone("0033143396693");
        userToSave.setGender(GenderEnum.FEMALE);
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.username").value(VALIDATION_MANDATORY_MSG))
                .andExpect(jsonPath("$.metaData.birthday").value(VALIDATION_BIRTHDAY_MSG))
                .andExpect(jsonPath("$.metaData.country").value(VALIDATION_COUNTRY_MSG));
    }


    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenPassingInvalidCountry() throws Exception {
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername("Josh");
        userToSave.setBirthday(LocalDate.of(1990, 2, 2));
        userToSave.setCountry("Allmagne");
        userToSave.setPhone("0033589396693");
        userToSave.setGender(GenderEnum.MALE);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.country").value(VALIDATION_COUNTRY_MSG));
    }

    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenPassingInvalidBirthday() throws Exception {
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername("Josh");
        userToSave.setBirthday(LocalDate.of(2015, 2, 2));
        userToSave.setCountry("France");
        userToSave.setPhone("0033589396693");
        userToSave.setGender(GenderEnum.MALE);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.birthday").value(VALIDATION_BIRTHDAY_MSG));
    }

    @Test
    public void getUserShouldReturnsUserDetails() throws Exception {
        Long userId = 1L;
        String username = "john";
        LocalDate birthday = LocalDate.of(1990, 2, 2);
        String country = "France";
        String phone = "0033143485548";
        GenderEnum gender = GenderEnum.MALE;

        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        given(userService.findById(any())).willReturn(user);
        // invoke and check the received response
        mockMvc.perform(get(USERS_DETAILS_API, userId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.birthday").value(birthday.toString()))
                .andExpect(jsonPath("$.country").value(country))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.gender").value(gender.name()));
    }

    @Test
    public void getUserShouldReturnsNotFoundErrorWhenPassingUnknownUserId() throws Exception {
        long userId = 1152;

        given(userService.findById(any())).willThrow(UserNotFoundException.class);

        mockMvc.perform(get(USERS_DETAILS_API, userId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(DATA_NOT_FOUND_ERROR_MSG));
    }
}