package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.search.Colaborador;
import com.backend.model.user.FuncionarioCCA;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	Optional<Colaborador> findByFuncionarioCCA(FuncionarioCCA funcionarioCCA);
}
