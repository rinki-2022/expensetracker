package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser(Long id);
    User updateUser(UserModel user, Long id);
    void deleteUser(Long id);
}
