package com.luan.at.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luan.at.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByNome(String nome);
}
