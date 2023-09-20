package linguasol.Project;

import java.io.BufferedReader;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class FileOperations {
	public List<String> l,l2,l3,l4;
	public HashSet<String> set;
	
	
	
	
	public FileOperations(String fileName) throws IOException {
		File file = new File(fileName);
		String filePath = file.getAbsolutePath();

		l = new ArrayList<>();
		l2 = new ArrayList<>();
		l3 = new ArrayList<>();
		l4 = new ArrayList<>();
		set = new HashSet<>();

		// 1. Capture.txt
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = bf.readLine()) != null) {
				String[] splitData = line.split("~");
				for (String x : splitData) {
					l.add(x);
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("1_Capture.txt"));
			for (int i = 0; i < l.size(); i++) {
				writer.write("" + (i+1) + "" + ":" + "\""+ l.get(i).toLowerCase().toString() + "\"" + ",");
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < l.size(); i++) {
			String current = l.get(i);
			if (current.contains("\n")) {
				String[] dataSplit = current.split("\n");
				for (String x : dataSplit) {
					x.replace("\n", "");
				}
			} else {
				l2.add(current);
			}
		}

		// 2_NewLine.txt

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("2_NewLine.txt"));
			for (int i = 0; i < l.size(); i++) {
				writer.write("\"" + l2.get(i).toLowerCase().toString() + "\"" + ":" + "\"" + "###"
						+ l2.get(i).toUpperCase().toString() + "###" + "\"" + ",");
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// for duplicate elements in the file.
		for (int i = 0; i < l2.size(); i++) {
			String current = l2.get(i);
			if (set.contains(current)) {
				l3.add(current);
			} else {
				set.add(current);
			}
		}

		// Writing unique data:
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("3_UniqueText.txt"));
			int j=0;
			for (int i = 0; i < l3.size(); i++) {
				j++;
				writer.write("" + j + "" + ":" + "\"" + l3.get(i).toLowerCase() + "\"" + ",");
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// writing duplicate data :
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("3_Duplicate.txt"));
			String[] Geeks = set.toArray(new String[set.size()]);
			if (set.size() == 0) {
				System.out.println("no duplicate elements found!");
			} else {
				int j=0;
				for (int i = 0; i < set.size(); i++) {
					j++;
					writer.write(""+ j + "" + ":" + "\"" + Geeks[i].toLowerCase() + "\"" + ",");
					writer.newLine();
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		l3 --> unique data 
		for (int i = 0; i < l3.size(); i++) {
			String line = l3.get(i);
			String data = line.replaceAll("<[^>]+>", "").replaceAll("(<[a-zA-Z]+) [^>]+>", "$1>")
					.replaceAll(" style=[\"'][^\"']*?[\"']", "").replaceAll("=", "");
			l4.add(data);
		}

		// writing removed value data.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("4_RemovedGarbageText.txt"));
			int j=0;
			for (int i = 0; i < l4.size(); i++) {
				j++;
				writer.write("" + j + "" + ":" + "\"" + l4.get(i).toLowerCase() + "\"" + ",");
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		List<String> list4 = new ArrayList<>();
		try (BufferedReader bf = new BufferedReader(new FileReader("D:\\Rahul Adagale\\File Project\\2_NewLine.txt"))) {
			String line;
			while((line=bf.readLine())!=null) {
				Pattern pattern = Pattern.compile("\"(.*?)\":"); // normal data
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()) {
					String line2 = matcher.group(1);
					Pattern pattern2 = Pattern.compile("<(.*?)>(.*?)</(.*?)>"); // complex data
					Matcher matcher2 = pattern2.matcher(line2);
					if(matcher2.find()) {
						list2.add(matcher2.group(2)); // to show in table
						list4.add(matcher2.group()); // to show in star dictonary
					}else {
						list3.add(matcher.group(1));
					}
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter("4_ComplexData.txt"));
			for(int i=0 ; i<list2.size() ; i++) {
				writer.write((i+1)+"\t\""+list2.get(i).toLowerCase()+"\",");
				writer.newLine();
			}
			writer.close();
			
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("3_NormalData.txt"));
			for(int i=0 ; i<list3.size() ; i++) {
				writer2.write((i+1)+":\""+list3.get(i).toLowerCase()+"\",");
				writer2.newLine();
			}
			writer2.close();
			
			BufferedWriter writer3 = new BufferedWriter(new FileWriter("ComplexStarDictonary.txt"));
			for(int i=0 ; i<list4.size() ; i++) {
				writer3.write("\""+list4.get(i).toLowerCase()+"\"\t\""+list4.get(i).toUpperCase()+"\",");
				writer3.newLine();
			}
			writer3.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Preprocessings po = new Preprocessings();
			po.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
	

	
	
	

}
