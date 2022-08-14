package com.backend.model.search;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Aluno {
	private String CPF;
	private String nome;
	private Curso curso;
	private LocalDate dataMatricula;
}
