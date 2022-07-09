package com.backend.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.search.Colaborador;
import com.backend.service.ColaboradorService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;
	
	@RequestMapping("/listar-por-campus")
	public ResponseEntity<List<Colaborador>> findByCampus(@PathParam("id") Long id){
		
		List<Colaborador> colaboradores = colaboradorService.findByCampus(id);
		return ResponseEntity.status(HttpStatus.OK).body(colaboradores);
		
	}
	
}
