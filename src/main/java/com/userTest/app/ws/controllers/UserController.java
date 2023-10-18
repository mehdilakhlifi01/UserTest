package com.userTest.app.ws.controllers;

import com.userTest.app.ws.service.UserService;
import com.userTest.app.ws.shared.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_API;
import static com.userTest.app.ws.constants.ApiUrlConstant.USERS_DETAILS_API;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(USERS_API)
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO user) {
        UserDTO response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(USERS_DETAILS_API)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO response = userService.findById(id);
        return ResponseEntity.ok().body(response);
    }
}
