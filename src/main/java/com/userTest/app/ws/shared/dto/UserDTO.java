package com.userTest.app.ws.shared.dto;



import com.userTest.app.ws.constants.GenderEnum;
import com.userTest.app.ws.util.exception.validation.BirthdayConstraint;
import com.userTest.app.ws.util.exception.validation.CountryConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.userTest.app.ws.constants.ErrorMessageConstant.*;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class UserDTO {

    private Long id;

    @NotNull(message = VALIDATION_MANDATORY_MSG)
    @NotBlank(message = VALIDATION_MANDATORY_MSG)
    private String username;

    @BirthdayConstraint(message = VALIDATION_BIRTHDAY_MSG)
    private LocalDate birthday;

    @CountryConstraint(message = VALIDATION_COUNTRY_MSG)
    private String country;

    private String phone;

    private GenderEnum gender;


}
