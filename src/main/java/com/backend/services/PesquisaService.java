package com.backend.services;

import java.util.ArrayList;
import java.util.Date;
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
import com.backend.repositores.ColaboradorRepository;
import com.backend.repositores.PesquisaRepository;
import com.backend.repositores.UsuarioRepository;

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
	public void cadastrarPesquisa(PesquisaDTO dto, String token) throws Exception {

		if(token == null) {
			throw new Exception("ERRO TOKEN É OBRIGATÓRIO");
		}
		
		Date date = util.extrairTempoValidade(token);
		if(date == null) {
			throw new Exception("ERRO TOKEN INVÁLIDO");
		}
		
		Usuario user = null;
		try {
			String email = util.extrairUsername(token);
			user = usuarioRepository.findByEmail(email);
		} catch (Exception e) {
			throw new Exception("ERRO TOKEN NÃO PODE SER EXTRAÍDO");
		}
		
		if(user.getTipoUsuario().equals(TipoUsuario.COLABORADOR)) {
			throw new Exception("ERRO ID NÃO É DE UM PESQUISADOR");
		}
		PesquisadorInstitucional pesquisador = (PesquisadorInstitucional) user;
		
		Long id = dto.getColaboradorId();
		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		if (colaborador.isEmpty()) {
			throw new Exception("ERRO COLABORADOR NÃO CADASTRADO");
		}
		
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setDataInicio(dto.getDataInicio());
		pesquisa.setDataTermino(dto.getDataTermino());
		pesquisa.setPesquisador(pesquisador);
		pesquisa.setInicioDoCiclo(dto.getInicioDoCiclo());
		pesquisa.setTerminoDoCiclo(dto.getTerminoDoCiclo());
		pesquisa.setStatus(Status.ATRIBUIDA);
		
		List<Colaborador> colaboradores = new ArrayList<>();
		
		colaboradores.add(colaborador.get());
		pesquisa.setColaboradores(colaboradores);

		repository.save(pesquisa);
	}

	public Page<Pesquisa> listarPorUsuario(String token, Pageable pageable) {

		if(token == null) {
			return new PageImpl<>(new ArrayList<>());
		}
		
		Date date = util.extrairTempoValidade(token);
		if(date == null) {
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
