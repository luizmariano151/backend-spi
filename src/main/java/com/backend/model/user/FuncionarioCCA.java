package com.backend.model.user;

import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO_CCA")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
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

}
