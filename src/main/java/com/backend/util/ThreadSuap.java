package com.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadSuap extends Thread{
	
	private MultipartFile suapFile;
	
	private String suapDATA;

	@Override
	public void run() {
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			buffer.append("[");
		
			InputStream input = suapFile.getInputStream();
			BufferedReader csv = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			
			String line = csv.readLine();
			line = csv.readLine();
			
			List<String> columns = ColumnFile.getColumnsSuap();
		
		
			while(line != null) {
				
				String[] arrCSV = line.split(",");
				Map<String, String> obj = new HashMap<String, String>();  
				
				obj.put(columns.get(0), arrCSV[0]);
				obj.put(columns.get(1), arrCSV[1]); 
				obj.put(columns.get(2), arrCSV[2]); 
				obj.put(columns.get(3), arrCSV[3]); 
				obj.put(columns.get(4), arrCSV[4]); 
				obj.put(columns.get(5), arrCSV[5]); 
				obj.put(columns.get(6), arrCSV[6]); 
				obj.put(columns.get(7), arrCSV[7]); 
				obj.put(columns.get(8), arrCSV[8]); 
				obj.put(columns.get(9), arrCSV[9]); 
				obj.put(columns.get(10), arrCSV[10]); 
				obj.put(columns.get(11), arrCSV[11]); 
				obj.put(columns.get(12), arrCSV[12]); 
				obj.put(columns.get(13), arrCSV[13]); 
				obj.put(columns.get(14), arrCSV[14]); 
				obj.put(columns.get(15), arrCSV[15]); 
				obj.put(columns.get(16), arrCSV[16]); 
				obj.put(columns.get(17), arrCSV[17]); 
				obj.put(columns.get(18), arrCSV[18]); 
				obj.put(columns.get(19), arrCSV[19]);
				obj.put(columns.get(20), arrCSV[20]); 
				
				String json = JSONValue.toJSONString(obj);
				buffer.append(",");
				buffer.append(json);
				line = csv.readLine();
			}
			
			buffer.append("]");
			
			buffer = buffer.deleteCharAt(1);
			
			this.suapDATA += buffer;
			System.out.println("ARQUIVO SUAP LIDO COM SUCESSO!");
			
			} catch (IOException e) {
				System.out.println("ERRO AO LER AQUIVO DO SUAP!");
			}
	}

}
