package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.search.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long>{

	Optional<Campus> findById(Long id);

}
