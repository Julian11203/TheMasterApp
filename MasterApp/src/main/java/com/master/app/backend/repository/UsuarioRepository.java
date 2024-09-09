package com.master.app.backend.repository;

import com.master.app.backend.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);

    boolean existsByCelular(String celular);
}
