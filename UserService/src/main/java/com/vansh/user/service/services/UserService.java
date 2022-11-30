package com.vansh.user.service.services;

import com.vansh.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUser();

    //get single user by userId
    User getUser(String userId);
}
