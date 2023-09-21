package com.test.FileDirectory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Test1 {

	private JFrame frame;
	private JTextField directory_path;
	private JTextField search;
	private JFileChooser fc;
	private File file1, file2;
	private File[] files;
	private List<String> list1, list2, list3;

	/**
	 * Launch the application.
	 */

	public static boolean isSubstring(String main, String sub) {

		return main.matches("(.*?)" + sub.toLowerCase() + "(.*?)");
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 window = new Test1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 508, 231);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Select Folder : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 59, 106, 26);
		frame.getContentPane().add(lblNewLabel);

		directory_path = new JTextField();
		directory_path.setBounds(115, 63, 239, 20);
		frame.getContentPane().add(directory_path);
		directory_path.setColumns(10);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(fc);
				file1 = fc.getSelectedFile();
				directory_path.setText(file1.getAbsolutePath());
			}
		});
		btnNewButton.setBounds(364, 62, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Search : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 113, 106, 26);
		frame.getContentPane().add(lblNewLabel_1);

		search = new JTextField();
		search.setBounds(115, 117, 239, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String srch = search.getText();
				file2 = new File(file1.getAbsolutePath());
				files = file2.listFiles();
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
							flag = isSubstring(line.toLowerCase(), srch);
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
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Exception Found : " + ex, "Error!",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				File dir = new File(file1.getAbsolutePath()+"\\Search");
				dir.mkdir();
				for (int i = 0; i < list3.size(); i++) {
					FileInputStream fis;
					try {
						fis = new FileInputStream(list3.get(i));
						String path = dir.getAbsoluteFile() + "\\" + list2.get(i);
						FileOutputStream fos = new FileOutputStream(path);
						int line;
						while ((line = fis.read()) != -1) {
							fos.write(line);
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				
				JOptionPane.showMessageDialog(null, "Records Found : " + list3.size()+"\nFilePath : "+dir.getAbsoluteFile());
			}
		});
		btnNewButton_1.setBounds(364, 116, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

}
