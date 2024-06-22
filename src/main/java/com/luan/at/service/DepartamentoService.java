package com.luan.at.service;

import java.util.List;
import java.util.Optional;

import com.luan.at.model.Departamento;

public interface DepartamentoService {
    Departamento save(Departamento departamento);

    List<Departamento> findAll();

    Optional<Departamento> findById(Long id);

    Optional<Departamento> deleteById(Long id);

    Optional<Departamento> updateById(Long id, Departamento departamento) throws Exception;
}
