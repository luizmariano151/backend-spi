package com.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.search.Campus;
import com.backend.repositores.CampusRepository;

@Service
public class CampusService {

	@Autowired
	private CampusRepository repository;
	
	public List<Campus> findAll(){
		List<Campus> campus = repository.findAll();
		if(!campus.isEmpty()) {
			return campus;
		}
		return new ArrayList<>();
	}
}
