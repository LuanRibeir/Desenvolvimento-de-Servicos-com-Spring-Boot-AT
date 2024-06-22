package com.luan.at.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luan.at.model.Departamento;
import com.luan.at.repository.DepartamentoRepository;
import com.luan.at.service.DepartamentoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    @Override
    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public Optional<Departamento> deleteById(Long id) {
        Optional<Departamento> departamentoDeletado = departamentoRepository.findById(id);
        departamentoRepository.deleteById(id);
        return departamentoDeletado;
    }

    @Override
    public Optional<Departamento> updateById(Long id, Departamento departamento) throws Exception {

        if (!departamentoRepository.existsById(id)) {
            throw new Exception("Departamento não existe.");
        }

        departamento.setId(id);
        departamentoRepository.save(departamento);

        return departamentoRepository.findById(id);
    }

}
