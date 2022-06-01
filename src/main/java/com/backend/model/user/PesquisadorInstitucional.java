package com.backend.model.user;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.model.search.Pesquisa;

import lombok.Data;

@Data
@Entity
@Table(name="TB_PESQUISADOR_INSTITUCIONAL")
public class PesquisadorInstitucional extends Usuario {

	private static final long serialVersionUID = -5153678487655357359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pesquisador_id")
	private Long id;
	
	@OneToMany(mappedBy = "pesquisador")
	private List<Pesquisa> pesquisas; 
	
	public PesquisadorInstitucional() {
	}
	
	@Override
	public String toString() {
		return "PesquisadorInstitucional [id=" + id + " pesquisas="+pesquisas+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesquisadorInstitucional other = (PesquisadorInstitucional) obj;
		return Objects.equals(id, other.id) && Objects.equals(pesquisas, other.pesquisas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, pesquisas);
		return result;
	}	
}
