package com.backend.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SOLICITACAO")
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "funcionario_cca_id")
	private FuncionarioCCA usuario;

	public Solicitacao() {
	}

	@Override
	public String toString() {
		return "Solicitacao [id=" + id + ", usuario=" + usuario + "]";
	}

}
