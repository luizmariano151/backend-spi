package com.backend.model.search;

import java.time.LocalDateTime;
import java.util.Date;

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
public class AlunoSuap {

	private Long matricula;
	private String nome;
	private String campus;
	private String curso;
	private String situacaoCurso;
	private String situacaoUltimoPeriodo;
	private String nivelEnsino;
	private String modalidade;
	private Long codigoMEC;
	private Integer anoIngresso;
	private Integer periodoIngresso;
	private Integer anoConclusao;
	private String cpf;
	private String cotaMEC;
	private String cotaSistec;
	private LocalDateTime dataMatricula;
	private Date dataConclusao;
	private String turno;
	private String vinculo;
	private String DiariosMatriculados;
	
}
