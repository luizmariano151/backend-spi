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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_CAMPUS")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Campus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "campus")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<FuncionarioCCA> funcionarioCCAs;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "campus")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

}
