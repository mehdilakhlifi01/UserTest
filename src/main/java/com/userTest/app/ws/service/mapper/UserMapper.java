package com.userTest.app.ws.service.mapper;

import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.shared.dto.UserDTO;

/**
 * This is a user mapper interface, that contains method definition of mapping between user DTOs and Entities.
 */
public interface UserMapper {

    UserDTO mapToDTO(UserEntity user);

    UserEntity mapToEntity(UserDTO userDTO);

}
