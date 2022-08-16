package com.backend.model.search;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_ALUNO")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CPF
	private String CPF;

	private String nome;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@Column(columnDefinition = "DATE")
	private LocalDate dataMatricula;
}
