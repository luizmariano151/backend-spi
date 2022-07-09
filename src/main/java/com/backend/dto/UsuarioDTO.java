package com.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
	
	@NotNull(message = " ID DO CAMPUS É OBRIGATÓRIO")
	private Long campus;
	
}
