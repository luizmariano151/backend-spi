package com.backend.util;

import java.util.Arrays;
import java.util.List;

public class ColumnFile {
	
	
	public static List<String> getColumnsSuap() {
		List<String> columns = Arrays.asList(
				"Codigo",
				"Matricula",
				"Nome",
				"Campus",
				"Curso",
				"Situacao no Curso",
				"Situacao no Ultimo Periodo",
				"Nivel de ensino",
				"Modalidade",
				"Codigo e-MEC",
				"Ano de ingresso",
				"Periodo de ingresso",
				"Ano de Conclusao",
				"CPF",
				"Cota MEC",
				"Cota SISTEC",
				"Data da matricula",
				"Data de conclusao",
				"Turno",
				"Vinculo",
				"Diarios matriculados no ultimo periodo");
		return columns;
	}
	
	public static List<String> getColumnsSistec(){
		List<String> columns = Arrays.asList(
				"CO_ALUNO_IDENTIFICADO",
				"CO_ALUNO",
				"NO_ALUNO",
				"SG_SEXO",
				"DT_DATA_NASCIMENTO",
				"NU_CPF",
				"DS_EMAIL",
				"CO_PESSOA_FISICA_ALUNO",
				"DS_SENHA",
				"CO_MATRICULA",
				"CO_CICLO_MATRICULA",
				"CO_CURSO",
				"NU_CARGA_HORARIA",
				"DT_DATA_INICIO",
				"DT_DATA_FIM_PREVISTO",
				"CO_PERIODO_CADASTRO",
				"NO_CICLO_MATRICULA",
				"CO_TIPO_OFERTA_CURSO",
				"CO_TIPO_INSTITUICAO",
				"CO_PORTFOLIO",
				"NU_VAGAS_OFERTADAS",
				"NU_TOTAL_INSCRITOS",
				"NO_STATUS_MATRICULA",
				"CO_UNIDADE_ENSINO",
				"MES_DE_OCORRENCIA");
		return columns;
	}

}
