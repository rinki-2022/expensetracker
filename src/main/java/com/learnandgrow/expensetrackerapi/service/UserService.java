package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser();
    User updateUser(UserModel user);
    void deleteUser();
    User getLoggedInUser();
}
