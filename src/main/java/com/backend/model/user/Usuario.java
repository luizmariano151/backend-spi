package com.backend.model.user;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TB_USUARIO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{
	
	private static final long serialVersionUID = -4415053381921478760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(unique = true, nullable = false)
	private Long matricula;
	
	@Column(unique = true, nullable = false)
	private String cpf;
	
	@Column(unique = true, nullable = false, length = 100)
	private String email;

	@Column(nullable = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Role> roles;
	
	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getPassword() {
		return this.senha;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getUsername() {
		return this.email;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isEnabled() {
		return true;
	}
	
}
