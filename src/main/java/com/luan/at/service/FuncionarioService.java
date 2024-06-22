package com.luan.at.service;

import java.util.List;
import java.util.Optional;

import com.luan.at.model.Funcionario;

public interface FuncionarioService {
    Funcionario save(Funcionario funcionario);

    List<Funcionario> findAll();

    Optional<Funcionario> findById(Long id);

    Optional<Funcionario> deleteById(Long id);

    Optional<Funcionario> updateById(Long id, Funcionario funcionario) throws Exception;
}
