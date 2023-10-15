package com.userTest.app.ws.userRequest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
@Getter @Setter
public class UserRequest {
    private String userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate should be in the past")
    private Date birthdate;
    @NotBlank(message = "Country is required")
    private String country;
    private String phoneNumber;
    private String gender;
}
