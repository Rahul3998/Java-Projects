package linguasol.Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManagement {

	private JFrame frmFileManagementProject;
	private JTextField filePath;
	private String fileName;
	private String exp;

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
					FileManagement window = new FileManagement();
					window.frmFileManagementProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileManagementProject = new JFrame();
		frmFileManagementProject.setTitle("File Management Project");
		frmFileManagementProject.setFont(new Font("SansSerif", Font.BOLD, 12));
		frmFileManagementProject.setForeground(new Color(240, 240, 240));
		frmFileManagementProject.setResizable(false);
		frmFileManagementProject.getContentPane().setBackground(new Color(194, 194, 194));
		frmFileManagementProject.setBounds(100, 100, 565, 260);
		frmFileManagementProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFileManagementProject.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("File Management ");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		lblNewLabel.setBounds(174, 27, 169, 49);
		frmFileManagementProject.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("File ");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 98, 49, 14);
		frmFileManagementProject.getContentPane().add(lblNewLabel_1);

		filePath = new JTextField();
		filePath.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 11));
		filePath.setBounds(44, 97, 393, 20);
		frmFileManagementProject.getContentPane().add(filePath);
		filePath.setColumns(10);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser file = new JFileChooser("C:\\Users\\Rahul.Adagale\\Downloads");
				file.setMultiSelectionEnabled(true);
				file.setAcceptAllFileFilterUsed(false);

				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("All Files", "xls", "java", "txt", "lstr",
						"js");
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Java File (.java)", "java");
				FileNameExtensionFilter filter3 = new FileNameExtensionFilter("LSTR File (.lstr)", "lstr");
				FileNameExtensionFilter filter4 = new FileNameExtensionFilter("Excel File (.xls)", "xls");
				FileNameExtensionFilter filter5 = new FileNameExtensionFilter("Text File (.txt)", "txt");
				FileNameExtensionFilter filter6 = new FileNameExtensionFilter("Javascript File (.js)", "js");

				file.addChoosableFileFilter(filter1);
				file.addChoosableFileFilter(filter2);
				file.addChoosableFileFilter(filter3);
				file.addChoosableFileFilter(filter4);
				file.addChoosableFileFilter(filter5);
				file.addChoosableFileFilter(filter6);

				int select = file.showOpenDialog(null);
				if (select == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "File Selected Successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					File fileDetails = file.getSelectedFile();
					fileName = fileDetails.getAbsolutePath();
					filePath.setText(fileName);
				}
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 12));
		btnNewButton.setBounds(447, 95, 89, 23);
		frmFileManagementProject.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Preprocessing");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileName == null) {
					try {
						JOptionPane.showMessageDialog(null, "Exception Recorded!", "Exception!",
								JOptionPane.ERROR_MESSAGE);
						throw new WelcomeTab();
					} catch (Exception ex) {
						exp = ex.toString();
						write(exp);
					}
				} else {
					try {
						new FileOperations(fileName);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton_1.setFont(new Font("Serif", Font.PLAIN, 12));
		btnNewButton_1.setBounds(202, 145, 129, 30);
		frmFileManagementProject.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Log");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("Log.txt");
				if (file.exists() == false) {
					JOptionPane.showMessageDialog(null, "File Does not exists in path");
					return;
				} else {
					try {
						LogFrame lf = new LogFrame();
						lf.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_2.setBounds(447, 189, 89, 23);
		frmFileManagementProject.getContentPane().add(btnNewButton_2);
	}

	public static void write(String note) {
		try {
			File file = new File("Log.txt");
			file.createNewFile();
			String filename = file.getAbsolutePath();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename),true));
			bw.write(note);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class WelcomeTab extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6585790357828691103L;

	@Override
	public String toString() {
		return "There is Exception in Welcome Page, file is not imported. ";
	}

	@Override
	public String getMessage() {
		return "There is Exception in Welcome Page, file is not imported. ";
	}
}
