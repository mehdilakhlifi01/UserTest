package com.userTest.app.ws;

import com.userTest.app.ws.constants.GenderEnum;
import com.userTest.app.ws.shared.dto.UserDTO;
import com.userTest.app.ws.util.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_API;
import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_DETAILS_API;
import static com.userTest.app.ws.constants.ErrorMessageConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationTests {


	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void makeUserRegistrationReturnsRegisteredUser() {
		// arrange
		String username = "Aurielne";
		LocalDate birthday =  LocalDate.of(1980, 6, 20);
		String country = "France";
		String phone = "003358513411";
		GenderEnum gender = GenderEnum.MALE;
		UserDTO user = new UserDTO();
		user.setUsername(username);
		user.setBirthday(birthday);
		user.setCountry(country);
		user.setPhone(phone);
		user.setGender(gender);
		// act
		ResponseEntity<UserDTO> response = testRestTemplate.postForEntity(USERS_API, user, UserDTO.class);
		// assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getId()).isNotNull();
		assertThat(response.getBody().getUsername()).isEqualTo(username);
		assertThat(response.getBody().getBirthday()).isEqualTo(birthday);
		assertThat(response.getBody().getCountry()).isEqualTo(country);
		assertThat(response.getBody().getPhone()).isEqualTo(phone);
		assertThat(response.getBody().getGender()).isEqualTo(gender);
	}


	@Test
	void makeUserRegistrationReturnsBadRequestWhenMissingRequiredAttributes() {
		// arrange
		String phone = "003358513411";
		GenderEnum gender = GenderEnum.FEMALE;
		UserDTO user = new UserDTO();
		user.setPhone(phone);
		user.setGender(gender);

		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USERS_API, user, ErrorResponse.class);

		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void makeUserRegistrationReturnsConflictWhenUserAlreadyExist() {

		String username = "Aurielne";
		LocalDate birthday =  LocalDate.of(1980, 6, 20);
		String country = "France";
		String phone = "003358513411";
		GenderEnum gender = GenderEnum.MALE;
		UserDTO user = new UserDTO();
		user.setUsername(username);
		user.setBirthday(birthday);
		user.setCountry(country);
		user.setPhone(phone);
		user.setGender(gender);

		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USERS_API, user, ErrorResponse.class);

		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void getUserReturnsUserDetails() {

		long userId = 1;

		ResponseEntity<UserDTO> response = testRestTemplate.getForEntity(USERS_DETAILS_API, UserDTO.class, userId);

		assertThat(response.getBody()).isNotNull();
	}


	@Test
	void getUserByIdReturnsNotFoundWhenUserNotFound() {

		long userId = 1152;

		ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(USERS_DETAILS_API, ErrorResponse.class, userId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getMessage()).isNotNull();
		assertThat(response.getBody().getMessage()).isEqualTo(DATA_NOT_FOUND_ERROR_MSG);
	}
}
