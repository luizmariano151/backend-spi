package com.backend.model.search;

import java.time.LocalDateTime;

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
public class AlunoSistec {

	private Long coAlunoIdentificado;
	private Long coAluno;
	private String nomeAluno;
	private String sexo;
	private LocalDateTime dataNascimento;
	private String cpf;
	private Long coPessoaFisica;
	private String dsSenha;
	private Long coMatricula;
	private Long coCicloMatricula;
	private String coCurso;
	private Integer cargaHoraria;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFimPrevisto;
	private Integer coPeriodoCadastrado;
	private Integer coTipo;
	private Integer coTipoInstituicao;
	private Long coPortfolio;
	private Integer nuVagasOfertadas;
	private Integer nuTotalInscritos;
	private String statusMatricula;
	private Long coUnidadeEnsino;
	private String mesDeOcorrencia;

}
