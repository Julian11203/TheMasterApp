package com.example.demo.controller;

import com.example.demo.persistence.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role){
        if(!roleService.existsByName(role.getName())){
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(role.getName()+" ya existe!");
    }
}
