package com.learnandgrow.expensetrackerapi.controller;

import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.entity.UserModel;
import com.learnandgrow.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save (@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> readUser (@Valid @PathVariable Long id) {
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser ( @RequestBody UserModel user, @PathVariable Long id) {
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }
}
