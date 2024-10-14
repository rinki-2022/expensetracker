package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.entity.UserModel;
import com.learnandgrow.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user) {
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }
}
