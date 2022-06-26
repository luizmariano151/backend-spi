package com.backend.model.search;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CNPJ;

import com.backend.model.user.FuncionarioCCA;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CAMPUS")
public class Campus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "campus")
	private List<FuncionarioCCA> funcionarioCCAs;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "campus")
	@JsonManagedReference
	private List<Colaborador> colaboradorPesquisas;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 10)
	private String sigla;
	
	@CNPJ
	private String cnpj;
	
	@Column(length = 100)
	private String endereco;
	
	@Column(length = 50)
	private String municipioNome;
	
	@Column(length = 2)
	private String municipioUf;
	
	@Column(length = 30)
	private String cep; 
	
	private String telefone;
	
	public Campus() {
	}

	@Override
	public String toString() {
		return "Campus [id=" + id + ", funcionarioCCAs=" + funcionarioCCAs + ", colaboradorPesquisas="
				+ colaboradorPesquisas + ", nome=" + nome + ", sigla=" + sigla + ", cnpj=" + cnpj + ", endereco="
				+ endereco + ", municipioNome=" + municipioNome + ", municipioUf=" + municipioUf + ", cep=" + cep
				+ ", telefone=" + telefone + "]";
	}

}
