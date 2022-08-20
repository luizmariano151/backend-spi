package com.backend.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.search.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long>{

	
}
