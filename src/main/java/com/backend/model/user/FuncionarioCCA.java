package com.backend.model.user;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.model.search.Campus;
import com.backend.model.search.Colaborador;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO_CCA")
public class FuncionarioCCA extends Usuario{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "funcionario_cca_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "campus_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Campus campus;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "funcionarioCCA")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Colaborador> colaboradores;
	
	public FuncionarioCCA() {
	}

	@Override
	public String toString() {
		return "FuncionarioCCA [id=" + id + ", campus=" + campus + ", colaboradores=" + colaboradores + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioCCA other = (FuncionarioCCA) obj;
		return Objects.equals(campus, other.campus) && Objects.equals(colaboradores, other.colaboradores)
				&& Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(campus, colaboradores, id);
		return result;
	}

}
