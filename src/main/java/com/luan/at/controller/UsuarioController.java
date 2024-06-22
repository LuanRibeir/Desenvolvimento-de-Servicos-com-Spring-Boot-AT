package com.luan.at.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.at.model.Usuario;
import com.luan.at.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public")
public class UsuarioController {
    final UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(usuarioService.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.updateById(id, usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
