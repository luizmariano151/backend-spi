package com.backend.model.search;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	private String CPF;
	private String nome;
	private Curso curso;
}
