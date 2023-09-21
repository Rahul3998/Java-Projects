package com.test.FileDirectory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test4 {
	
	public static boolean isSubstring(String main, String sub) {
	
		return main.matches("(.*?)"+sub.toLowerCase()+"(.*?)");
	}

	public static void main(String[] args) throws IOException {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\Rahul.Adagale\\Desktop\\TestFolder\\sjj.txt"));
			String line;
			boolean flag = false;
			while((line=bf.readLine())!=null || flag==true) {
				flag=isSubstring(line.toLowerCase(),"Rahul");
				if(flag==true) {
					break;
				}
			}
			bf.close();
			System.out.println(flag);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
