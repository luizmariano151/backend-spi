package com.backend.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_ROLE")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1347309395190574303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;
	
	@Override
	public String getAuthority() {
		return role;
	}
}
