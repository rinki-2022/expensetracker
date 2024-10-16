package com.learnandgrow.expensetrackerapi.controller;

import com.learnandgrow.expensetrackerapi.entity.AuthModel;
import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.entity.UserModel;
import com.learnandgrow.expensetrackerapi.security.CustomUserDetailsService;
import com.learnandgrow.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody AuthModel authModel) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, authModel.getPassword(), userDetails.getAuthorities());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<String>("User has logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> save (@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}
