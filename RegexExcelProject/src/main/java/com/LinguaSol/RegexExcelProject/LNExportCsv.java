package com.LinguaSol.RegexExcelProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LNExportCsv {
	public File file;
	public String regex;
	public String expFile;

	public LNExportCsv(File file) {
		this.file = file;
		this.regex = "Enter Some Pattern";
		try {
			readFile(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}
	
	public LNExportCsv(File file, String regex) {
		this.file = file;
		this.regex = regex;
		try {
			readFile(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public void readFile(File file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String currentLine;

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("Regex_Seperated_Data");
		XSSFRow row;

		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		data.put(1, new Object[] { "Original", "Trim", "Target" });
		int i = 2;
		while ((currentLine = bufferedReader.readLine()) != null) {
			if(regex.equals("Enter Some Pattern")) {
				Pattern pattern = Pattern.compile("\"(.*?)\":\\s\"(.*?)\"");
				Matcher matcher = pattern.matcher(currentLine);
				if (matcher.find()) {
					data.put(i, new Object[] { matcher.group(1) ,matcher.group(2), "" });
//					System.out.println( matcher.group(1) +"==>"+matcher.group(2));
				}
				i++;
			}else {
				Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		        Matcher matcher = pattern.matcher(currentLine);
		        while (matcher.find()) {
		            data.put(i, new Object[] {matcher.group(), matcher.group(), ""});
		            i++;
		        }
			}
			
		}
		
		Set<Integer> keyid = data.keySet();
		int rowid = 0;
		for (int key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		
//		String fileName = file.getName()+"_exported.xlsx";
		File exportedFile = new File(file.getName()+"_exported.xlsx");
		String expfile = exportedFile.getAbsolutePath();
		this.expFile = exportedFile.getAbsolutePath();
		FileOutputStream out = new FileOutputStream(new File(expfile));
		workbook.write(out);
		out.close();
		workbook.close();
		bufferedReader.close();
		JOptionPane.showMessageDialog(null, "Data Exported Sucessfully.");
	}

}
