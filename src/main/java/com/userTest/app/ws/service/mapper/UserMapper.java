package com.userTest.app.ws.service.mapper;

import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.shared.dto.UserDTO;


public interface UserMapper {

    UserDTO mapToDTO(UserEntity user);

    UserEntity mapToEntity(UserDTO userDTO);

}
