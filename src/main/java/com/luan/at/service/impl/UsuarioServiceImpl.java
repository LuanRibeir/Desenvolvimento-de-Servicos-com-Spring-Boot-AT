package com.luan.at.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luan.at.model.Usuario;
import com.luan.at.repository.UsuarioRepository;
import com.luan.at.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuarioRepository.delete(usuario);
        return ResponseEntity.ok().build();
    }

    @Override
    public Optional<Usuario> updateById(String id, Usuario usuario) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("Usuário não existe.");
        }

        usuario.setId(id);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByName(String nome) {
        return usuarioRepository.findByNome(nome);
    }
}
