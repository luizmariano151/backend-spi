package com.backend.model.search;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.backend.model.user.PesquisadorInstitucional;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PESQUISA")
public class Pesquisa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "pesquisador_id")
	private PesquisadorInstitucional pesquisador;
	
	@OneToMany(mappedBy = "pesquisa")
	private List<ColaboradorPesquisa> colaboradoresPesquisas;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	@Temporal(TemporalType.DATE)
	private Date inicioDoCiclo;
	
	@Temporal(TemporalType.DATE)
	private Date terminoDoCiclo;
	
	public Pesquisa() {
	}
	
	@Override
	public String toString() {
		return "Pesquisa [id=" + id + ", status=" + status + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", inicioDoCiclo=" + inicioDoCiclo + ", terminoDoCiclo=" + terminoDoCiclo + "]";
	}
}
