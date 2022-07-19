package com.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.SistecDTO;
import com.backend.dto.SuapDTO;

@RestController
@RequestMapping("/fonte-dados")
public class FonteDadosController {
	
	@PostMapping("/extrair-dados")
	public ResponseEntity<Boolean> extrairDados(
			@RequestBody(required = true) List<SuapDTO> suapDTOs, 
			@RequestBody(required = true) List<SistecDTO> sistecDTOs){
		
		System.out.println("Arquivos SUAP");
		for (SuapDTO dto : suapDTOs) {
			System.out.println(dto);
		}
		
		System.out.println("/nArquivos SISTEC");
		for (SistecDTO dto : sistecDTOs) {
			System.out.println(dto);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
