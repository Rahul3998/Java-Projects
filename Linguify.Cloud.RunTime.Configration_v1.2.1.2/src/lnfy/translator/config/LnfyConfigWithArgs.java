package lnfy.translator.config;

public class LnfyConfigWithArgs
{

	public LnfyConfigWithArgs()
	{
		objLnfyConfigGeneral=new LnfyConfigGeneral();
	}
	
	public void setLogLevel(String[] szInArgs)
	{
//		long lStartTime=0;
//		String szFunDisc = szLnfyClassName + "::setLogLevel";
		try	
		{

//			lStartTime=LnfyGlobalVarDeclaration.objGlobleLnfyLogger.writeTimeLogStart(szFunDisc);


		

			int iCtr=0;
			int iArgsCount=0;
			String szArgsData1="";
			String szArgsData2="";	

			String szArgsTmp="";
			String[] szArgsTmpSplit={};

			iArgsCount=szInArgs.length;
			for(iCtr=0;iCtr<iArgsCount;iCtr++)
			{
				szArgsTmp=szInArgs[iCtr];
				szArgsTmpSplit=szArgsTmp.split("=");

				if(szArgsTmpSplit.length==2)
				{
					szArgsData1=szArgsTmpSplit[0];
					szArgsData2=szArgsTmpSplit[1];

				
					if(szArgsData1.charAt(0)=='-' && szArgsData2.charAt(0)!='-' )
					//if(szArgsData1.startsWith("-")==true && szArgsData2.startsWith("-")==false )
					{
						if(szArgsData1.equals("-LogLevel")==true)
						{
						//	LnfyGlobalVarDeclaration.szGlobleLogLevel=szArgsData2;
						}
					}
				}
			}

		} 
		catch (Exception e) 
		{
		//	LnfyGlobalVarDeclaration.objGlobleLnfyLogger.writeExceptionLog(szFunDisc, e);
		}
		finally
		{
		//	LnfyGlobalVarDeclaration.objGlobleLnfyLogger.writeTimeLogEnd(lStartTime,szFunDisc);
		}
	}
	
	public LnfyConfigGeneral objLnfyConfigGeneral=null;
	
	String szTranslatorPort="";
	String szTranslatorIPAdd="";
	String szTranslatorOparationType="";
	String szTranslatorMsg="";
	
	public void setAndProcessWithArgs(String[] szInArgs)
	{
	//	long lStartTime=0;
	//	String szFunDisc = szLnfyClassName + "::setIpAndPort";
		try 
		{
			int iCtr=0;
			int iArgsCount=0;
			String szArgsData1="";
			String szArgsData2="";	

			String szArgsTmp="";
			String[] szArgsTmpSplit={};

		//	lStartTime=objLnfyLogger.writeTimeLogStart(szFunDisc);


			szTranslatorPort="";
			szTranslatorIPAdd="";
			szTranslatorOparationType="";
			szTranslatorMsg="";

			iArgsCount=szInArgs.length;
			for(iCtr=0;iCtr<iArgsCount;iCtr++)
			{
				szArgsTmp=szInArgs[iCtr];
				szArgsTmpSplit=szArgsTmp.split("=");

				if(szArgsTmpSplit.length==2)
				{

					szArgsData1=szArgsTmpSplit[0];
					szArgsData2=szArgsTmpSplit[1];


					if(szArgsData1.charAt(0)=='-' && szArgsData2.charAt(0)!='-' )
					//if(szArgsData1.startsWith("-")==true && szArgsData2.startsWith("-")==false )
					{
						//	-TransPort=6001 -H2ServerIP=192.168.2.21 -H2ServerPort=7001 -evLogIP=192.168.2.21 -evLogPort=7010
						if(szArgsData1.equals("-TransPort")==true)
						{
							szTranslatorPort=szArgsData2;
						}
						else if(szArgsData1.equals("-TransIP")==true)
						{
							szTranslatorIPAdd=szArgsData2;
						}
						else if(szArgsData1.equals("-OparationType")==true)
						{
							szTranslatorOparationType=szArgsData2;
						}
						else if(szArgsData1.equals("-Msg")==true)
						{
							szTranslatorMsg=szArgsData2;
						}

						
						
						
						//		System.out.println(szArgsData1+"   \t"+ szArgsData2);
					}
				}
			}

			if(szTranslatorPort.length()>0 && szTranslatorIPAdd.length()>0 && szTranslatorOparationType.length()>0 )
			{
				String szRetMsg="";
				boolean bValidateIpAddressStatus=false;
				boolean bValidatePorfStatus=false;

				
				
				bValidateIpAddressStatus=objLnfyConfigGeneral.validate_IpAddress(szTranslatorIPAdd) ;
				
				int iPort=0;
				try
				{
					iPort=Integer.parseInt(szTranslatorPort);
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
					if(szTranslatorOparationType.equals("ReloadConfigration")==true)
					{
						szRetMsg=objLnfyConfigGeneral.sendReLoadMsgToParser(szTranslatorIPAdd, iPort);
						System.out.println("Status :: "+szRetMsg);
					//	JOptionPane.showMessageDialog(objlnfyConfigFrame,szRetMsg, lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
					}
					else if(szTranslatorOparationType.equals("TestConnection")==true)
					{
						szRetMsg=objLnfyConfigGeneral.sendTestConnectionMsgToParser(szTranslatorIPAdd, iPort);
						System.out.println("Status :: "+szRetMsg);
					//	JOptionPane.showMessageDialog(objlnfyConfigFrame,szRetMsg, lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
					}
					else if(szTranslatorOparationType.equals("Message")==true)
					{
						if(szTranslatorMsg.length()>0)//szTranslatorMsg
						{
							szRetMsg=objLnfyConfigGeneral.sendSpecificMsgToParser(szTranslatorIPAdd, iPort,szTranslatorMsg);
							System.out.println("Status :: "+szRetMsg);
						//	JOptionPane.showMessageDialog(objlnfyConfigFrame,szRetMsg, lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							System.out.println("Status :: "+"Message is not avaliable or blank.");
						//	JOptionPane.showMessageDialog(objlnfyConfigFrame,"Please enter Msg/Data.", lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						//Nothing
					}	
				}
				else if(bValidateIpAddressStatus==false)
				{
					System.out.println("Status :: "+"Invalid Ip Address. Please enter valid Ip Address.");
				//	JOptionPane.showMessageDialog(objlnfyConfigFrame,"Invalid Ip Address. Please enter valid Ip Address.", lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
				}
				else if(bValidatePorfStatus==false)
				{
					System.out.println("Status :: "+"Invalid Port Number. Please enter valid Port Number.");
				//	JOptionPane.showMessageDialog(objlnfyConfigFrame,"Invalid Port Number. Please enter valid Port Number.", lnfyConfigCaption,JOptionPane.WARNING_MESSAGE);
				}
			}
			


		} 
		catch (Exception e) 
		{
		//	objLnfyLogger.writeExceptionLog(szFunDisc,e);
		}
		finally
		{
		//	objLnfyLogger.writeTimeLogEnd(lStartTime,szFunDisc);
		}
	}

	
}
