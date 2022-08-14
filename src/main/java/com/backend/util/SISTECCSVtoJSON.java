package com.backend.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
//NOTAÇÃO PARA "OBRIGAR" AO MAPPER A USAR SOMENTE OS CAMPOS PARA REALIZAR O MAPEAMENTO
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)

//MAPEAMENTO COM TODOS OS CAMPOS DO CSV EM ORDEM (importante)
public class SISTECCSVtoJSON {
	private String CO_ALUNO_IDENTIFICADO;
	private String CO_ALUNO;
	private String NO_ALUNO;
	private String CO_UNIDADE_ENSINO;
	private String NO_CICLO_MATRICULA;
	private String DS_SENHA;
	private String CO_TIPO_OFERTA_CURSO;
	private String CO_PESSOA_FISICA_ALUNO;
	private String CO_CURSO;
	private String CO_MATRICULA;
	private String NO_STATUS_MATRICULA;
	private String DT_DATA_NASCIMENTO;
	private String DT_DATA_FIM_PREVISTO;
	private String CO_TIPO_INSTITUICAO;
	private String CO_PORTFOLIO;
	private String NU_TOTAL_INSCRITOS;
	private String NU_VAGAS_OFERTADAS;
	private String CO_PERIODO_CADASTRO;
	private String NU_CPF;
	private String DT_DATA_INICIO;
	private String CO_CICLO_MATRICULA;
	private String MES_DE_OCORRENCIA;
	private String SG_SEXO;
	private String DS_EMAIL;
	private String NU_CARGA_HORARIA;
}
