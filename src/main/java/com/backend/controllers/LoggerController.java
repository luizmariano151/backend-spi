package com.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.system.Logger;
import com.backend.services.LoggerService;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    public ResponseEntity<List<Logger>> listAll() {
        List<Logger> loggers = loggerService.fidnAll();
        return ResponseEntity.ok().body(loggers);
    }

}
