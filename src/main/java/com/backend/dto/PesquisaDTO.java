package com.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class PesquisaDTO {
	
	@NotNull(message = "ID DO COLABORADOR NÃO PODE SER NULO")
	@Positive(message = "ID DO COLABORADOR INVÁLIDO")
	private Long colaboradorId;
	
	@NotNull(message = "DATA DE INÍCIO NÃO PODE SER NULA")
	@FutureOrPresent(message = "DATA DE INÍCIO DA DEVE SER PRESENTE OU FUTURA")
	private LocalDate dataInicio;
	
	@NotNull(message = "DATA DE TÉRMINO NÃO PODE SER NULA")
	@Future(message = "DATA DE TÉRMINO DEVE SER FUTURA")
	private LocalDate dataTermino;
	
	@NotNull(message = "DATA DE INÍCIO DO CICLO NÃO PODE SER NULA")
	private LocalDate inicioDoCiclo;
	
	@NotNull(message = "DATA DO TÉRMINO DO CICLO NÃO PODER SER NULA")
	private LocalDate terminoDoCiclo;
	
	public PesquisaDTO(){}

	@Override
	public String toString() {
		return "PesquisaDTO [colaboradorId=" + colaboradorId + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", inicioDoCiclo=" + inicioDoCiclo + ", terminoDoCiclo=" + terminoDoCiclo + "]";
	}
	
}
