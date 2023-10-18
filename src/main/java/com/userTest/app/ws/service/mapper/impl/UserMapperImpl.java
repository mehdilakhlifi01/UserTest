package com.userTest.app.ws.service.mapper.impl;


import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.service.mapper.UserMapper;
import com.userTest.app.ws.shared.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapToDTO(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserEntity mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
