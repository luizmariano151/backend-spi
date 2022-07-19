package com.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.PesquisaDTO;
import com.backend.model.search.Pesquisa;
import com.backend.services.PesquisaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

	@Autowired
	private PesquisaService pesquisaService;
	
	@PostMapping("cadastrar-pesquisa")
	public ResponseEntity<String> cadastrarPesquisa(@Valid @RequestBody PesquisaDTO dto, BindingResult bindingResult, @PathParam("token") String token) throws JsonProcessingException{
		
		if(bindingResult.hasErrors()) {
			List<FieldError> erros = bindingResult.getFieldErrors();
			List<String> message = new ArrayList<>();
			
			for (FieldError erro : erros) {
				message.add(erro.getDefaultMessage());
			}
			
			String resposta = new ObjectMapper().writeValueAsString(message);
			return ResponseEntity.status(
					HttpStatus.UNAUTHORIZED).body(resposta);
		}
		
		try {
			pesquisaService.cadastrarPesquisa(dto, token);
		} catch (Exception e) {
			return ResponseEntity.status(
					HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("PESQUISA SALVA COM SUCESSO!");
	}
	
	@GetMapping("/listar-por-usuario")
	public ResponseEntity<Page<Pesquisa>> listarPorUsuario(@PathParam("token") String token, Pageable pageable) {
		
		Page<Pesquisa> pesquisas = pesquisaService.listarPorUsuario(token, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pesquisas);
		
	}
}
