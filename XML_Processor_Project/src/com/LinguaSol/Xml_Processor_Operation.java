package com.LinguaSol;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml_Processor_Operation {
	private File operationPath;
	private String filePath;
	public Xml_Processor_Main xmlMain;
	public String parentNode;
	public String[] childNode;
	public Scanner scanner;
	
	
	Xml_Processor_Operation(String filePath,Xml_Processor_Main xml_Main){
		this.filePath = filePath;
		this.xmlMain = xml_Main;
		scanner = new Scanner(System.in);
		String input = JOptionPane.showInputDialog("Enter the Parent Node :");
		this.parentNode = input;
		String number = JOptionPane.showInputDialog("Enter the number of childNode to search : ");
		int no = Integer.parseInt(number);
		childNode = new String[no];
		for(int i=0 ; i<no ; i++) {
			this.childNode[i] = JOptionPane.showInputDialog("ChildNode["+(i+1)+"]");
		}
		
		
	}
	
	public void performOperation() {
		operationPath = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		xmlMain = new Xml_Processor_Main();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(operationPath);
			xmlMain.textField_Filepath.setText(operationPath.getAbsolutePath());
//			System.out.println("Parent Node : " + doc.getDocumentElement().getNodeName());
			xmlMain.textArea.append("Root Node :: " + doc.getDocumentElement().getNodeName()+"\n");
			NodeList list = doc.getElementsByTagName(parentNode);
//			setListn(list);
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
//					System.out.println("Current parent tag: "+element.getNodeName());
					xmlMain.textArea.append("Parent Node :: "+element.getNodeName()+"\n");
					for(int j=0 ; j<childNode.length ; j++) {
//						System.out.println(childNode[j]+" : " + element.getElementsByTagName(childNode[j]).item(0).getTextContent());
						xmlMain.textArea.append(childNode[j]+" :: " + element.getElementsByTagName(childNode[j]).item(0).getTextContent()+"\n");
					}
				}
			}
			xmlMain.Xml_Frame.setVisible(true);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
