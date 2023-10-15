package com.userTest.app.ws.userResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class UserResponse {
    private String userId;
    private String username;
    private Date birthdate;
    private String country;
    private String phoneNumber;
    private String gender;
}
