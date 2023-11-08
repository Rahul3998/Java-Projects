package com.LinguaSol.RegexExcelProject;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class LinguaSolRegexToolMain {

	private JFrame frmLsregextool;
	private JTextField textField_filePath;
	private JTextField textField_replacePath;
	private JTextField textField_regex;
	public File selectedFile, replaceFile;
	public String expFile, repFile;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinguaSolRegexToolMain window = new LinguaSolRegexToolMain();
					window.frmLsregextool.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LinguaSolRegexToolMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLsregextool = new JFrame();
		frmLsregextool.setTitle("LS_RegexTool");
		frmLsregextool.setBounds(100, 100, 679, 395);
		frmLsregextool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLsregextool.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("File");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 167, 49, 14);
		frmLsregextool.getContentPane().add(lblNewLabel);

		textField_filePath = new JTextField();
		textField_filePath.setBounds(72, 162, 440, 20);
		frmLsregextool.getContentPane().add(textField_filePath);
		textField_filePath.setColumns(10);

		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(414, 188, 95, 14);
		frmLsregextool.getContentPane().add(lblNewLabel_1);

		JButton fileBrowseBtn = new JButton("...");
		fileBrowseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fc.getSelectedFile();
					textField_filePath.setText(selectedFile.getAbsolutePath());
					lblNewLabel_1.setText("Imported");
				} else {
					JOptionPane.showMessageDialog(frmLsregextool, "File is not selected. Please try again!");
				}
			}
		});
		fileBrowseBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fileBrowseBtn.setBounds(519, 161, 43, 23);
		frmLsregextool.getContentPane().add(fileBrowseBtn);

		JButton exportBtn = new JButton("Export");
		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String myfile = selectedFile.getAbsolutePath();
				File fileType = new File(myfile);
				String fileName = fileType.getName();
				String regex = textField_regex.getText().toString();
				int index = fileName.lastIndexOf('.');
				if (index > 0) {
					String extension = fileName.substring(index + 1);
					if (extension.equals("json")) {
						if (regex.equals("Enter Some Pattern")) {
							int input = JOptionPane.showConfirmDialog(frmLsregextool,"Do You Want Apply Default Regular Expression Operation.");
							if (input == 0) {
								LNExportCsv export = new LNExportCsv(selectedFile);
								textField_replacePath.setText(export.expFile);
								String exp = export.expFile;
								expFile = exp;
							} else if (input == 1) {
								JOptionPane.showMessageDialog(frmLsregextool,"Please enter regex operation to continue");
								return;
							} else {
								return;
							}
						}else {
							LNExportCsv export = new LNExportCsv(selectedFile,regex);
							textField_replacePath.setText(export.expFile);
							
						}

					} else {
						JOptionPane.showMessageDialog(frmLsregextool, "Invalid File");
					}
				}

			}
		});
		exportBtn.setBounds(519, 188, 124, 23);
		frmLsregextool.getContentPane().add(exportBtn);

		textField_replacePath = new JTextField();
		textField_replacePath.setColumns(10);
		textField_replacePath.setBounds(72, 236, 440, 20);
		frmLsregextool.getContentPane().add(textField_replacePath);

		JLabel lblReplace = new JLabel("Replace");
		lblReplace.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblReplace.setBounds(13, 241, 49, 14);
		frmLsregextool.getContentPane().add(lblReplace);

		JButton replaceBtn = new JButton("Replace");
		replaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String slt = selectedFile.getAbsolutePath();
				try {
					if((textField_replacePath.getText())!="") {
						repFile = textField_replacePath.getText().toString();
						new LNReplaceCsv(repFile,slt);
					}else {
						new LNReplaceCsv(replaceFile,slt);
					}
					
				} catch (IOException e1) {
//					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmLsregextool, e1.toString());
				}
			}
		});
		replaceBtn.setBounds(519, 262, 124, 23);
		frmLsregextool.getContentPane().add(replaceBtn);

		final JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 10));
		lblNewLabel_1_1.setBounds(417, 262, 95, 14);
		frmLsregextool.getContentPane().add(lblNewLabel_1_1);

		JButton replaceBrowseBtn = new JButton("...");
		replaceBrowseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					final JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(fc);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						replaceFile = fc.getSelectedFile();
						textField_replacePath.setText(replaceFile.getAbsolutePath());
						lblNewLabel_1_1.setText("Imported");
					} else {
						JOptionPane.showMessageDialog(frmLsregextool, "File is not selected. Please try again!");
					}
			}
		});
		replaceBrowseBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		replaceBrowseBtn.setBounds(519, 235, 43, 23);
		frmLsregextool.getContentPane().add(replaceBrowseBtn);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Find", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(194, 28, 283, 123);
		frmLsregextool.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblRegex = new JLabel("Regex");
		lblRegex.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblRegex.setBounds(105, 34, 49, 14);
		panel.add(lblRegex);

		textField_regex = new JTextField("Enter Some Pattern");
		textField_regex.setBounds(20, 51, 240, 20);
		panel.add(textField_regex);
		textField_regex.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Final Output: ");
		lblNewLabel_2.setBounds(10, 333, 95, 14);
		frmLsregextool.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("none");
		lblNewLabel_3.setBounds(95, 333, 548, 14);
		frmLsregextool.getContentPane().add(lblNewLabel_3);

	}
	
	
}
