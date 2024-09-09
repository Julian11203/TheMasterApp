package com.master.app.backend.repository;

import com.master.app.backend.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
