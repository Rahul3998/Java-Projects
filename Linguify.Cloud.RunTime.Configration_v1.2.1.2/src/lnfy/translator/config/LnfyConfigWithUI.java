package lnfy.translator.config;

import java.awt.FileDialog;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JOptionPane;

public class LnfyConfigWithUI
{

	LnfyConfig objlnfyConfigMain=null;
	LnfyConfigGeneral objLnfyConfigGeneral=null;
	
	public LnfyConfigWithUI(LnfyConfig objlnfyConfigFrame)
	{
		this.objlnfyConfigMain=objlnfyConfigFrame;
		this.objLnfyConfigGeneral=new LnfyConfigGeneral();
	}
	
	// Displays a message dialog box with the specified message.
	void showMyMessage(String szInShowMsg) 
	{
		try
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,szInShowMsg , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);

		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
		//	e.printStackTrace();
		}
	}
	
	// Handles the selection of an item from a combo box and updates the UI elements accordingly based on the selected operation type.
	void SelectComboBox_SelEvent()
	{
		try
		{
			String szMsg="";
			szMsg=String.valueOf(objlnfyConfigMain.comboBox_SelEvent.getSelectedItem());
			
			if(szMsg.equals("------ Select Opration Type ------")==true)
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			//	btn_OK.enable(false);
			//	btnReloadConfig.enable(false);
				

				objlnfyConfigMain.lbl_FileName.setEnabled(false);
				objlnfyConfigMain.label_FileName.setEnabled(false);
				objlnfyConfigMain.textField_FileName.setEnabled(false);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(false);;
				
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
				objlnfyConfigMain.lbl_Msg.setEnabled(false);
				objlnfyConfigMain.label_Msg.setEnabled(false);
			}
			else if(szMsg.equals("2. Reload Configration")==true)
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			//	btn_OK.enable(false);
			//	btnReloadConfig.enable(true);

				objlnfyConfigMain.lbl_FileName.setEnabled(false);
				objlnfyConfigMain.label_FileName.setEnabled(false);
				objlnfyConfigMain.textField_FileName.setEnabled(false);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(false);
				
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
				objlnfyConfigMain.lbl_Msg.setEnabled(false);
				objlnfyConfigMain.label_Msg.setEnabled(false);
				
			}
			else if(szMsg.equals("3. Send Message form Text Area")==true)
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(true);
			//	btn_OK.enable(true);
			//	btnReloadConfig.enable(false);
				
				objlnfyConfigMain.lbl_FileName.setEnabled(false);
				objlnfyConfigMain.label_FileName.setEnabled(false);
				objlnfyConfigMain.textField_FileName.setEnabled(false);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(false);
				
				objlnfyConfigMain.lbl_Msg.setEnabled(true);
				objlnfyConfigMain.label_Msg.setEnabled(true);
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(true);
				
				

			}
			else if(szMsg.equals("4. Send Message form File")==true)
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			//	btn_OK.enable(true);
			//	btnReloadConfig.enable(false);
				
				objlnfyConfigMain.lbl_FileName.setEnabled(true);
				objlnfyConfigMain.label_FileName.setEnabled(true);
				objlnfyConfigMain.textField_FileName.setEnabled(true);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(true);
				
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(true);
				objlnfyConfigMain.lbl_Msg.setEnabled(false);
				objlnfyConfigMain.label_Msg.setEnabled(false);
			
			}
			else if(szMsg.equals("1. Test Connection")==true)
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			//	btn_OK.enable(true);
			//	btnReloadConfig.enable(false);
				
				objlnfyConfigMain.lbl_FileName.setEnabled(false);
				objlnfyConfigMain.label_FileName.setEnabled(false);
				objlnfyConfigMain.textField_FileName.setEnabled(false);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(false);
				
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
				objlnfyConfigMain.lbl_Msg.setEnabled(false);
				objlnfyConfigMain.label_Msg.setEnabled(false);
			}
			else
			{
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			//	btn_OK.enable(false);
			//	btnReloadConfig.enable(false);
				
				objlnfyConfigMain.lbl_FileName.setEnabled(false);
				objlnfyConfigMain.label_FileName.setEnabled(false);
				objlnfyConfigMain.textField_FileName.setEnabled(false);
				objlnfyConfigMain.btnBrowseFileName.setEnabled(false);
				
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
				objlnfyConfigMain.lbl_Msg.setEnabled(false);
				objlnfyConfigMain.label_Msg.setEnabled(false);
				
			}	
			try
			{
			////objlnfyConfigFrame.revalidate();
			objlnfyConfigMain.invalidate();
			objlnfyConfigMain.validate();
			objlnfyConfigMain.repaint();
			}
			catch (Exception e)
			{
			//	e.printStackTrace();
			}
		} 
		catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
	}

	// Clears input fields and resets the UI to an initial state.
	void PressBtn_Refresh()
	{
		try
		{
			objlnfyConfigMain.textField_IpAdd.setText("");
			objlnfyConfigMain.textField_PortNumber.setText("");
			objlnfyConfigMain.textField_FileName.setText("");
			objlnfyConfigMain.txtrtestconnectionlnend_Msg.setText("");

			objlnfyConfigMain.comboBox_SelEvent.setSelectedItem("------ Select Opration Type ------");
			try
			{
				objlnfyConfigMain.invalidate();
				objlnfyConfigMain.validate();
				objlnfyConfigMain.repaint();
			}
			catch (Exception e)
			{
			//	e.printStackTrace();
			}
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
	}
	
	// Closes the application by calling System.exit(0).
	void PressBtn_Close()
	{
		try
		{
			System.exit(0);
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
	}
	
	// Sends a message to a specified IP address and port, with validation for IP address and port.
	void PressBtn_MsgSend()
	{
		try
		{
			String szInIpAddress="";
			String szInPortNo="";
			String szInMsg="";
			String szRetMsg="";
			boolean bValidateIpAddressStatus=false;
			boolean bValidatePorfStatus=false;
			szInIpAddress=objlnfyConfigMain.textField_IpAdd.getText();
			szInPortNo=objlnfyConfigMain.textField_PortNumber.getText();
			szInMsg=objlnfyConfigMain.txtrtestconnectionlnend_Msg.getText();
			
			
			bValidateIpAddressStatus=objLnfyConfigGeneral.validate_IpAddress(szInIpAddress) ;
			
			int iPort=0;
			try
			{
				iPort=Integer.parseInt(szInPortNo);
				bValidatePorfStatus=true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if(iPort==0)
			{
				bValidatePorfStatus=false;
			}
			
			
			
			if(bValidateIpAddressStatus==true && bValidatePorfStatus==true && szInMsg.length()>0)
			{
				szRetMsg=objLnfyConfigGeneral.sendSpecificMsgToParser(szInIpAddress, iPort,szInMsg);
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidateIpAddressStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Ip Address. Please enter valid Ip Address.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidatePorfStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Port Number. Please enter valid Port Number.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(szInMsg.length()<=0)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Please enter Msg/Data.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
		
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
		
		
	}
	// Reloads the configuration by sending a message to a specified IP address and port, with validation for IP address and port.
	void PressBtn_ReloadConfig()
	{
		try
		{
			String szInIpAddress="";
			String szInPortNo="";
			String szRetMsg="";
			boolean bValidateIpAddressStatus=false;
			boolean bValidatePorfStatus=false;
			szInIpAddress=objlnfyConfigMain.textField_IpAdd.getText();
			szInPortNo=objlnfyConfigMain.textField_PortNumber.getText();
			bValidateIpAddressStatus=objLnfyConfigGeneral.validate_IpAddress(szInIpAddress) ;
			
			int iPort=0;
			try
			{
				iPort=Integer.parseInt(szInPortNo);
				bValidatePorfStatus=true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if(iPort==0)
			{
				bValidatePorfStatus=false;
			}
			if(bValidateIpAddressStatus==true && bValidatePorfStatus==true)
			{
				szRetMsg=objLnfyConfigGeneral.sendReLoadMsgToParser(szInIpAddress, iPort);
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidateIpAddressStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Ip Address. Please enter valid Ip Address.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidatePorfStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Port Number. Please enter valid Port Number.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
		} 
		catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
		
	}
	// Performs different actions based on the selected operation type, such as sending a message or reloading the configuration, with input validation.
	void PressBtn_OK()
	{
		try
		{
			String szInIpAddress="";
			String szInPortNo="";
			String szInMsg="";
			String szRetMsg="";
			boolean bValidateIpAddressStatus=false;
			boolean bValidatePorfStatus=false;
			szInIpAddress=objlnfyConfigMain.textField_IpAdd.getText();
			szInPortNo=objlnfyConfigMain.textField_PortNumber.getText();
			szInMsg=objlnfyConfigMain.txtrtestconnectionlnend_Msg.getText();
			
			
			bValidateIpAddressStatus=objLnfyConfigGeneral.validate_IpAddress(szInIpAddress) ;
			
			int iPort=0;
			try
			{
				iPort=Integer.parseInt(szInPortNo);
				bValidatePorfStatus=true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if(iPort==0)
			{
				bValidatePorfStatus=false;
			}
			
			
			String szMsg="";
			szMsg=String.valueOf(objlnfyConfigMain.comboBox_SelEvent.getSelectedItem());
			
			if(bValidateIpAddressStatus==true && bValidatePorfStatus==true)
			{
				if(szMsg.equals("------ Select Opration Type ------")==true)
				{
					//Nothing
				}
				else if(szMsg.equals("2. Reload Configration")==true)
				{
					szRetMsg=objLnfyConfigGeneral.sendReLoadMsgToParser(szInIpAddress, iPort);
					JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
				}
				else if(szMsg.equals("3. Send Message form Text Area")==true)
				{
					if(szInMsg.length()>0)
					{
						szRetMsg=objLnfyConfigGeneral.sendSpecificMsgToParser(szInIpAddress, iPort,szInMsg);
						JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
					}
					else if(szInMsg.length()<=0)
					{
						JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Please enter Msg/Data.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(szMsg.equals("4. Send Message form File")==true)
				{
					if(szInMsg.length()>0)
					{
						szRetMsg=objLnfyConfigGeneral.sendSpecificMsgToParser(szInIpAddress, iPort,szInMsg);
						JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
					}
					else if(szInMsg.length()<=0)
					{
						JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Please enter Msg/Data.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(szMsg.equals("1. Test Connection")==true)
				{
					szRetMsg=objLnfyConfigGeneral.sendTestConnectionMsgToParser(szInIpAddress, iPort);
					JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//Nothing
				}	
			}
			else if(bValidateIpAddressStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Ip Address. Please enter valid Ip Address.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidatePorfStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Port Number. Please enter valid Port Number.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
	
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
		
		
	
	
		
	}
	// Opens a file dialog for selecting a file and then reads and displays the content of the selected file in a text area.
	void BrowseAndGetFileNameAndData()
	{
		try
		{
			FileDialog fd = new FileDialog(objlnfyConfigMain, "Choose a file",FileDialog.LOAD);
			fd.setFilenameFilter(new FilenameFilter(){
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.endsWith(".txt");
                }
            });
			//fd.setDirectory("C:\\");
			fd.setVisible(true);
			String szDir = fd.getDirectory();
			fd.setAlwaysOnTop(true);
			String filename = fd.getFile();
			if (filename == null) 
			{
			}
			else
			{
			//	System.out.println("You chose " + szDir + filename);
				objlnfyConfigMain.textField_FileName.setText(szDir + filename);
			}
			if (objlnfyConfigMain.textField_FileName.getText().equals("") || objlnfyConfigMain.textField_FileName.getText().equals("Please browse dict path...")) 
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Please select a file", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				String szFileData="";
				szFileData=objLnfyConfigGeneral.readFile(objlnfyConfigMain.textField_FileName.getText());
				objlnfyConfigMain.txtrtestconnectionlnend_Msg.setText(szFileData);
			}
		
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Tests the connection to a specified IP address and port, with validation for IP address and port.
	void TestConnection()
	{
		
		try
		{
			String szInIpAddress="";
			String szInPortNo="";
			String szRetMsg="";
			boolean bValidateIpAddressStatus=false;
			boolean bValidatePorfStatus=false;
			szInIpAddress=objlnfyConfigMain.textField_IpAdd.getText();
			szInPortNo=objlnfyConfigMain.textField_PortNumber.getText();
			bValidateIpAddressStatus=objLnfyConfigGeneral.validate_IpAddress(szInIpAddress) ;
			
			int iPort=0;
			try
			{
				iPort=Integer.parseInt(szInPortNo);
				bValidatePorfStatus=true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if(iPort==0)
			{
				bValidatePorfStatus=false;
			}
			if(bValidateIpAddressStatus==true && bValidatePorfStatus==true)
			{
				szRetMsg=objLnfyConfigGeneral.sendTestConnectionMsgToParser(szInIpAddress, iPort);
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+szRetMsg, objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidateIpAddressStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Ip Address. Please enter valid Ip Address.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(bValidatePorfStatus==false)
			{
				JOptionPane.showMessageDialog(objlnfyConfigMain,"Status :: "+"Invalid Port Number. Please enter valid Port Number.", objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
			}
		} 
		catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		//	e.printStackTrace();
		}
		
	
	}
	// Sets the initial configuration for the UI, disabling certain buttons and UI elements.
	void SetStartUpConfig()
	{
		try
		{
			
			objlnfyConfigMain.btnReloadConfig.setEnabled(false);
	//		objlnfyConfigMain.btnTestConnection.setEnabled(false);
			objlnfyConfigMain.btnMsgSend.setEnabled(false);
			
			
			objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
		//	objlnfyConfigFrame.btnReloadConfig.enable(false);
			

			objlnfyConfigMain.lbl_FileName.setEnabled(false);
			objlnfyConfigMain.label_FileName.setEnabled(false);
			objlnfyConfigMain.textField_FileName.setEnabled(false);
			objlnfyConfigMain.btnBrowseFileName.setEnabled(false);
			
			objlnfyConfigMain.txtrtestconnectionlnend_Msg.setEnabled(false);
			objlnfyConfigMain.lbl_Msg.setEnabled(false);
			objlnfyConfigMain.label_Msg.setEnabled(false);
			
			objlnfyConfigMain.comboBox_SelEvent.setSelectedItem("------ Select Opration Type ------");
			try
			{
				objlnfyConfigMain.invalidate();
				objlnfyConfigMain.validate();
				objlnfyConfigMain.repaint();
			}
			catch (Exception e)
			{
			//	e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(objlnfyConfigMain,"Exception :: "+ e.toString() , objlnfyConfigMain.lnfyConfigCaption,JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
