package com.userTest.app.ws.controllers;

import com.userTest.app.ws.service.UserService;
import com.userTest.app.ws.shared.dto.UserDto;
import com.userTest.app.ws.userRequest.UserRequest;
import com.userTest.app.ws.userResponse.UserResponse;
import com.userTest.app.ws.util.exception.ErrorMessages;
import com.userTest.app.ws.util.exception.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {
        if(userRequest.getUsername().isEmpty()) throw new UserException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userRequest,userDto);
        UserDto createUser=userService.createUser(userDto);
        UserResponse userResponse=new UserResponse();
        BeanUtils.copyProperties(createUser,userResponse);
        return  new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String id) {
        UserDto userDto=userService.getUserById(id);
        UserResponse userResponse=new UserResponse();
        BeanUtils.copyProperties(userDto,userResponse);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
