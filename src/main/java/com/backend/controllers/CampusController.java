package com.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.search.Campus;
import com.backend.service.CampusService;

@RestController
@RequestMapping("/campus")
public class CampusController {
	
	@Autowired
	private CampusService service;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Campus>> listarCampus(){
		List<Campus> campus = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(campus);
	}
	
}
