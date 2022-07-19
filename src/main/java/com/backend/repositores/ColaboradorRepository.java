package com.backend.repositores;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.search.Campus;
import com.backend.model.search.Colaborador;
import com.backend.model.user.FuncionarioCCA;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	Optional<Colaborador> findByFuncionarioCCA(FuncionarioCCA funcionarioCCA);
	
	Optional<List<Colaborador>> findByCampus(Campus campus);
	
}
