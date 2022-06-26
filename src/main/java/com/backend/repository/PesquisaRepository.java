package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.search.Colaborador;
import com.backend.model.search.Pesquisa;

@Repository
public interface PesquisaRepository  extends JpaRepository<Pesquisa, Long>{
	
	List<Pesquisa> findByColaboradores(Colaborador colaboradores);
}
