package com.jefferson.desafiopicpay.controllers;

import com.jefferson.desafiopicpay.domain.User;
import com.jefferson.desafiopicpay.dtos.UserDTO;
import com.jefferson.desafiopicpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = this.userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id") Long id) throws Exception {
        var user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
