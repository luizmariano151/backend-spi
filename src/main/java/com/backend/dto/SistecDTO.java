package com.backend.dto;

import java.time.LocalDate;

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
public class SistecDTO {

	private String uf;
	private String municipio;
	private String unidadeEnsino;
	private Long codigoUnidadeEnsino;
	private Long codigoInstituicao;
	private String subtipoCurso;
	private Long codigoCurso;
	private String curso;
	private Integer cargaHorariaMinima;
	private String oferta;
	private Long codigoCicloMatricula;
	private String cicloMatricula;
	private Integer cargaHorariaTotal;
	private LocalDate dataInicioCurso;
	private LocalDate dataFimPrevistoCurso;
	private String modalidadeEnsino;
	private String statusCicloMatricula;
	private String situacaoCiclo;
	private String tipoOfertaCurso;
	private String estagio;
	private Integer cargaHorariaEstagio;
	private String projetoPedagogico;
	private String situacaoETEC;
	private Long codigoPortifolio;
	private Long codigoPolo;
	private Integer qtdVagas;
	private Integer qtdInscritos;
	private Integer qtdMatriculas;
	private LocalDate dataCriacao;
	private String nomeResponsavel;
	private String cpf;
	
}
