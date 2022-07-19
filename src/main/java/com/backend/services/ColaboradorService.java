package com.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.search.Campus;
import com.backend.model.search.Colaborador;
import com.backend.repositores.CampusRepository;
import com.backend.repositores.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private CampusRepository campusRepository;
	@Autowired
	private ColaboradorRepository repository;
	
	public List<Colaborador> findByCampus(Long id){
		
		Optional<Campus> campus = campusRepository.findById(id);
		if(!campus.isEmpty()) {
			Optional<List<Colaborador>> colaboradores = repository.findByCampus(campus.get());
			if(!colaboradores.isEmpty()) {
				return colaboradores.get();
			}
		}
		return new ArrayList<>();
		
	}
	
}
