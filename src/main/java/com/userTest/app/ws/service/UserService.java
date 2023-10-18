package com.userTest.app.ws.service;

import com.userTest.app.ws.shared.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO findById(Long id);
}
