package linguasol.Project;

import java.awt.EventQueue;





import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Preprocessing extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5607394511900963462L;
	private JPanel contentPane;
	private JTable table;
	private JTextField Captured_textField;
	private JTextField captured_size;
	private JTextField duplicate_size;
	private JTextField unique_size;
	private JTextField normal_size;
	private JTextField complex_size;
	private int file1, file2, file3, file4, file5;
	private JTextField Duplicate_textField;
	private JTextField Unique_textField;
	private JTable table_2;
	private JTable table_3;
	private JTextField Normal_textField;
	private JTextField complex_textField;
	private JTable table_4;
	private JTable table_5;
	public int size1, size2, size3, size4, size5;

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
					Preprocessing frame = new Preprocessing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
						} else {}
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

	/**
	 * Create the frame.
	 * @throws IOException 
	 */

	public Preprocessing() throws IOException {
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1289, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(177, 100, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1255, 636);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 186, 117));
		tabbedPane.addTab("Captured", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 1211, 528);
		panel.add(scrollPane);
		Captured_textField = new JTextField();
		Captured_textField.setEditable(false);
		Captured_textField.setBounds(1082, 577, 139, 20);
		panel.add(Captured_textField);
		Captured_textField.setColumns(10);
		FileTableModel data = new FileTableModel("1_Capture.txt");
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
			Captured_textField.setText("Total Lines : "+list.size());
			file1 = list.size();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setModel(data);
		

		JLabel lblNewLabel = new JLabel("Captured File Data");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		lblNewLabel.setBounds(451, 11, 183, 27);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 186, 117));
		tabbedPane.addTab("Unique", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(54, 74, 564, 492);
		panel_1.add(scrollPane_1);
		Duplicate_textField = new JTextField();
		Duplicate_textField.setEditable(false);
		Duplicate_textField.setBounds(488, 577, 130, 20);
		panel_1.add(Duplicate_textField);
		Duplicate_textField.setColumns(10);
		FileTableModel data2 = new FileTableModel("3_Duplicate.txt");
		List<String> list1 = new ArrayList<>();
		BufferedReader bf1 = new BufferedReader(new FileReader("3_Duplicate.txt"));
		String line1;
		while((line1 = bf1.readLine())!=null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line1);
			while(matcher.find()) {
				list1.add(matcher.group(1));
			}
		}
		bf1.close();
		Duplicate_textField.setText("Total Lines : "+list1.size());
		file2 = list1.size();
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		table_2.setModel(data2);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(651, 74, 564, 492);
		panel_1.add(scrollPane_2);
		Unique_textField = new JTextField();
		Unique_textField.setEditable(false);
		Unique_textField.setBounds(1085, 577, 130, 20);
		panel_1.add(Unique_textField);
		FileTableModel data3 = new FileTableModel("3_UniqueText.txt");
		List<String> list2 = new ArrayList<>();
		BufferedReader bf2 = new BufferedReader(new FileReader("3_UniqueText.txt"));
		String line2;
		while((line2 = bf2.readLine())!=null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line2);
			while(matcher.find()) {
				list2.add(matcher.group(1));
			}
		}
		bf2.close();
		Unique_textField.setText("Total Lines : "+list2.size());
		file3 = list2.size();
		Unique_textField.setColumns(10);
		table_3 = new JTable();
		scrollPane_2.setViewportView(table_3);
		table_3.setModel(data3);

		JLabel lblNewLabel_1 = new JLabel("Duplicate Data : ");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(54, 47, 103, 25);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Unique Data : ");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(651, 49, 95, 23);
		panel_1.add(lblNewLabel_2);

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
//		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 11));
//		btnNewButton.setBounds(453, 49, 165, 23);
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
//		btnNewButton_1.setFont(new Font("Serif", Font.PLAIN, 11));
//		btnNewButton_1.setBounds(1050, 49, 165, 23);
//		panel_1.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 186, 117));
		tabbedPane.addTab("Complex", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(54, 74, 564, 492);
		panel_2.add(scrollPane_3);
		Normal_textField = new JTextField();
		Normal_textField.setEditable(false);
		Normal_textField.setBounds(489, 577, 130, 20);
		panel_2.add(Normal_textField);
		Normal_textField.setColumns(10);
		FileTableModel data4 = new FileTableModel("3_NormalData.txt");
		List<String> list3 = new ArrayList<>();
		BufferedReader bf3 = new BufferedReader(new FileReader("3_NormalData.txt"));
		String line3;
		while((line3 = bf3.readLine())!=null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line3);
			while(matcher.find()) {
				list3.add(matcher.group(1));
			}
		}
		bf3.close();
		Normal_textField.setText("Total Lines : "+list3.size());
		file4 = list3.size();
		table_4 = new JTable();
		scrollPane_3.setViewportView(table_4);
		table_4.setModel(data4);
		table_4.getColumn(1).setPreferredWidth(120);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(651, 74, 564, 492);
		panel_2.add(scrollPane_4);
		complex_textField = new JTextField();
		complex_textField.setEditable(false);
		complex_textField.setBounds(1085, 577, 130, 20);
		panel_2.add(complex_textField);
		complex_textField.setColumns(10);
		FileTableModel2 data5 = new FileTableModel2("4_ComplexData.txt");
		List<String> list4 = new ArrayList<>();
		BufferedReader bf4 = new BufferedReader(new FileReader("4_ComplexData.txt"));
		String line4;
		while((line4 = bf4.readLine())!=null) {
			Pattern pattern = Pattern.compile("\"(.*?)\",");
			Matcher matcher = pattern.matcher(line4);
			while(matcher.find()) {
				list4.add(matcher.group(1));
			}
		}
		bf4.close();
		complex_textField.setText("Total Lines : "+list4.size());
		file5 = list4.size();
		table_5 = new JTable();
		scrollPane_4.setViewportView(table_5);
		table_5.setModel(data5);

		JLabel lblNewLabel_3 = new JLabel("Normal Text : ");
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(54, 47, 112, 25);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Complex Text :");
		lblNewLabel_4.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(651, 49, 112, 23);
		panel_2.add(lblNewLabel_4);

		JButton btnNewButton_2 = new JButton("Prepare Star Dictonary");
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
		btnNewButton_2.setFont(new Font("Serif", Font.PLAIN, 11));
		btnNewButton_2.setBounds(453, 49, 165, 23);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Prepare Star Dictonary");
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
		btnNewButton_3.setFont(new Font("Serif", Font.PLAIN, 11));
		btnNewButton_3.setBounds(1050, 49, 165, 23);
		panel_2.add(btnNewButton_3);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 186, 117));
		tabbedPane.addTab("Statistics", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Captured Lines : ");
		lblNewLabel_7.setFont(new Font("Serif", Font.BOLD, 10));
		lblNewLabel_7.setBounds(521, 222, 84, 14);
		panel_6.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Duplicate Lines ");
		lblNewLabel_8.setFont(new Font("Serif", Font.BOLD, 10));
		lblNewLabel_8.setBounds(348, 157, 84, 14);
		panel_6.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Unique lines ");
		lblNewLabel_9.setFont(new Font("Serif", Font.BOLD, 10));
		lblNewLabel_9.setBounds(361, 107, 76, 14);
		panel_6.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Normal Lines  ");
		lblNewLabel_10.setFont(new Font("Serif", Font.BOLD, 10));
		lblNewLabel_10.setBounds(808, 107, 76, 14);
		panel_6.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Complex Lines ");
		lblNewLabel_11.setFont(new Font("Serif", Font.BOLD, 10));
		lblNewLabel_11.setBounds(808, 157, 84, 14);
		panel_6.add(lblNewLabel_11);

		captured_size = new JTextField();
		captured_size.setEditable(false);
		captured_size.setBounds(603, 219, 96, 20);
		panel_6.add(captured_size);
		captured_size.setColumns(10);
		captured_size.setText("" + file1 + "");

		duplicate_size = new JTextField();
		duplicate_size.setEditable(false);
		duplicate_size.setBounds(431, 154, 96, 20);
		panel_6.add(duplicate_size);
		duplicate_size.setColumns(10);
		duplicate_size.setText("" + file2 + "");

		unique_size = new JTextField();
		unique_size.setEditable(false);
		unique_size.setBounds(431, 104, 96, 20);
		panel_6.add(unique_size);
		unique_size.setColumns(10);
		unique_size.setText("" + file3 + "");

		normal_size = new JTextField();
		normal_size.setEditable(false);
		normal_size.setBounds(702, 104, 96, 20);
		panel_6.add(normal_size);
		normal_size.setColumns(10);
		normal_size.setText("" + file4 + "");

		complex_size = new JTextField();
		complex_size.setEditable(false);
		complex_size.setBounds(702, 154, 96, 20);
		panel_6.add(complex_size);
		complex_size.setColumns(10);
		complex_size.setText("" + file5 + "");

		JLabel lblNewLabel_13 = new JLabel("Statistics");
		lblNewLabel_13.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
		lblNewLabel_13.setBounds(572, 49, 96, 27);
		panel_6.add(lblNewLabel_13);

	}
}
