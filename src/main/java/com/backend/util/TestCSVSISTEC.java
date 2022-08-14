package com.backend.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

//PROGRAMA UTILIZADO PARA TESTAR SE O SISTEMA CONSEGUE CONVERTER 
public class TestCSVSISTEC {
	public static void toJSON(File csv) {
		//Gerando o esquema e definindo suas caracteristicas
		CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(";".charAt(0)); 
		CsvMapper csvMapper = new CsvMapper(); 
		MappingIterator orderLines = null;
		try {
			//lendo os valores do csv e criando o .json
			orderLines = csvMapper.readerFor(SISTECCSVtoJSON.class).with(orderLineSchema).readValues(csv);
			new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(new File("orderLinesFromCsv.json"),orderLines.readAll());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		File arq = new File("rel.csv");
		TestCSVSISTEC.toJSON(arq);
		System.out.println("OK");
	}
}
