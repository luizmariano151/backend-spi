package com.backend.services;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.model.search.Data;
import com.backend.repositores.DataRepository;
import com.backend.util.ColumnFile;
import com.backend.util.ThreadSistec;
import com.backend.util.ThreadSuap;

@Service
public class FonteDadosService {
	
	@Autowired
	private DataRepository dataRepository;

	public Boolean extrairDados(MultipartFile sistec,MultipartFile suap) throws Exception{

		long tempoInicial = System.currentTimeMillis();
		
		boolean check1 = checkHeaderSistec(sistec);
		boolean check2 = checkHeaderSuap(suap);
		
		if(!check1 || !check2) {
			return false;
		}
		
		String suapDATA = "";
		String sistecDATA = "";
	
		ThreadSuap threadSuap = new ThreadSuap(suap, suapDATA);
		ThreadSistec threadSistec = new ThreadSistec(sistec, sistecDATA); 
		
		threadSuap.start();
		threadSistec.start();
		System.out.println("Loading...");
		
		try {
			threadSuap.join();
			threadSistec.join();
        } catch (InterruptedException e) {
            throw new Exception("A EXTRAÇÃO FOI INTERROMPIDA");
        }
		
		String dataSuap = threadSuap.getSuapDATA();
		String dataSistec = threadSistec.getSistecDATA();
		
		FileWriter writeFile = null;
		UUID uuid = UUID.randomUUID();

		String json = "{ \"suap\": "+dataSuap+", \"sistec\" :"+dataSistec+"  }";
		String path = "data/"+uuid.toString()+".json";
		
		try{
			writeFile = new FileWriter(path);
			writeFile.write(json.toString());
			writeFile.close();
		}
		catch(IOException e){
			throw new Exception("ERRO AO SALVAR ARQUIVO");
		}
		
		Data data = new Data();
		data.setPath(path);
		dataRepository.save(data);
		System.out.println("ARQUIVOS SALVOS COM SUCESSO!");
		
		long tempoFinal = System.currentTimeMillis();
		System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
		return true;
	}

	
	private boolean checkHeaderSistec(MultipartFile sistec) {
		try {
			InputStream input = sistec.getInputStream();
			BufferedReader csvReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

			String header = csvReader.readLine();
			String[] arrHeader = header.split(",");
			List<String> listHeader = ColumnFile.getColumnsSistec(); 
		
			for (int i = 0; i < arrHeader.length; i++) {
				if(!listHeader.contains(arrHeader[i])) 
					return false;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean checkHeaderSuap(MultipartFile suap) {
		try {
			InputStream input = suap.getInputStream();
			BufferedReader csvReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String header = csvReader.readLine();
			
			String[] arrHeader = header.split(",");
			List<String> listHeader = ColumnFile.getColumnsSuap();
			for (int i = 0; i < arrHeader.length; i++) {
				if(!listHeader.contains(arrHeader[i])) 
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
