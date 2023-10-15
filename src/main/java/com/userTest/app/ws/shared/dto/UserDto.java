package com.userTest.app.ws.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
public class UserDto implements Serializable {
    private String userId;
    private String username;
    private Date birthdate;
    private String country;
    private String phoneNumber;
    private String gender;
}
