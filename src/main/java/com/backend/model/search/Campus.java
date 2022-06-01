package com.backend.model.search;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.model.user.FuncionarioCCA;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CAMPUS")
public class Campus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "campus")
	private List<FuncionarioCCA> funcionarioCCAs;
	
	@OneToMany(mappedBy = "campus")
	private List<ColaboradorPesquisa> colaboradorPesquisas;
	
	private Integer suapCodigo;
	
	private String suapSigla;
	
	private Integer sistecCodigo;

	public Campus() {
	}

	@Override
	public String toString() {
		return "Campus [id=" + id + ", funcionarioCCAs=" + funcionarioCCAs + ", colaboradorPesquisas="
				+ colaboradorPesquisas + ", suapCodigo=" + suapCodigo + ", suapSigla=" + suapSigla + ", sistecCodigo="
				+ sistecCodigo + "]";
	}
}
