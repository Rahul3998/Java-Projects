package com.LinguaSol;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Regex_Main {

	private JFrame frmRegexParsingTool;
	private JTextField textField_RegularExp;
	private String regexPattern;
	private String testString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regex_Main window = new Regex_Main();
					window.frmRegexParsingTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Regex_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegexParsingTool = new JFrame();
		frmRegexParsingTool.getContentPane().setBackground(new Color(255, 150, 45));
		frmRegexParsingTool.setTitle("Regex Parsing Tool");
		frmRegexParsingTool.setResizable(false);		
		frmRegexParsingTool.setBounds(100, 100, 755, 510);
		frmRegexParsingTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegexParsingTool.getContentPane().setLayout(null);
		
		
		textField_RegularExp = new JTextField();
		textField_RegularExp.setBounds(31, 49, 341, 29);
		frmRegexParsingTool.getContentPane().add(textField_RegularExp);
		textField_RegularExp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Regular Expression");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(31, 24, 211, 23);
		frmRegexParsingTool.getContentPane().add(lblNewLabel);
		
		JLabel lblTestString = new JLabel("Test String ");
		lblTestString.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTestString.setBounds(31, 112, 211, 23);
		frmRegexParsingTool.getContentPane().add(lblTestString);
		
		JLabel lblMatchInformation = new JLabel("Match Information");
		lblMatchInformation.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMatchInformation.setBounds(398, 24, 211, 23);
		frmRegexParsingTool.getContentPane().add(lblMatchInformation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 136, 341, 293);
		frmRegexParsingTool.getContentPane().add(scrollPane);
		
		JTextArea textArea_TestStr = new JTextArea();
		textArea_TestStr.setLineWrap(true);
		scrollPane.setViewportView(textArea_TestStr);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(398, 49, 333, 376);
		frmRegexParsingTool.getContentPane().add(scrollPane_1);
		
		JTextArea textArea_MatchInfo = new JTextArea();
		scrollPane_1.setViewportView(textArea_MatchInfo);
		
		JButton Match_Btn = new JButton("Match");
		Match_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regexPattern = textField_RegularExp.getText();
				testString = textArea_TestStr.getText();
				
				if(regexPattern.equals("") || testString.equals("")) {
					JOptionPane.showMessageDialog(frmRegexParsingTool, "Please enter complete details.");
					return;
				}else {
					Pattern pattern = Pattern.compile(regexPattern, Pattern.MULTILINE);
			        Matcher matcher = pattern.matcher(testString);
			        int i = 1;
			        textArea_MatchInfo.append("Pattern to match :: "+regexPattern+"\n");
			        while (matcher.find()) {
			            textArea_MatchInfo.append("Match "+Integer.toString(i)+": " + matcher.group()+" ["+matcher.start()+"-"+matcher.end()+"]\n");
			            i++;
			        }
			        
			        textArea_MatchInfo.append("Total Match :: "+(i-1)+"\n");
				}
			}
		});
		Match_Btn.setBounds(153, 440, 89, 23);
		frmRegexParsingTool.getContentPane().add(Match_Btn);
		
		JLabel lblNewLabel_1 = new JLabel("LinguaNext");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(682, 449, 49, 14);
		frmRegexParsingTool.getContentPane().add(lblNewLabel_1);
		
		JButton clear_Btn = new JButton("Clear");
		clear_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_MatchInfo.setText("");
			}
		});
		clear_Btn.setBounds(520, 439, 89, 23);
		frmRegexParsingTool.getContentPane().add(clear_Btn);
		
		
	}
}
