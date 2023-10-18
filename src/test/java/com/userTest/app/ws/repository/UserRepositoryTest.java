package com.userTest.app.ws.repository;

import com.userTest.app.ws.constants.GenderEnum;
import com.userTest.app.ws.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryTest {

   /* @Autowired
    private UserRepository userRepository;

    private UserEntity user;

    @BeforeEach
    public void setUp() {
        String username = "Mikel";
        LocalDate birthday = LocalDate.of(1984, 1, 23);
        String country = "France";
        String phone = "0033582446557";
        GenderEnum gender = GenderEnum.MALE;
        user = new UserEntity(1L,username, birthday, country, phone, gender);
        userRepository.save(user);
    }

    @AfterEach
    public void clear() {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    public void givenUserToAddShouldReturnAddedUser() {
        // arrange
        String username = "Jack";
        LocalDate birthday = LocalDate.of(1994, 11, 11);
        String country = "France";
        String phone = "0033582446557";
        GenderEnum gender = GenderEnum.MALE;
        UserEntity user = new UserEntity(1L,username, birthday, country, phone, gender);
        // act
        UserEntity savedUser = userRepository.save(user);
        // assert
        assertNotNull(savedUser);
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getBirthday()).isEqualTo(birthday);
        assertThat(savedUser.getCountry()).isEqualTo(country);
        assertThat(savedUser.getPhone()).isEqualTo(phone);
        assertThat(savedUser.getGender()).isEqualTo(gender);
    }

    @Test
    public void givenUserIdShouldReturnUserDetails() {
        // arrange
        Long userId = 1L;
        // act
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        // assert
        assertTrue(userOptional.isPresent());
        assertThat(userOptional.get().getUsername()).isEqualTo(user.getUsername());
        assertThat(userOptional.get().getBirthday()).isEqualTo(user.getBirthday());
        assertThat(userOptional.get().getCountry()).isEqualTo(user.getCountry());
        assertThat(userOptional.get().getPhone()).isEqualTo(user.getPhone());
        assertThat(userOptional.get().getGender()).isEqualTo(user.getGender());
    }*/

}