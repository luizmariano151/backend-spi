package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.search.Campus;
import com.backend.model.search.Colaborador;
import com.backend.repository.CampusRepository;
import com.backend.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private CampusRepository campusRepository;
	@Autowired
	private ColaboradorRepository repository;
	
	public List<Colaborador> findByCampus(String nome){
		
		Optional<Campus> campus = campusRepository.findByNome(nome);
		
		if(!campus.isEmpty()) {
			Optional<List<Colaborador>> colaboradores = repository.findByCampus(campus.get());
			if(!colaboradores.isEmpty()) {
				return colaboradores.get();
			}
		}
		return new ArrayList<>();
	}
	
	
}
