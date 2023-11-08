package com.LinguaSol.RegexExcelProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LNReplaceCsv {
	public static Map<String, String> data;
	public File replaceFile;
	public String selectedFile, repFile;

	public LNReplaceCsv(File replaceFile, String selectedFile) throws IOException {
		this.replaceFile = replaceFile;
		this.selectedFile = selectedFile;
		File file = new File(selectedFile);
		String reppFile = file.getAbsolutePath();
		readXlsData(reppFile);
	}
	
	public LNReplaceCsv(String repFile, String selectedFile) throws IOException {
		this.repFile = repFile;
		this.selectedFile = selectedFile;
		readXlsData(repFile);
	}

	

	private void readXlsData(String file) throws IOException {
		try {
			XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = work.getSheet("Regex_Seperated_Data");
			XSSFRow row = null;
			int i = 0;
			data = new HashMap<>();
			while ((row = sheet.getRow(i)) != null) {
				data.put(row.getCell(0).getStringCellValue(), row.getCell(2).getStringCellValue());
				i++;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		PrintWriter writer = new PrintWriter("replacedJSON.json");
		if(!selectedFile.equals(null)) {
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line;
			while((line = reader.readLine()) != null) {
				Pattern pattern1 = Pattern.compile("\"(.*?)\":\\s\"(.*?)\"(,)");
				Matcher matcher1 = pattern1.matcher(line);
				Pattern pattern2 = Pattern.compile("\"(.*?)\":\\s\"(.*?)\"");
				Matcher matcher2 = pattern2.matcher(line);
				if(matcher1.find()) {
					String matData = matcher1.group(1);
					for(Map.Entry m : data.entrySet()) {
						if(m.getKey().equals(matData)) {
//							System.out.println("\t\""+matcher.group(1)+"\": \""+m.getValue()+"\"");
							writer.write("\t\""+matcher1.group(1)+"\": \""+m.getValue()+"\"");
						}
					}
					writer.write(",\n");
				}else if(matcher2.find()) {
					String matData = matcher2.group(1);
					for(Map.Entry m : data.entrySet()) {
						if(m.getKey().equals(matData)) {
//							System.out.println("\t\""+matcher.group(1)+"\": \""+m.getValue()+"\"");
							writer.write("\t\""+matcher2.group(1)+"\": \""+m.getValue()+"\"");
						}
					}
					writer.write("\n");
				}else{
					writer.write(line+"\n");
				}
			}
			reader.close();
			JOptionPane.showMessageDialog(null, "Done");
		}else {
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line;
			while((line = reader.readLine()) != null) {
				Pattern pattern = Pattern.compile("\"(.*?)\":\\s\"(.*?)\"");
				Matcher matcher = pattern.matcher(line);
				if(matcher.find()) {
					String matData = matcher.group(1);
					for(Map.Entry m : data.entrySet()) {
						if(m.getKey().equals(matData)) {
//							System.out.println("\t\""+matcher.group(1)+"\": \""+m.getValue()+"\"");
							writer.write("\t\""+matcher.group(1)+"\": \""+m.getValue()+"\"\n");
						}
					}
				}else {
					writer.write(line+"\n");
				}
			}
			reader.close();
			JOptionPane.showMessageDialog(null, "Done");
		}
		
		writer.close();
		
		

	}

	/*public void replaceData() {
		try {
			String sltFile = replaceFile.getAbsolutePath();
			File sltFileDt = new File(sltFile);
			PrintWriter writer = new PrintWriter("replacedJSON.json");
			if (!sltFileDt.exists()) {
				JOptionPane.showMessageDialog(null,
						"Exported File does not exists. Please export the file and try again.");
				return;
			} else {
				XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(sltFile));
				XSSFSheet sheet = work.getSheet("Regex_Seperated_Data");
				XSSFRow row = null;
				int i = 0;
//				System.out.println("{");
				writer.print("{\n");
				while ((row = sheet.getRow(i)) != null) {
//					System.out.print("\t\""+row.getCell(0).getStringCellValue()+"\": \""+row.getCell(2).getStringCellValue()+"\"");
//					System.out.println(",");
					writer.print("\t\"" + row.getCell(0).getStringCellValue() + "\": \""
							+ row.getCell(2).getStringCellValue() + "\",\n");
					i++;
				}
				writer.print("\n}");
				writer.close();
//				System.out.println("}");
				JOptionPane.showMessageDialog(null, "Replaced!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
