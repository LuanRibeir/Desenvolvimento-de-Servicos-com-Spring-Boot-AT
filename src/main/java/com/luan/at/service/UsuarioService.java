package com.luan.at.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.luan.at.model.Usuario;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findById(String id);

    ResponseEntity<?> deleteById(String id);

    Optional<Usuario> updateById(String id, Usuario usuario) throws Exception;

    Usuario findByName(String nome);
}
