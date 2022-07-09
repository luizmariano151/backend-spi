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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.JWTUtil;
import com.backend.dto.AuthDTO;
import com.backend.dto.UsuarioDTO;
import com.backend.model.user.UsuarioCredenciais;
import com.backend.model.user.Solicitacao;
import com.backend.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
		} catch (Exception error) {
			return "CREDENCIAIS INVÁLIDAS!";
		}

		String profile = usuarioService.buscarPerfil(userCredentials.getEmail());
		String token = jwtUtil.gerarToken(userCredentials.getEmail());

		return mapper.writeValueAsString(new AuthDTO(token, profile));
	}

	@PostMapping("/solicitar-cadastro")
	public ResponseEntity<String> solicitarCadastroColaborador(@Valid @RequestBody UsuarioDTO usuario,
			BindingResult bindingResult) throws JsonProcessingException {

		if (bindingResult.hasErrors()) {
			
			List<FieldError> erros = bindingResult.getFieldErrors();
			List<String> message = new ArrayList<>();
			
			for (FieldError erro : erros) {
				message.add(erro.getDefaultMessage());
			}
			
			String resposta = new ObjectMapper().writeValueAsString(message);
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(resposta);
		}

		try {
			usuarioService.solicitarCadastro(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}

		return ResponseEntity.ok().body("SOLICITAÇÃO REALIZADA COM SUCESSO!");
	}

	@GetMapping("/listar-solicitacoes")
	public ResponseEntity<Page<Solicitacao>> listarSolicitacoesUsuarios(Pageable pageable) {

		Page<Solicitacao> solicitacoes = usuarioService.solicitacoesUsuarios(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(solicitacoes);

	}

	@PostMapping("/aceitar-solicitacao")
	public ResponseEntity<Boolean> aceitarSolicitacao(@PathParam("id") Long id) {

		Boolean valid = usuarioService.aceitarSolicitacao(id);
		if (!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

}
