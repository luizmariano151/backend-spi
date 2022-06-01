package com.backend.model.search;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.model.user.FuncionarioCCA;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_COLABORADOR_PESQUISA")
public class ColaboradorPesquisa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "funcionario_cca_id")
	private FuncionarioCCA funcionarioCCA;
	
	@ManyToOne
    @JoinColumn(name = "campus_id")
	private Campus campus;
	
	@ManyToOne
    @JoinColumn(name = "pesquisa_id")
	private Pesquisa pesquisa;
	
	private Long matricula;
	
	private String nome;
	
	private String email;

	public ColaboradorPesquisa() {
	}

	@Override
	public String toString() {
		return "ColaboradorPesquisa [id=" + id + ", funcionarioCCA=" + funcionarioCCA + ", campus=" + campus
				+ ", pesquisa=" + pesquisa + ", matricula=" + matricula + ", nome=" + nome + ", email=" + email + "]";
	}
}
