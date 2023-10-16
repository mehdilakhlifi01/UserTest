package com.userTest.app.ws.service;

import com.userTest.app.ws.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String id);
}
