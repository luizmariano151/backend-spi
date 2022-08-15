package com.backend.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SUAPCSVtoJSON {
	private String Matricula;
	private String Nome;
	private String Campus;
	private String Curso;
	private String Modalidade;
	private String CPF;
	private String Turno;
	private String Vinculo;
}
