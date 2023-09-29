import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField ip_textField;
	private JTextField port_textField;
	private JTextField message;
	private static JTextArea textArea;
	private static JLabel label1;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

	/**
	 * Launch the application.
	 * Credits : Rahul Adagale.
	 * E-Mail : rahuladagale3998@gmail.com
	 * GitHub : https://github.com/Rahul3998
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		setTitle("Client Messaging [End- User]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 423);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Connection Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 416, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ip_textField = new JTextField();
		ip_textField.setBounds(88, 33, 96, 20);
		panel.add(ip_textField);
		ip_textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP Address : ");
		lblNewLabel.setBounds(10, 33, 80, 20);
		panel.add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port : ");
		lblPort.setBounds(250, 33, 49, 20);
		panel.add(lblPort);
		
		port_textField = new JTextField();
		port_textField.setBounds(298, 33, 96, 20);
		panel.add(port_textField);
		port_textField.setColumns(10);
		
		JButton connect_btn = new JButton("Connect");
		connect_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipAddress = ip_textField.getText();
				String portNo = port_textField.getText();
				if(ipAddress.isEmpty() || portNo.isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Please Fill Complete Details!");
				}else {
					int finalPortNumber = Integer.parseInt(portNo);
					try {
						socket = new Socket(ipAddress,finalPortNumber);
						label1.setText("Status : Connected ");
						printWriter = new PrintWriter(socket.getOutputStream(), true);
						bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						reader();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "C : Exception in Client()." + ex.toString());
					}
					
				}
			}
		});
		connect_btn.setBounds(164, 64, 89, 23);
		panel.add(connect_btn);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 416, 176);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		message = new JTextField();
		message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					String messageToSend = message.getText();
					textArea.append("Me : "+messageToSend+"\n");
					printWriter.println(messageToSend);
					if(messageToSend.toLowerCase().equals("exit")) {
						JOptionPane.showMessageDialog(null, "Chat is been terminated!");
						textArea.append("Chat is been terminated!");
						message.setText(null);
						message.setEditable(false);
						return;
					}
					printWriter.flush();
					message.setText(null);
					message.requestFocus();
				}
			}
		});
		message.setBounds(10, 328, 318, 27);
		contentPane.add(message);
		message.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String messageToSend = message.getText();
				textArea.append("Me : "+messageToSend+"\n");
				printWriter.println(messageToSend);
				printWriter.flush();
				message.setText(null);
				message.requestFocus();
			}
		});
		btnNewButton.setBounds(337, 330, 89, 23);
		contentPane.add(btnNewButton);
		
		label1 = new JLabel("Status : Not Connected");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(10, 108, 416, 27);
		contentPane.add(label1);
		
		
	}
	
	private void reader() {
		Runnable r2 = () -> {
			try {
				while (!socket.isClosed()) {
					String msg = bufferedReader.readLine();
					if (msg.equals("exit")) {
						textArea.append("Chat is Terminated!");
						message.setEnabled(false);
						printWriter.close();
						bufferedReader.close();
						socket.close();
						break;
					}
					textArea.append("Client : " + msg + "\n");
				}
				JOptionPane.showMessageDialog(null, "C : Connection Ternminated");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "C : Exception in reader()." + e.toString());
			}

		};
		new Thread(r2).start();
	}
}
