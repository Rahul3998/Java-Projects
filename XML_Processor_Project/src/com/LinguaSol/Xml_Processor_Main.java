package com.LinguaSol;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


public class Xml_Processor_Main {

	public JFrame Xml_Frame;
	public JTextField textField_Filepath;
	public String filePath;
	public File selectedFile;
	public Xml_Processor_Operation xmlProcessingOperation;
	public JTextArea textArea;
	
	
	/**
	 * Programmed By : Rahul Adagale.
	 * Topic : Xml Parsing tool.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Xml_Processor_Main window = new Xml_Processor_Main();
					window.Xml_Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Xml_Processor_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Xml_Frame = new JFrame();
		Xml_Frame.setResizable(false);
		Xml_Frame.setTitle("Xml Processing");
		Xml_Frame.setBounds(100, 100, 664, 460);
		Xml_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Xml_Frame.getContentPane().setLayout(null);

		textField_Filepath = new JTextField();
		textField_Filepath.setBounds(90, 68, 457, 29);
		Xml_Frame.getContentPane().add(textField_Filepath);
		textField_Filepath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("File :: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 70, 70, 23);
		Xml_Frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 126, 630, 286);
		Xml_Frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 610, 239);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					Xml_Processor_Main xml_main = new Xml_Processor_Main();
					selectedFile = fileChooser.getSelectedFile();
					textField_Filepath.setText(selectedFile.getAbsolutePath());
					xmlProcessingOperation = new Xml_Processor_Operation(selectedFile.getAbsolutePath(),xml_main);
					xmlProcessingOperation.performOperation();
					Xml_Frame.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(557, 68, 43, 29);
		Xml_Frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("LinguaNext");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(591, 409, 49, 14);
		Xml_Frame.getContentPane().add(lblNewLabel_1);

	}
}
