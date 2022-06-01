package com.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.backend.model.user.TipoUsuario;
import com.backend.model.user.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=3, max = 8)
	private String senha;
	
	private TipoUsuario tipoUsuario;
	
	public Usuario parser(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
		usuario.setTipoUsuario(this.tipoUsuario);
		
		return usuario;
	}
	
}
