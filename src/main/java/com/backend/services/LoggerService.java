package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.system.Logger;
import com.backend.repositores.LoggerRepository;

@Service
public class LoggerService {

    @Autowired
    private LoggerRepository repository;

    public Logger create(Logger logger) {
        return repository.save(logger);
    }

    public List<Logger> fidnAll() {
        List<Logger> loggers = repository.findAll();
        return loggers;
    }
}
