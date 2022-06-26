package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.search.Campus;
import com.backend.model.user.FuncionarioCCA;
import com.backend.repository.CampusRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private CampusRepository campusRepository;
	
	public List<FuncionarioCCA> findByCampus(String nome){
		
		Optional<Campus> campus = campusRepository.findByNome(nome);
		
		if(!campus.isEmpty()) {
			return campus.get().getFuncionarioCCAs();
		}
		return new ArrayList<>();
	}
	
	
}
