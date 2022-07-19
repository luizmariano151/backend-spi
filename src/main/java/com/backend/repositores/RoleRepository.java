package com.backend.repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.user.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{

	Optional<Role> findByRole(String role);
}
