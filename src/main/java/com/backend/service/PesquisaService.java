package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.config.JWTUtil;
import com.backend.dto.PesquisaDTO;
import com.backend.model.search.Colaborador;
import com.backend.model.search.Pesquisa;
import com.backend.model.search.Status;
import com.backend.model.user.FuncionarioCCA;
import com.backend.model.user.PesquisadorInstitucional;
import com.backend.model.user.TipoUsuario;
import com.backend.model.user.Usuario;
import com.backend.repository.ColaboradorRepository;
import com.backend.repository.PesquisaRepository;
import com.backend.repository.UsuarioRepository;

@Service
public class PesquisaService {

	@Autowired
	private PesquisaRepository repository;
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private JWTUtil util;

	@Transactional
	public void cadastrarPesquisa(PesquisaDTO dto) throws Exception {

		Long id = dto.getColaboradorId();
		Optional<Usuario> cca = usuarioRepository.findById(id);
		if (cca.isEmpty()) {
			throw new Exception("ERRO COLABORADOR NÃO CADASTRADO");
		}

		FuncionarioCCA user = null;
		try {
			user = (FuncionarioCCA) cca.get();
		} catch (Exception e) {
			throw new Exception("ERRO ID FORNECIDO NÂO É DE UM COLABORADOR");
		}

		Long idPsi = dto.getPesquisadorId();
		Optional<Usuario> psi = usuarioRepository.findById(idPsi);
		if (psi.isEmpty()) {
			throw new Exception("ERRO PESQUISADOR NÃO CADASTRADO");
		}

		PesquisadorInstitucional pesquisador = null;
		try {
			pesquisador = (PesquisadorInstitucional) psi.get();
		} catch (Exception e) {
			throw new Exception("ERRO ID FORNECIDO NÂO É DE UM PESQUISADOR");
		}

		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setDataInicio(dto.getDataInicio());
		pesquisa.setDataTermino(dto.getDataTermino());
		pesquisa.setPesquisador(pesquisador);
		pesquisa.setInicioDoCiclo(dto.getInicioDoCiclo());
		pesquisa.setTerminoDoCiclo(dto.getTerminoDoCiclo());
		pesquisa.setStatus(Status.AGUARDANDO_UPLOAD_DE_ARQUIVOS);
		
		Optional<Colaborador> response = colaboradorRepository.findByFuncionarioCCA(user);
		List<Colaborador> colaboradores = new ArrayList<>();
		Colaborador colaborador = null;
		if(response.isEmpty()) {
			colaborador = new Colaborador();
			colaborador.setCampus(user.getCampus());
			colaborador.setEmail(user.getEmail());
			colaborador.setFuncionarioCCA(user);
			colaborador.setMatricula(user.getMatricula());
			colaborador.setNome(user.getNome());
			colaboradorRepository.saveAndFlush(colaborador);
		}else {
			colaborador = response.get();
		}
		
		colaboradores.add(colaborador);
		pesquisa.setColaboradores(colaboradores);
		user.setColaboradores(colaboradores);
		user.getCampus().setColaboradorPesquisas(colaboradores);

		repository.save(pesquisa);
		usuarioRepository.save(user);
	}

	public Page<Pesquisa> listarPorUsuario(String token, Pageable pageable) {

		if(token == null) {
			return new PageImpl<>(new ArrayList<>());
		}
		
		Usuario user = null;
		try {
			String email = util.extrairUsername(token);
			user = usuarioRepository.findByEmail(email);
		} catch (Exception e) {
			return new PageImpl<>(new ArrayList<>());
		}
		
		List<Pesquisa> pesquisas = null;
		if(user.getTipoUsuario().equals(TipoUsuario.COLABORADOR)) {
			FuncionarioCCA cca = (FuncionarioCCA) user;
			Optional<Colaborador> colaborador = colaboradorRepository.findByFuncionarioCCA(cca);
			if(!colaborador.isEmpty()) {
				pesquisas = repository.findByColaboradores(colaborador.get());
			}else {
				return new PageImpl<>(new ArrayList<>());
			}
		}else {
			PesquisadorInstitucional pesquisador = (PesquisadorInstitucional) user;
			pesquisas = pesquisador.getPesquisas();
		}
		
		Page<Pesquisa> page = new PageImpl<>(pesquisas,pageable,pesquisas.size());
		return page;
	}
}
