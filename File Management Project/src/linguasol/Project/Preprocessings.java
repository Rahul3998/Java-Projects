package linguasol.Project;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Preprocessings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890304807784670720L;
	private JPanel contentPane;
	private JTable table;
	private JTextField Captured_textField;
	private int file1, file2, file3, file4, file5;
	private JTable table_1;
	private JTable table_2;
	private JTextField Duplicate_textField;
	private JTextField Unique_textField;
	private JTable table_3;
	private JTable table_4;
	private JTextField Normal_textField;
	private JTextField complex_textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JPanel panel_5;
	private JScrollPane scrollPane_5;
	private JButton btnNewButton;
	private JTable table_5;
	private JScrollPane scrollPane_6;
	private JTable table_6;
	private JButton btnNewButton_1;
	private JPanel panel_8;
	private JButton btnNewButton_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preprocessings frame = new Preprocessings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	class FileTableModel extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1845271751077700307L;
		private List<String[]> data = new ArrayList<>();

		public FileTableModel(String filePath) {
			loadFileData(filePath);
			setColumnIdentifiers(new String[] { "Srno", "data" });
			for (int i = 0; i < data.size(); i++) {
				addRow(data.get(i));
			}
		}

		private void loadFileData(String filePath) {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] columns = line.split(":");
					if (columns.length == 2) {
						data.add(columns);
					} else {
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	class FileTableModel2 extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1845271751077700307L;
		private List<String[]> data = new ArrayList<>();

		public FileTableModel2(String filePath) {
			loadFileData(filePath);
			setColumnIdentifiers(new String[] { "Srno", "data" });
			for (int i = 0; i < data.size(); i++) {
				addRow(data.get(i));
			}
		}

		private void loadFileData(String filePath) {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] columns = line.split("\t");
					if (columns.length == 2) {
						data.add(columns);
					} else {
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	public Preprocessings() throws IOException {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1304, 714);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 205, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1250, 655);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 187, 119));
		tabbedPane.addTab("Captured Data", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1225, 579);
		panel.add(scrollPane);
		Captured_textField = new JTextField();
		Captured_textField.setEditable(false);
		Captured_textField.setBounds(1139, 596, 96, 20);
		panel.add(Captured_textField);
		Captured_textField.setColumns(10);
		FileTableModel data1 = new FileTableModel("1_Capture.txt");
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(data1);
		try {
			List<String> list = new ArrayList<>();
			BufferedReader bf = new BufferedReader(new FileReader("1_Capture.txt"));
			String line;
			while ((line = bf.readLine()) != null) {
				Pattern pattern = Pattern.compile("\"(.*?)\",");
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					list.add(matcher.group(1));
				}
			}
			bf.close();
			Captured_textField.setText("Total Lines : " + list.size());
			file1 = list.size();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 187, 119));
		tabbedPane.addTab("Duplicate Data", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 46, 603, 545);
		panel_1.add(scrollPane_1);
		Duplicate_textField = new JTextField();
		Duplicate_textField.setEditable(false);
		Duplicate_textField.setBounds(497, 596, 116, 20);
		panel_1.add(Duplicate_textField);
		Duplicate_textField.setColumns(10);
		FileTableModel data2 = new FileTableModel("3_Duplicate.txt");
		List<String> list1 = new ArrayList<>();
		BufferedReader bf1 = new BufferedReader(new FileReader("3_Duplicate.txt"));
		String line1;
		while ((line1 = bf1.readLine()) != null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line1);
			while (matcher.find()) {
				list1.add(matcher.group(1));
			}
		}
		bf1.close();
		Duplicate_textField.setText("Total Lines : " + list1.size());
		file2 = list1.size();
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(data2);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(632, 46, 603, 545);
		panel_1.add(scrollPane_2);
		Unique_textField = new JTextField();
		Unique_textField.setEditable(false);
		Unique_textField.setBounds(1119, 596, 116, 20);
		panel_1.add(Unique_textField);
		Unique_textField.setColumns(10);
		FileTableModel data3 = new FileTableModel("3_UniqueText.txt");
		List<String> list3 = new ArrayList<>();
		BufferedReader bf3 = new BufferedReader(new FileReader("3_UniqueText.txt"));
		String line3;
		while ((line3 = bf3.readLine()) != null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line3);
			while (matcher.find()) {
				list3.add(matcher.group(1));
			}
		}
		bf3.close();
		Unique_textField.setText("Total Lines : " + list3.size());
		file3 = list3.size();
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(data3);

		lblNewLabel = new JLabel("Duplicate.txt");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 164, 32);
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("UniqueText.txt");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(632, 11, 157, 32);
		panel_1.add(lblNewLabel_1);

//		JButton btnNewButton = new JButton("Prepare Star Dictonary");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DuplicateFrame frame;
//				try {
//					frame = new DuplicateFrame();
//					frame.setVisible(true);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 10));
//		btnNewButton.setBounds(449, 20, 164, 23);
//		panel_1.add(btnNewButton);

