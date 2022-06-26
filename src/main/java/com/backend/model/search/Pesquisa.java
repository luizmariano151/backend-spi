package com.backend.model.search;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.model.user.PesquisadorInstitucional;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PESQUISA")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Pesquisa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "pesquisador_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private PesquisadorInstitucional pesquisador;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Colaborador> colaboradores;

	@Column(columnDefinition = "DATE")
	private LocalDate dataInicio;

	@Column(columnDefinition = "DATE")
	private LocalDate dataTermino;

	@Column(columnDefinition = "DATE")
	private LocalDate inicioDoCiclo;

	@Column(columnDefinition = "DATE")
	private LocalDate terminoDoCiclo;

	public Pesquisa() {
	}

	@Override
	public String toString() {
		return "Pesquisa [id=" + id + ", status=" + status + ", pesquisador=" + pesquisador + ", colaboradores="
				+ colaboradores + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", inicioDoCiclo="
				+ inicioDoCiclo + ", terminoDoCiclo=" + terminoDoCiclo + "]";
	}
}
