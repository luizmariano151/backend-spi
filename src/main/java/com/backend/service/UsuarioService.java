package com.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.UsuarioDTO;
import com.backend.model.user.PesquisadorInstitucional;
import com.backend.model.user.Role;
import com.backend.model.user.TipoUsuario;
import com.backend.model.user.Usuario;
import com.backend.repository.RoleRepository;
import com.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired 
	private RoleRepository roleRepository;

	public String buscarPerfil(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		return new ArrayList<Role>(usuario.getRoles()).get(0).getRole();
	}
	
	public void cadastrar(UsuarioDTO usuarioDTO) {

		Usuario usuarioParse = usuarioDTO.parser();
		Role role = new Role();
		Usuario usuario = null;
		
		
		if(usuarioParse.getTipoUsuario().equals(TipoUsuario.PESQUISADOR)) {
			usuario = new PesquisadorInstitucional();
			role.setRole("PESQUISADOR");			
			usuario.setEmail(usuarioParse.getEmail());
			usuario.setSenha(usuarioParse.getSenha());
			usuario.setTipoUsuario(TipoUsuario.PESQUISADOR);
		}
		
		Optional<Role> buscarRole = roleRepository.findByRole(role.getRole());
		
		if(buscarRole.isEmpty()) {			
			roleRepository.save(role);
		}else {
			role = buscarRole.get();
		}
				
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		
		usuario.setRoles(roles);
		usuarioRepository.save(usuario);
	}
}
