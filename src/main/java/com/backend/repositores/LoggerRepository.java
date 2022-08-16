package com.backend.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.system.Logger;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {}
