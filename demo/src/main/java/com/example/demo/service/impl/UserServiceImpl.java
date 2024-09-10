package com.example.demo.service.impl;

import com.example.demo.persistence.Role;
import com.example.demo.persistence.User;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
        roleUser.ifPresent(roles::add);
        if(user.isAdmin()){
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleAdmin.ifPresent(roles::add);
        }
        user.setRole(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
