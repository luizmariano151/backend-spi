package com.backend.model.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.model.user.FuncionarioCCA;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_COLABORADOR")
public class Colaborador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "colaborador_id")
	private Long id;
	
	private Long matricula;
	
	private String nome;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "campus_id")
	@JsonBackReference
	private Campus campus;

	@ManyToOne
	@JoinColumn(name = "funcionario_cca_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private FuncionarioCCA funcionarioCCA;
	
	public Colaborador() {
	}

	@Override
	public String toString() {
		return "Colaborador [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", email=" + email
				+ ", campus=" + campus + ", funcionarioCCA=" + funcionarioCCA + "]";
	}

}

	
