package com.master.app.backend.persistence.service.impl;

import com.master.app.backend.persistence.entity.Role;
import com.master.app.backend.persistence.service.RoleService;
import com.master.app.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
