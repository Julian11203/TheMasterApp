package com.master.app.backend.persistence.service;

import com.master.app.backend.persistence.entity.Role;

import java.util.List;

public interface RoleService {
    boolean existsByName(String name);

    List<Role> findAll();

    Role save(Role role);
}
