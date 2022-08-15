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
	//arquivo a ser convertido e classe de mapeamento do csv
	public static void toJSON(File csv, Object input) {
		//Gerando o esquema e definindo suas caracteristicas
		CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(",".charAt(0)); 
		CsvMapper csvMapper = new CsvMapper(); 
		MappingIterator orderLines = null;
		try {
			//lendo os valores do csv e criando o .json
			if(input instanceof SISTECCSVtoJSON)
			{
				orderLines = csvMapper.readerFor(SISTECCSVtoJSON.class).with(orderLineSchema).readValues(csv);				
			}else
			{
				orderLines = csvMapper.readerFor(SUAPCSVtoJSON.class).with(orderLineSchema).readValues(csv);				
			}
			new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(new File("orderLinesFromCsv.json"),orderLines.readAll());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		File arq = new File("Relatoriocsv.csv");
		SUAPCSVtoJSON input = new SUAPCSVtoJSON();
		TestCSVSISTEC.toJSON(arq, input);
		System.out.println("OK");
	}
}
