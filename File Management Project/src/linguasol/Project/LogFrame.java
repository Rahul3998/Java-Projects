package linguasol.Project;

import java.awt.EventQueue;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class LogFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7197962108782117230L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogFrame frame = new LogFrame();
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
	public LogFrame() throws IOException {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 551, 241);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		 
		try {
			new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader("Log.txt"));
//			String line;
//			while((line = reader.readLine())!=null) {
//				list.add(line);
//			}
			textPane.read(reader, null);
			reader.close();
			textPane.requestFocus();
			
//			for(int i=0 ; i<list.size() ; i++) {
//				textPane.setText(list.get(i));
//			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
