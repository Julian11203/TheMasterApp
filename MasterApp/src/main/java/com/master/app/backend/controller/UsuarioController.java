package com.master.app.backend.controller;

import com.master.app.backend.persistence.entity.Usuario;
import com.master.app.backend.persistence.entity.dto.UsuarioDto;
import com.master.app.backend.persistence.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        if(!usuarioService.existsByCorreo(usuario.getCorreo()) && !usuarioService.existsByCelular(usuario.getCelular())){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
        }
        if(usuarioService.existsByCorreo(usuario.getCorreo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el correo!");
        }
        if (usuarioService.existsByCelular(usuario.getCelular())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el celular!");
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<UsuarioDto> mostrarInformacionDelUsuario(@RequestBody Usuario usuario){
        if (usuarioService.existsByCorreo(usuario.getCorreo())){
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNombres(usuario.getNombres());
            usuarioDto.setApellidos(usuario.getApellidos());
            usuarioDto.setCorreo(usuario.getCorreo());
            usuarioDto.setCelular(usuario.getCelular());
            usuarioDto.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioDto.setEdad(usuario.getEdad());
            return ResponseEntity.ok(usuarioDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
