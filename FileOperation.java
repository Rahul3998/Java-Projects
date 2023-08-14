package fileManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class Main {

	private JFrame mainFrame;
	private JFileChooser file;
	private JTextField CharText;
	private JTextField textField_1;
	private String fileName;
	private File file1; // created file
	private JTable table;
	private FileWriter writer;
	private File newFile;
	private String symbol;
	ArrayList obj;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	public static String loadFile(String filePath) {
		try {
			return new String(Files.readAllBytes(new File(filePath).toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}

	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setForeground(new Color(0, 0, 0));
		mainFrame.setForeground(new Color(255, 255, 255));
		mainFrame.setBackground(new Color(0, 0, 0));
		mainFrame.getContentPane().setBackground(new Color(192, 192, 192));
		mainFrame.setTitle("File Operation");
		mainFrame.setBounds(100, 100, 705, 615);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JLabel lblSearchFilePath = new JLabel("Choose File : ");
		lblSearchFilePath.setForeground(new Color(0, 0, 0));
		lblSearchFilePath.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearchFilePath.setBounds(20, 113, 127, 19);
		mainFrame.getContentPane().add(lblSearchFilePath);

		JLabel lblNewLabel = new JLabel("File Operation Project");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(223, 26, 238, 31);
		mainFrame.getContentPane().add(lblNewLabel);

		JButton BtnBrowse = new JButton("Browse");
		BtnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file = new JFileChooser("C:\\Users\\Rahul.Adagale\\Desktop");
				file.setMultiSelectionEnabled(true);
				file.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text File (.txt)", "txt");
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Java File (.java)", "java");
				FileNameExtensionFilter filter3 = new FileNameExtensionFilter("LSTR File (.lstr)", "lstr");

				file.addChoosableFileFilter(filter1);
				file.addChoosableFileFilter(filter2);
				file.addChoosableFileFilter(filter3);

				int option = file.showOpenDialog(null);

				if (option == JFileChooser.APPROVE_OPTION) {
					file1 = file.getSelectedFile();
					fileName = file1.getAbsolutePath();
					textField_1.setText(fileName);
					
					

					JOptionPane.showMessageDialog(null, "File Imported Successfully !", "Success",
							JOptionPane.DEFAULT_OPTION);
					
					JLabel lblNewLabel_2 = new JLabel("Imported!");
					lblNewLabel_2.setBounds(615, 111, 66, 19);
					mainFrame.getContentPane().add(lblNewLabel_2);
				} else {
					JOptionPane.showMessageDialog(null, "No File Selected !", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		BtnBrowse.setForeground(new Color(0, 0, 0));
		BtnBrowse.setBackground(new Color(128, 255, 0));
		BtnBrowse.setBounds(523, 111, 76, 19);
		mainFrame.getContentPane().add(BtnBrowse);

		JLabel lblNewLabel_1 = new JLabel("Enter Character : ");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(20, 143, 127, 31);
		mainFrame.getContentPane().add(lblNewLabel_1);

		CharText = new JTextField();
		CharText.setBounds(129, 146, 344, 20);
		mainFrame.getContentPane().add(CharText);
		CharText.setColumns(10);

		JButton btnNewButton_1 = new JButton("Prepare Star Dictonary");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = CharText.getText();
					String path = file1.getAbsolutePath();
					File myFile = new File(path);
					myFile.createNewFile();

					if (text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input some text", "ERROR!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
							writer.write("\"" + text.toLowerCase() + "\"" + "\t" + "###" + text.toLowerCase() + "###");
							writer.newLine();
							JOptionPane.showMessageDialog(null, "Successfully Saved!", "Saved",
									JOptionPane.WARNING_MESSAGE);
							CharText.setText("");
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null, "There is some problem while saving data", "ERROR!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBackground(new Color(128, 255, 0));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(469, 147, 130, 19);
		mainFrame.getContentPane().add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.setBounds(119, 110, 480, 20);
		mainFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Maximum three words!");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(129, 163, 106, 14);
		mainFrame.getContentPane().add(lblNewLabel_3);

		JButton ShowBtn = new JButton("Show Data");
		ShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file1 == null) {
					JOptionPane.showMessageDialog(null, "Please import file first!");
				}

				try {

					BufferedReader br = new BufferedReader(new FileReader("Output.txt"));
//					String firstLine = br.readLine().trim();

					String[] columnName = { "Text", "Star Dictonary" };
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setColumnIdentifiers(columnName);

					Object[] line = br.lines().toArray();
					for (int i = 0; i < line.length; i++) {
						String lines = line[i].toString().trim();

						String[] dataRow = lines.split(":");

						model.addRow(dataRow);

					}

				} catch (Exception i) {
					JOptionPane.showMessageDialog(null, "Error! " + i);
				}
			}
		});
		ShowBtn.setBounds(45, 303, 89, 23);
		mainFrame.getContentPane().add(ShowBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 337, 569, 224);
		mainFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Text", "Star Dictonary" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(null, new String[] { "Text", "Star Dictonary" }));
			}
		});
		closeBtn.setBounds(510, 303, 89, 23);
		mainFrame.getContentPane().add(closeBtn);

		JButton convertBtn = new JButton("Convert");
		convertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String path = file1.getAbsolutePath();

					obj = new ArrayList();

					BufferedReader reader = new BufferedReader(new FileReader(path));
					String line;
					while ((line = reader.readLine()) != null) {
						String[] rs = line.split("~");
						for (String x : rs) {
							obj.add(x);
						}
					}

					BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"));
					for (int i = 0; i < obj.size(); i++) {
						writer.write("\"" + obj.get(i).toString().toLowerCase() + "\"" + ":" + "###"
								+ obj.get(i).toString().toUpperCase() + "###,");
						writer.newLine();
//					System.out.println(obj.get(i).toString().toLowerCase());
					}

					JOptionPane.showMessageDialog(null, "Saved!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		convertBtn.setBounds(271, 303, 89, 23);
		mainFrame.getContentPane().add(convertBtn);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected()) {
					String symbol = JOptionPane.showInputDialog(null,
							"Enter the special Character you want to seperate : ");
					if (symbol == null) {
						return; // User canceled the search
					}
					try {
						symbolSearch(symbol);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private void symbolSearch(String symbols) throws IOException {
				ArrayList<String> obj = new ArrayList<>();
				BufferedReader reader = new BufferedReader(new FileReader("Output.txt"));
				String line;
				while ((line = reader.readLine()) != null) {
					String symbolsToRemove = "[" + Pattern.quote(symbols) + "]";

					String updatedString = line.replaceAll(symbolsToRemove, "");

					String[] rs = updatedString.split(":");
					for (String x : rs) {
						obj.add(x);
					}
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"));
				for (int i = 0; i < obj.size(); i++) {
					writer.write("\"" + obj.get(i).toString().toLowerCase() + "\"" + ":" + "###"
							+ obj.get(i).toString().toUpperCase() + "###,");
					writer.newLine();
				}
				JOptionPane.showMessageDialog(null, "Separated Successfully!");
			}

		});
		checkBox.setBackground(new Color(192, 192, 192));
		checkBox.setBounds(233, 204, 21, 19);
		mainFrame.getContentPane().add(checkBox);

		JLabel lblNewLabel_4 = new JLabel("Separate using Special Character");
		lblNewLabel_4.setBounds(72, 204, 170, 19);
		mainFrame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Steps to perform operation : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(294, 189, 215, 14);
		mainFrame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("1. Choose the file.");
		lblNewLabel_6.setBounds(271, 216, 143, 14);
		mainFrame.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("2. Seperate the document ");
		lblNewLabel_7.setBounds(271, 229, 143, 19);
		mainFrame.getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("using regular seperator ");
		lblNewLabel_8.setBounds(399, 231, 153, 14);
		mainFrame.getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("method by pressing \"Convert\" button.");
		lblNewLabel_9.setBounds(294, 241, 215, 14);
		mainFrame.getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("3. You can also write in document by clicking Prepapre Star Dictonary");
		lblNewLabel_10.setBounds(269, 253, 350, 14);
		mainFrame.getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("4. Seperate Using Special characters.");
		lblNewLabel_11.setBounds(271, 266, 238, 14);
		mainFrame.getContentPane().add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("5. Show as well as also close data.");
		lblNewLabel_12.setBounds(271, 278, 238, 14);
		mainFrame.getContentPane().add(lblNewLabel_12);
		
		

	}
}
