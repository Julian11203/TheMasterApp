package com.master.app.backend.persistence.service;

import com.master.app.backend.persistence.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);

}
