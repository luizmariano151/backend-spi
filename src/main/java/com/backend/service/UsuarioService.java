package com.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.UsuarioDTO;
import com.backend.model.search.Campus;
import com.backend.model.search.Colaborador;
import com.backend.model.user.FuncionarioCCA;
import com.backend.model.user.Role;
import com.backend.model.user.TipoUsuario;
import com.backend.model.user.Usuario;
import com.backend.model.user.Solicitacao;
import com.backend.repository.CampusRepository;
import com.backend.repository.ColaboradorRepository;
import com.backend.repository.RoleRepository;
import com.backend.repository.UsuarioRepository;
import com.backend.repository.SolicitacaoRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CampusRepository campusRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public String buscarPerfil(String email) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		return new ArrayList<Role>(usuario.getRoles()).get(0).getRole();
	
	}

	public void solicitarCadastro(UsuarioDTO usuarioDTO) throws Exception{
		
		Optional<Campus> getCampus = campusRepository.findById(usuarioDTO.getCampus());
		if(getCampus.isEmpty()) {
			throw new Exception("CAMPUS NÃO ENCONTRADO");
		}

		Solicitacao solicitacao = new Solicitacao();
		FuncionarioCCA usuario = new FuncionarioCCA();
		
		usuario.setTipoUsuario(TipoUsuario.COLABORADOR);
		usuario.setNome(usuarioDTO.getNome());
		usuario.setMatricula(usuarioDTO.getMatricula());
		usuario.setCpf(usuarioDTO.getCpf());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setCampus(getCampus.get());
		
		Optional<Role> buscarRole = roleRepository.findByRole("COLABORADOR");
		if (buscarRole.isEmpty()) {
			throw new Exception("PERFIL DO USUÁRIO NÃO ESTÁ CADASTRADO NO BANCO DE DADOS");
		}
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(buscarRole.get());
		usuario.setRoles(roles);
		
		try {			
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new Exception("ERRO AO SALVAR USUÁRIO");
		}
		
		solicitacao.setUsuario(usuario);
		solicitacaoRepository.save(solicitacao);
	}

	public Page<Solicitacao> solicitacoesUsuarios(Pageable pageable) {
		
		Page<Solicitacao> page = solicitacaoRepository.findAll(pageable);
		return page;
		
	}

	public boolean aceitarSolicitacao(Long id) {
		
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
		if(!solicitacao.isEmpty()) {
			
			FuncionarioCCA usuario = solicitacao.get().getUsuario();
			List<FuncionarioCCA> ccas = new ArrayList<>();
			ccas.add(usuario);
			Campus campus = usuario.getCampus();
			campus.setFuncionarioCCAs(ccas);
			
			Colaborador colaborador = new Colaborador();
			colaborador.setCampus(usuario.getCampus());
			colaborador.setEmail(usuario.getEmail());
			colaborador.setFuncionarioCCA(usuario);
			colaborador.setMatricula(usuario.getMatricula());
			colaborador.setNome(usuario.getNome());
			
			colaboradorRepository.save(colaborador);
			usuarioRepository.save((Usuario) usuario);
			campusRepository.save(campus);
			solicitacaoRepository.delete(solicitacao.get());
			return true;
		}
		return false;
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
