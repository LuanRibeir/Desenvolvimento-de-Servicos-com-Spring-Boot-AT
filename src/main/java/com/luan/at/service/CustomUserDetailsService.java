package com.luan.at.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luan.at.model.Usuario;
import com.luan.at.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não cadastrado.");
        }
        return new User(usuario.getNome(), usuario.getSenha(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getPapel())));
    }
}
