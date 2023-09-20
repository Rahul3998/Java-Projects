package linguasol.Project;

import java.awt.EventQueue;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NormalFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232575423393840105L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	class FileTableModel extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1845271751077700307L;
		private List<String[]> data = new ArrayList<>();

		public FileTableModel(String filePath) {
			loadFileData(filePath);
			setColumnIdentifiers(new String[] { "data", "star dictonary" });
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
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalFrame frame = new NormalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public NormalFrame() throws IOException {
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 389, 386);
		contentPane.add(scrollPane);

		List<String> list = new ArrayList<>();
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader("3_NormalData.txt"));

			String line;
			while ((line = bf.readLine()) != null) {
				Pattern pattern = Pattern.compile("\"(.*?)\",");
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					list.add(matcher.group(1));
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("NormalStarDictonary.txt"));
		for(int i=0 ; i<list.size() ; i++) {
			writer.write(""+list.get(i).toLowerCase()+""+":"+"###"+list.get(i).toUpperCase()+"###"+",");
			writer.newLine();
		}
		writer.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		FileTableModel n = new FileTableModel("NormalStarDictonary.txt");
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(n);
	}

}
