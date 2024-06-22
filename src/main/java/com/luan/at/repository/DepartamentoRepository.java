package com.luan.at.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.at.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
