package com.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.JWTUtil;
import com.backend.dto.AuthDTO;
import com.backend.dto.UsuarioDTO;
import com.backend.model.user.UsuarioCredenciais;
import com.backend.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	@Autowired 
	private UsuarioService usuarioService;
	@Autowired 
	private JWTUtil jwtUtil;
	@Autowired 
	private ObjectMapper mapper;
	
	@PostMapping("/autenticar")
	public String autenticar(@RequestBody UsuarioCredenciais userCredentials) throws Exception {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getSenha()));
		} catch(Exception error) {
			throw new Exception("CREDENCIAIS INVÁLIDAS!");
		}
		
		String profile = usuarioService.buscarPerfil(userCredentials.getEmail());
		String token = jwtUtil.gerarToken(userCredentials.getEmail());
		
		
		return mapper.writeValueAsString(new AuthDTO(token, profile));
	}
	
	@PostMapping("/cadastrar") 
	public void cadastrar(@Valid @RequestBody UsuarioDTO usuario, BindingResult bindingResult) throws Exception {

		if(bindingResult.hasErrors()) {
			throw new Exception("NÃO FOI POSSÍVEL CADASTRAR O USUÁRIO");
		}
		
		usuarioService.cadastrar(usuario);
	}
}
