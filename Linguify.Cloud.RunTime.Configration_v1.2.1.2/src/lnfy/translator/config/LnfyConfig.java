package lnfy.translator.config;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class LnfyConfig extends JFrame 
{

	
	public static LnfyConfig objlnfyConfigMain=null;
	public String lnfyConfigCaption="Linguify.Cloud RunTime Configration";

	private JPanel contentPane;
	public JTextField textField_IpAdd;
	public JTextField textField_PortNumber;
	
	public JLabel lblIpAdd = null;
	public JLabel lbl_Port = null;
	public JLabel lbl_PortNumber;
	public JLabel lbl_Msg = null;
	public JLabel lbl_OprationType;
	public JLabel lbl_FileName;
	public JButton btn_OK;
	public JButton btnClose = null;
	public JButton btnBrowseFileName;
	public JButton btnTestConnection;
	public JButton btnTest;
	public JButton btnMsgSend = null;
	public JButton btnReloadConfig = null;
	public JLabel label_Msg;
	public JLabel label_IpAdd;
	public JLabel label_PortNumber;
	public JLabel label_OprationType;
	public JLabel label_FileName;
	public JLabel lblNewLabel = null;
	public JTextField textField_FileName;
	public JTextArea txtrtestconnectionlnend_Msg = null;
	public JComboBox comboBox_SelEvent = null;
	
	public static LnfyConfigWithArgs objLnfyConfigWithArgs=null;
	public static LnfyConfigWithUI objLnfyConfigWithUI=null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
	
		int iArgsCount=0;
		iArgsCount=args.length;
		if(iArgsCount!=0)
		{
			//"-TransPort"	//6001
			//"-TransIP"	//192.168.2.19
			//"-OparationType"	//ReloadConfigration , TestConnection , Message
			//"-Msg"	// ##TESTCONNECTION##LNEND##    // Optional for ReloadConfigration , TestConnection
			
			//-TransPort=6008 -TransIP=192.168.2.19 -OparationType=TestConnection
			//-TransPort=6008 -TransIP=192.168.2.19 -OparationType=ReloadConfigration
			//-TransPort=6008 -TransIP=192.168.2.19 -OparationType=Message -Msg=##TESTCONNECTION##LNEND##

			objLnfyConfigWithArgs=new LnfyConfigWithArgs();
			objLnfyConfigWithArgs.setAndProcessWithArgs(args);
		}
		else
		{
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					try 
					{
						objlnfyConfigMain = new LnfyConfig();
						objLnfyConfigWithUI=new LnfyConfigWithUI(objlnfyConfigMain);
						objlnfyConfigMain.setVisible(true);
						objLnfyConfigWithUI.SetStartUpConfig();
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Create the frame.
	 */
	public LnfyConfig() 
	{
		setResizable(false);
		try
		{
			setTitle("Linguify.Cloud RunTime Configration");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 396);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			lblIpAdd = new JLabel("Linguify.Cloud IP Address");
			lblIpAdd.setBounds(15, 16, 165, 14);
			
			lbl_PortNumber = new JLabel("Linguify.Cloud Port Number");
			lbl_PortNumber.setBounds(15, 44, 165, 14);
			
			textField_IpAdd = new JTextField();
			textField_IpAdd.setBounds(204, 13, 220, 20);
			textField_IpAdd.setText("192.168.2.19");
			textField_IpAdd.setColumns(10);
			
			textField_PortNumber = new JTextField();
			textField_PortNumber.setBounds(204, 40, 220, 20);
			textField_PortNumber.setText("6008");
			textField_PortNumber.setColumns(10);
			
			lbl_Msg = new JLabel("Linguify.Cloud Messages");
			lbl_Msg.setBounds(15, 158, 165, 14);
			
			txtrtestconnectionlnend_Msg = new JTextArea();
			txtrtestconnectionlnend_Msg.setLineWrap(true);
			txtrtestconnectionlnend_Msg.setWrapStyleWord(true);
			txtrtestconnectionlnend_Msg.setText("##TESTCONNECTION##LNEND##");
			txtrtestconnectionlnend_Msg.setBounds(15, 183, 409, 83);
			
			btnClose = new JButton("Close");
			btnClose.setBounds(294, 277, 130, 24);
			
			btnMsgSend = new JButton("Send Message");
			btnMsgSend.setBounds(323, 312, 101, 24);
			
			btnReloadConfig = new JButton("Reload Configration");
			btnReloadConfig.setBounds(15, 312, 150, 24);
			
			contentPane.setLayout(null);
			contentPane.add(txtrtestconnectionlnend_Msg);
			contentPane.add(lblIpAdd);
			contentPane.add(lbl_PortNumber);
			contentPane.add(textField_PortNumber);
			contentPane.add(textField_IpAdd);
			contentPane.add(btnReloadConfig);
			contentPane.add(btnMsgSend);
			contentPane.add(btnClose);
			contentPane.add(lbl_Msg);
			
			label_Msg = new JLabel("::");
			label_Msg.setBounds(190, 158, 14, 14);
			contentPane.add(label_Msg);
			
			label_IpAdd = new JLabel("::");
			label_IpAdd.setBounds(190, 16, 19, 14);
			contentPane.add(label_IpAdd);
			
			label_PortNumber = new JLabel("::");
			label_PortNumber.setBounds(190, 44, 14, 14);
			contentPane.add(label_PortNumber);
			
			lblNewLabel = new JLabel("LinguaNext");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(265, 339, 159, 15);
			contentPane.add(lblNewLabel);
			
			comboBox_SelEvent = new JComboBox();
			comboBox_SelEvent.setBounds(204, 71, 220, 20);
			
			contentPane.add(comboBox_SelEvent);
			
			
			
			btn_OK = new JButton("Ok");
			btn_OK.setBounds(154, 276, 130, 24);
			contentPane.add(btn_OK);
			
			lbl_OprationType = new JLabel("Linguify.Cloud Opration Type");
			lbl_OprationType.setBounds(15, 74, 165, 14);
			contentPane.add(lbl_OprationType);
			
			label_OprationType = new JLabel("::");
			label_OprationType.setBounds(190, 74, 14, 14);
			contentPane.add(label_OprationType);
			
			lbl_FileName = new JLabel("Linguify.Cloud FileName");
			lbl_FileName.setBounds(15, 102, 165, 14);
			contentPane.add(lbl_FileName);
			
			label_FileName = new JLabel("::");
			label_FileName.setBounds(190, 102, 14, 14);
			contentPane.add(label_FileName);
			
			textField_FileName = new JTextField();
			textField_FileName.setBounds(15, 127, 377, 20);
			contentPane.add(textField_FileName);
			textField_FileName.setColumns(10);
			
			btnBrowseFileName = new JButton("...");
			btnBrowseFileName.setBounds(393, 126, 31, 23);
			contentPane.add(btnBrowseFileName);
			
			btnTestConnection = new JButton("Test Connection");
			btnTestConnection.setBounds(170, 312, 150, 24);
			contentPane.add(btnTestConnection);
			
			JButton btn_Refresh = new JButton("Refresh");
			btn_Refresh.setBounds(14, 277, 130, 24);
			contentPane.add(btn_Refresh);
			
			comboBox_SelEvent.setModel(new DefaultComboBoxModel(new String[]
			{
					"------ Select Opration Type ------", "1. Test Connection", "2. Reload Configration", "3. Send Message form Text Area", "4. Send Message form File"
			}));
			
			comboBox_SelEvent.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.SelectComboBox_SelEvent();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
					//	e.printStackTrace();
					}
				}
			});
			btnClose.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.PressBtn_Close();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
				}
			});
			btnMsgSend.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.PressBtn_MsgSend();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
					
					
				}
			});
			btnReloadConfig.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						objLnfyConfigWithUI.PressBtn_ReloadConfig();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
				}
			});
			btn_OK.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.PressBtn_OK();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
				}
			});
			btnBrowseFileName.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.BrowseAndGetFileNameAndData();
					}
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
					}
				}
			});
			btnTestConnection.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						objLnfyConfigWithUI.TestConnection();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
					
				
				}
			});
			btn_Refresh.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						objLnfyConfigWithUI.PressBtn_Refresh();
					} 
					catch (Exception e)
					{
						objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
						//	e.printStackTrace();
					}
				}
			});
			

		}
		catch (Exception e)
		{
			objLnfyConfigWithUI.showMyMessage("Exception :: "+ e.toString()) ;
		}
	}
}
