package com.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.backend.model.user.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

	@Size(min = 4, max = 100, message = "NOME DEVE TER ENTRE 4 E 100 CARACTERES")
	@NotBlank(message = "NOME É OBRIGATÓRIO")
	private String nome;
	
	@NotNull(message = "MATRÍCULA É OBRIGATÓRIA")
	private Long matricula;
	
	@CPF(message = "CPF INVÁLIDO")
	@NotBlank(message = "CPF É OBRIGATÓRIO")
	private String cpf;
	
	@NotBlank(message = "EMAIL É OBRIGATÓRIO")
	@Email(message = "EMAIL INVÁLIDO")
	private String email;
	
	public Usuario parser(){
		
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setMatricula(this.matricula);
		usuario.setCpf(this.cpf);
		usuario.setEmail(this.email);
		
		return usuario;
	}	
}
