package com.backend.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fonte-dados")
public class FonteDadosController {

	@PostMapping("/extrair-dados")
	public ResponseEntity<Boolean> extrairDados(@RequestParam("file") MultipartFile file) {
		try {
			InputStream input = file.getInputStream();
			String file2 = StringUtils.cleanPath(file.getOriginalFilename());
			Long size = file.getSize();
			System.out.println(file);
			System.out.println(size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
