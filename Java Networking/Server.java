import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server extends JFrame {

	private JPanel contentPane;
	private static JTextField message;
	private static ServerSocket server;
	private static Socket socket;
	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;
	private static JLabel label;
	private static JTextArea textArea;

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
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			server = new ServerSocket(7777);
			socket = server.accept();
			label.setText("Status : Connected Successfully to "+socket.getInetAddress());
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			reader();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "S : Exception in Server()." + e.toString());
		}
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setResizable(false);
		setTitle("Server Messaging");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 374);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Status : Not Connected");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 416, 23);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 416, 239);
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
		message.setBounds(10, 295, 317, 23);
		contentPane.add(message);
		message.setColumns(10);
		
		JButton send_btn = new JButton("Send");
		send_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String messageToSend = message.getText();
				textArea.append("Me : "+messageToSend+"\n");
				printWriter.println(messageToSend);
				printWriter.flush();
				message.setText(null);
				message.requestFocus();
			}
		});
		send_btn.setBounds(337, 295, 89, 23);
		contentPane.add(send_btn);
		
		
		
	}

	private static void reader() {
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
					textArea.append("Client : "+msg+"\n");
				}
				JOptionPane.showMessageDialog(null, "Connection Terminated");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "S : Exception in reader()." + e.toString());
//				e.printStackTrace();
			}

		};
		new Thread(r2).start();
	}

}
