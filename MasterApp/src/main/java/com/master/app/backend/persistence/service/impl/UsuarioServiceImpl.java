package com.master.app.backend.persistence.service.impl;

import com.master.app.backend.persistence.entity.Usuario;
import com.master.app.backend.persistence.service.UsuarioService;
import com.master.app.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByCelular(String celular) {
        return usuarioRepository.existsByCelular(celular);
    }
}
