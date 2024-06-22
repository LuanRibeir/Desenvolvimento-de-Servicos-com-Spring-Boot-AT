package com.luan.at.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luan.at.model.Funcionario;
import com.luan.at.repository.FuncionarioRepository;
import com.luan.at.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public Optional<Funcionario> deleteById(Long id) {
        Optional<Funcionario> funcionarioDeletado = funcionarioRepository.findById(id);
        funcionarioRepository.deleteById(id);
        return funcionarioDeletado;
    }

    @Override
    public Optional<Funcionario> updateById(Long id, Funcionario funcionario) throws Exception {

        if (!funcionarioRepository.existsById(id)) {
            throw new Exception("Funcionario não existe.");
        }

        funcionario.setId(id);
        funcionarioRepository.save(funcionario);

        return funcionarioRepository.findById(id);
    }

}