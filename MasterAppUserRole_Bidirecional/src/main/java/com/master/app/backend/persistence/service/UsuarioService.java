package com.master.app.backend.persistence.service;

import com.master.app.backend.persistence.entity.Usuario;

public interface UsuarioService {
    boolean existsByCorreo(String correo);

    Usuario save(Usuario usuario);

    boolean existsByCelular(String celular);
}
