package com.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SuapDTO {

	private String nome;
	private String curso;
	private String situacaoCurso;
	private String situacaoUltimoPeriodo;
	private String nivelEnsino;
	private String modalidade;
	private Long codigoEMEC;
	private Integer anoIngresso;
	private Integer periodoIngresso;
	private Integer anoConclusao;
	private String cpf;
	private String cotaMEC;
	private String cotaSISTEC;
	private LocalDate dataMatricula;
	private LocalDate dataConclusao;
	private String turno;
	private String vinculo;
	private List<Integer> diariosMatriculadosUltimoPeriodo;
	
}
