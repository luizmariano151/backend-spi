package com.backend.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fonte-dados")
public class FonteDadosController {

	@PostMapping("/extrair-dados")
	public ResponseEntity<Boolean> extrairDados(@RequestParam("file") MultipartFile suapFile, @RequestParam("file2") MultipartFile sistecFile) {
		InputStream input =null;
		File arqSuap = new File("recebido_suap.csv");
		File arqSistec = new File("recebido_sistec.csv");
		try {
			input = suapFile.getInputStream();
			FileUtils.copyInputStreamToFile(input, arqSuap);
			input = sistecFile.getInputStream();
			FileUtils.copyInputStreamToFile(input, arqSistec);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
