package com.backend.model.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.model.search.Pesquisa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="TB_PESQUISADOR_INSTITUCIONAL")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PesquisadorInstitucional extends Usuario {

	private static final long serialVersionUID = -5153678487655357359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pesquisador_id")
	private Long id;
	
	@OneToMany(mappedBy = "pesquisador")
	private List<Pesquisa> pesquisas; 
	
	
}
