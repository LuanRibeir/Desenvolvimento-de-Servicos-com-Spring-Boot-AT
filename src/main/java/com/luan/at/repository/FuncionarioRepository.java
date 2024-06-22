package com.luan.at.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.at.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
