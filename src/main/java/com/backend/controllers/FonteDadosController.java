package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.services.FonteDadosService;

@RestController
@RequestMapping("/fonte-dados")
public class FonteDadosController {
	
	@Autowired
	private FonteDadosService service;

	@PostMapping("/extrair-dados")
	public ResponseEntity<Boolean> extrairDados(
			@RequestParam("sistec") MultipartFile sistec,
			@RequestParam("suap") MultipartFile suap) {

		HttpStatus erro = HttpStatus.EXPECTATION_FAILED;
		try {
			Boolean response = service.extrairDados(sistec, suap);
			ResponseEntity<Boolean> retorno; 
			retorno = response == true ? 
				ResponseEntity.status(HttpStatus.CREATED).body(true) : 
				ResponseEntity.status(erro).body(false); 
			return retorno;
		} catch (Exception e) {
			return ResponseEntity.status(erro).body(false);
		}
	}
	
}