//		JButton btnNewButton_1 = new JButton("Prepare Star Dictonary");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UniqueFrame frame;
//				try {
//					frame = new UniqueFrame();
//					frame.setVisible(true);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		btnNewButton_1.setFont(new Font("Serif", Font.PLAIN, 10));
//		btnNewButton_1.setBounds(1071, 20, 164, 23);
//		panel_1.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 187, 119));
		tabbedPane.addTab("Complex Data", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 46, 603, 545);
		panel_2.add(scrollPane_3);
		Normal_textField = new JTextField();
		Normal_textField.setEditable(false);
		Normal_textField.setBounds(497, 596, 116, 20);
		panel_2.add(Normal_textField);
		Normal_textField.setColumns(10);
		FileTableModel data4 = new FileTableModel("3_NormalData.txt");
		List<String> list4 = new ArrayList<>();
		BufferedReader bf4 = new BufferedReader(new FileReader("3_NormalData.txt"));
		String line4;
		while ((line4 = bf4.readLine()) != null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line4);
			while (matcher.find()) {
				list4.add(matcher.group(1));
			}
		}
		bf4.close();
		Normal_textField.setText("Total Lines : " + list4.size());
		file4 = list4.size();
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(data4);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(632, 46, 603, 545);
		panel_2.add(scrollPane_4);
		complex_textField = new JTextField();
		complex_textField.setEditable(false);
		complex_textField.setBounds(1119, 596, 116, 20);
		panel_2.add(complex_textField);
		complex_textField.setColumns(10);
		FileTableModel2 data5 = new FileTableModel2("4_ComplexData.txt");
		List<String> list5 = new ArrayList<>();
		BufferedReader bf5 = new BufferedReader(new FileReader("4_ComplexData.txt"));
		String line5;
		while ((line5 = bf5.readLine()) != null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line5);
			while (matcher.find()) {
				list5.add(matcher.group(1));
			}
		}
		bf5.close();
		complex_textField.setText("Total Lines : " + list5.size());
		file5 = list5.size();
		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		table_4.setModel(data5);

		lblNewLabel_2 = new JLabel("NormalData.txt");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 11, 136, 34);
		panel_2.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("ComplexData.txt");
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(632, 11, 136, 34);
		panel_2.add(lblNewLabel_3);

		btnNewButton_2 = new JButton("Prepare Star Dictonary");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NormalFrame frame;
				try {
					frame = new NormalFrame();
					frame.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Serif", Font.PLAIN, 10));
		btnNewButton_2.setBounds(450, 21, 164, 23);
		panel_2.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Prepare Star Dictonary");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComplexFrame frame;
				try {
					frame = new ComplexFrame();
					frame.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Serif", Font.PLAIN, 10));
		btnNewButton_3.setBounds(1071, 21, 164, 23);
		panel_2.add(btnNewButton_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 187, 119));
		tabbedPane.addTab("Statistics", null, panel_3, null);
		panel_3.setLayout(null);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(472, 107, 96, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		textField.setText(Integer.toString(file2));

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(472, 150, 96, 20);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Integer.toString(file3));

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(629, 107, 96, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(Integer.toString(file4));

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(629, 150, 96, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(Integer.toString(file5));

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(585, 200, 96, 20);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(Integer.toString(file1));

		lblNewLabel_5 = new JLabel("Duplicate Line");
		lblNewLabel_5.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(392, 110, 70, 14);
		panel_3.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Unique Line");
		lblNewLabel_6.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(405, 153, 57, 14);
		panel_3.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Total Captured");
		lblNewLabel_7.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(497, 203, 82, 14);
		panel_3.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("Normal Lines");
		lblNewLabel_8.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(735, 110, 70, 14);
		panel_3.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Complex Data");
		lblNewLabel_9.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(731, 153, 74, 14);
		panel_3.add(lblNewLabel_9);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(360, 59, 487, 186);
		panel_3.add(panel_8);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBackground(new Color(200, 145, 255));
		tabbedPane.addTab("Translation Dictonary", null, tabbedPane_1, null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 187, 119));
		tabbedPane_1.addTab("Normal Data", null, panel_4, null);
		panel_4.setLayout(null);

		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(24, 11, 802, 577);
		panel_4.add(scrollPane_5);
		FileTableModel data6 = new FileTableModel("3_NormalData.txt");
		table_5 = new JTable();
		scrollPane_5.setViewportView(table_5);
		table_5.setModel(data6);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 170, 85));
		panel_6.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(897, 70, 294, 138);
		panel_4.add(panel_6);
		panel_6.setLayout(null);

		btnNewButton = new JButton("Process");
		btnNewButton.setBounds(112, 85, 89, 23);
		panel_6.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NormalTranslate nt = new NormalTranslate();
				nt.setVisible(true);

			}
		});

		JButton btnNewButton_4 = new JButton("Log");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogFrame fo = new LogFrame();
					fo.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(1102, 219, 89, 23);
		panel_4.add(btnNewButton_4);

		panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 187, 119));
		tabbedPane_1.addTab("Complex Data", null, panel_5, null);
		panel_5.setLayout(null);

		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(24, 11, 802, 577);
		panel_5.add(scrollPane_6);
		FileTableModel2 data7 = new FileTableModel2("4_ComplexData.txt");
		table_6 = new JTable();
		scrollPane_6.setViewportView(table_6);
		table_6.setModel(data7);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 170, 85));
		panel_7.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(897, 70, 294, 138);
		panel_5.add(panel_7);
		panel_7.setLayout(null);

		btnNewButton_1 = new JButton("Process");
		btnNewButton_1.setBounds(112, 85, 89, 23);
		panel_7.add(btnNewButton_1);

		btnNewButton_5 = new JButton("Log");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogFrame fo = new LogFrame();
					fo.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(1102, 219, 89, 23);
		panel_5.add(btnNewButton_5);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComplexTranslate nt = new ComplexTranslate();
				nt.setVisible(true);

			}
		});
	}

	public static void write(String note) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Log.txt"), true));
			bw.write(note);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class PreprocessingError extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1443542757443632107L;

	@Override
	public String toString() {
		return "Language is not Selected in Normal tab Drop-Down.";
	}
	
	@Override
	public String getMessage() {
		return "Language is not Selected in Complex tab Drop-Down.";
	}
}
