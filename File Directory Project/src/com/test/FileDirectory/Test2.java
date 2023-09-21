package com.test.FileDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class Test2 {

	public static boolean isSubstring(String main, String sub) {

		return main.matches("(.*?)" + sub.toLowerCase() + "(.*?)");
	}

	public static void main(String[] args) throws IOException {
		List<String> list1, list2, list3;
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(fc);
		File file1 = fc.getSelectedFile();
		System.out.println(file1.getAbsolutePath());
		File file2 = new File(file1.getAbsolutePath());
		File[] files = file2.listFiles();
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		list3 = new ArrayList<>();
		for (File x : files) {
			list1.add(x.getName());
			try {
				BufferedReader bf = new BufferedReader(new FileReader(x.getAbsolutePath()));
				String line;
				boolean flag = false;
				while ((line = bf.readLine()) != null || flag == true) {
					flag = isSubstring(line.toLowerCase(), "Rahul");
					if (flag == true) {
						break;
					}
				}
				bf.close();

				if (flag == false) {
					continue;
				}
				list2.add(x.getName());
				list3.add(x.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		File dir = new File("Search");
		dir.mkdir();
//		System.out.println("Dir : "+dir.getAbsolutePath());
		for(int i=0 ; i<list3.size() ; i++) {
			FileInputStream fis = new FileInputStream(list3.get(i));
			String path = dir.getAbsoluteFile()+"\\"+list2.get(i);
			FileOutputStream fos = new FileOutputStream(path);
			int line;
			while((line = fis.read())!=-1) {
				fos.write(line);
			}
		}
		System.out.println("Data Copied Successfully!");

	}
}
