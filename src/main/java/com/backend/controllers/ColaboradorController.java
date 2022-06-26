package com.backend.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.user.FuncionarioCCA;
import com.backend.service.ColaboradorService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;
	
	@RequestMapping("/listar-por-campus")
	public ResponseEntity<List<FuncionarioCCA>> findByCampus(@PathParam("campus") String campus){
		
		List<FuncionarioCCA> colaboradores = colaboradorService.findByCampus(campus);
		
		return ResponseEntity.status(HttpStatus.OK).body(colaboradores);
	}
	
}
