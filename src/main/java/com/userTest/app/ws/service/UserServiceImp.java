package com.userTest.app.ws.service;

import com.userTest.app.ws.repository.UserRepository;
import com.userTest.app.ws.service.mapper.UserMapper;
import com.userTest.app.ws.shared.dto.UserDTO;

import com.userTest.app.ws.util.exception.exception.UserNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return userMapper.mapToDTO(
                userRepository.save(userMapper.mapToEntity(userDTO))
        );
    }

    @Override
    public UserDTO findById(Long id) {
        UserDTO userDTO;

        try {
            userDTO = userMapper.mapToDTO(userRepository.getById(id));
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }

        return userDTO;
    }
}
