package com.example.demo.service;

import com.example.demo.persistence.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role save(Role role);

    boolean existsByName(String name);
}
