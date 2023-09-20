package linguasol.Project;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class NormalTranslate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1366481291249862771L;
	private JPanel contentPane;
	private JTable table;
	private List<String> list;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblTranslation;
	private JTextField textField_data;
	private JTextField textField_translation;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	
	
	
	class ReaderWriter{
		ReaderWriter(String path) throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			list = new ArrayList<>();
			while((line = reader.readLine())!=null) {
				Pattern pattern = Pattern.compile("(.*?):\"(.*?)\",");
				Matcher matcher = pattern.matcher(line);
				if(matcher.find()) {
					list.add(matcher.group(2));
				}
			}
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("NormalTranslation.txt"));
			String text = "LinguaNext";
			for(int i=0 ; i<list.size() ; i++) {
				writer.write("\""+list.get(i).toLowerCase()+"\":\""+text+(i+1)+"\",");
				writer.newLine();
			}
			writer.close();
		}
	}
	
	
	class FileTableModel extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1845271751077700307L;
		private static List<String[]> list;

		public FileTableModel(String filePath) {
			try {
				loadFileData(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setColumnIdentifiers(new String[] { "data", "translation" });
			for (int i = 0; i < list.size(); i++) {
				addRow(list.get(i));
			}
		}

		private void loadFileData(String filePath) throws IOException {
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String line;
				list = new ArrayList<>(); 
				while((line=br.readLine())!=null) {
					Pattern pattern = Pattern.compile("\"(.*?)\":\"(.*?)\",");
					Matcher matcher = pattern.matcher(line);
					if(matcher.find()) {
						String[] splitData = {matcher.group(1),matcher.group(2)};
						if(splitData.length == 2) {
							list.add(splitData);
						}
					}
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalTranslate frame = new NormalTranslate();
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
	public NormalTranslate() {
		setTitle("Normal Translation");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1128, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 658, 612);
		contentPane.add(scrollPane);
		
		try {
			new ReaderWriter("3_NormalData.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileTableModel model = new FileTableModel("NormalTranslation.txt");
		table = new JTable();
		Font devanagariFont = new Font("Arial Unicode MS", Font.PLAIN, 12);
		table.setFont(devanagariFont);
		table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				Font devanagariFont = new Font("Arial Unicode MS", Font.PLAIN, 12);
				table.setFont(devanagariFont);
				 int[] selectedRows = table.getSelectedRows();

			        if (selectedRows.length > 0) {
			            StringBuilder dataBuilder = new StringBuilder();
			            StringBuilder translationBuilder = new StringBuilder();

			            for (int rowIndex : selectedRows) {
			                String data = tblmodel.getValueAt(rowIndex, 0).toString();
			                String translation = tblmodel.getValueAt(rowIndex, 1).toString();

			                dataBuilder.append(data.replaceAll("\"", "")).append(",");
			                translationBuilder.append(translation.replaceAll("\"", "").replaceAll(",", "")).append(",");
			            }

			            textField_data.setText(dataBuilder.toString().replaceAll(",$", "").trim());
			            textField_translation.setText(translationBuilder.toString().replaceAll(",$", "").trim());
			        } else {
			            textField_data.setText("");
			            textField_translation.setText("");
			        }
			
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				Font devanagariFont = new Font("Arial Unicode MS", Font.PLAIN, 12);
				table.setFont(devanagariFont);

				int[] selectedRows = table.getSelectedRows(); // Get an array of selected row indices

				if (selectedRows.length > 0) {
				    StringBuilder dataBuilder = new StringBuilder();
				    StringBuilder translationBuilder = new StringBuilder();

				    for (int rowIndex : selectedRows) {
				        // Get data and translation for each selected row
				        String data = tblmodel.getValueAt(rowIndex, 0).toString();
				        String translation = tblmodel.getValueAt(rowIndex, 1).toString();

				        // Append data and translation to the respective builders, separated by ","
				        dataBuilder.append(data.replaceAll("\"", "")).append(",");
				        translationBuilder.append(translation.replaceAll("\"", "").replaceAll(",", "")).append(",");
				    }

				    // Set the text fields with the concatenated data and translation
				    textField_data.setText(dataBuilder.toString().replaceAll(",$", "").trim()); // Remove trailing ","
				    textField_translation.setText(translationBuilder.toString().replaceAll(",$", "").trim()); // Remove trailing ","
				} else {
				    // Handle the case when no rows are selected, e.g., clear the text fields
				    textField_data.setText("");
				    textField_translation.setText("");
				}


			}
		});
//		Font devanagariFont = new Font("Arial Unicode MS", Font.PLAIN, 12);
//        table.setFont(devanagariFont);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data Info : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(678, 147, 426, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Data :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 36, 87, 23);
		panel.add(lblNewLabel);
		
		lblTranslation = new JLabel("Translation :");
		lblTranslation.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTranslation.setBounds(10, 70, 87, 23);
		panel.add(lblTranslation);
		
		textField_data = new JTextField();
		textField_data.setBounds(107, 37, 309, 20);
		panel.add(textField_data);
		textField_data.setColumns(10);
		
		textField_translation = new JTextField();
		textField_translation.setColumns(10);
		textField_translation.setBounds(107, 71, 309, 20);
		panel.add(textField_translation);
		
		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				Font devanagariFont = new Font("Arial Unicode MS", Font.PLAIN, 12);
				table.setFont(devanagariFont);

				int[] selectedRows = table.getSelectedRows();

				if (selectedRows.length > 0) {
					String data = textField_data.getText();
					String translation = textField_translation.getText();

					// Split the data and translation strings by ","
					String[] dataValues = data.split(",");
					String[] translationValues = translation.split(",");

					if (dataValues.length != translationValues.length) {
						JOptionPane.showMessageDialog(null,
								"Data and translation should have the same number of values!");
					} else {
						for (int i = 0; i < selectedRows.length && i < dataValues.length
								&& i < translationValues.length; i++) {
							int rowIndex = selectedRows[i];
							// Set values from the corresponding positions in the arrays
							tblModel.setValueAt(dataValues[i], rowIndex, 0);
							tblModel.setValueAt(translationValues[i], rowIndex, 1);
						}

						JOptionPane.showMessageDialog(null, "Data Saved for " + selectedRows.length + " rows!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No rows selected. Data not saved!");
				}
			}
		});
		btnNewButton.setBounds(107, 102, 89, 23);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Export");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter writer = new FileWriter("Exported_NormalData.txt");

                    for (int row = 0; row < table.getRowCount(); row++) {
                        String column1 = table.getValueAt(row, 0).toString();
                        String column2 = table.getValueAt(row, 1).toString();
                        String line = "\"" + column1 + "\":\"" + column2 + "\",\n";
                        writer.write(line);
                    }
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Data Exported!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(327, 102, 89, 23);
		panel.add(btnNewButton_1);
	}
}
