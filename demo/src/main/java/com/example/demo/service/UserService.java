package com.example.demo.service;

import com.example.demo.persistence.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);
}
