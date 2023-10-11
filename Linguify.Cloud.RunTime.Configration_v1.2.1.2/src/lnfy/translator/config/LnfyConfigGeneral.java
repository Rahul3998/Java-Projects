package lnfy.translator.config;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LnfyConfigGeneral
{

	public LnfyConfigGeneral()
	{
		
	}
	
	public String readFile(String filepath)
	{
		String completeData="";
		try
		{

			
			FileInputStream fis = new FileInputStream(filepath);
			try(InputStreamReader isr = new InputStreamReader(fis, "UTF8");)
			{
				try(Scanner objScanner = new Scanner(isr);)
				{
					completeData = objScanner.useDelimiter("\\A").next();
				}
				//completeData = new Scanner(isr).useDelimiter("\\A").next();
	
				/*
				
				
				BufferedReader br =
						new BufferedReader(
								new InputStreamReader(new FileInputStream(filepath)));
				String line = "";
	
				while ((line = br.readLine())!= null) 
				{
					completeData=completeData.concat(line);
	
				}
	
				if(br!=null)
					br.close();
				/**/
			}
		}
		catch(Exception ex)
		{
			completeData="Error in file :: " + "Unexpected exception : " +	ex.toString();
		//	System.out.println("Unexpected exception : " +	ex.toString());
		}
		return completeData;
	}

	
	public String readFile__(String filepath)
	{
		String completeData="";
		try
		{

			try(BufferedReader br =
					new BufferedReader(
							new InputStreamReader(new FileInputStream(filepath)));)
			{
				String line = "";
	
				while ((line = br.readLine())!= null) 
				{
					completeData=completeData.concat(line);
	
				}
	
				if(br!=null)
					br.close();
			}
		}
		catch(Exception ex)
		{
			completeData="Error in file :: " + "Unexpected exception : " +	ex.toString();
		//	System.out.println("Unexpected exception : " +	ex.toString());
		}
		return completeData;
	}
	public String sendTestConnectionMsgToParser(String szInIpAddress,int iInPortNo)
	{
		String szRetMsg="";
		try
		{
			String szIPAdd="";
			int iPort=0;
			String szSendMsg="";
			
			szIPAdd=szInIpAddress;
			iPort=iInPortNo;
			
			SocketClient objSocketClient=new SocketClient();
			
			szSendMsg="##TESTCONNECTION##LNEND##";
			szRetMsg=objSocketClient.sendMsgToServer(szIPAdd,iPort,szSendMsg);
			if(szRetMsg==null)
			{
				szRetMsg="Socket Failed.";
			}
			
			//		##LNRELEASEPROCESSSTART####LNEND##
			//		##LNRELEASEPROCESSUPDATING####LNEND##
			//		##LNRELEASEPROCESSEND####LNEND##
			//		"##OKAY##LNEND##"			------->	For Return Data	
			//		"##FAILED##LNEND##"			------->	For Return Data	
		} 
		catch (Exception e)
		{
			szRetMsg="Exception ReLoad Msg :: "+ e.toString();	
		//	e.printStackTrace();
		}
		return szRetMsg;
		
	}
	public String sendReLoadMsgToParser(String szInIpAddress,int iInPortNo)
	{
		String szRetMsg="";
		try
		{
			String szIPAdd="";
			int iPort=0;
			String szSendMsg="";
			
			szIPAdd=szInIpAddress;
			iPort=iInPortNo;
			
			SocketClient objSocketClient=new SocketClient();
			
			szSendMsg="##LNRELEASEPROCESSSTART####LNEND##";
			szRetMsg=objSocketClient.sendMsgToServer(szIPAdd,iPort,szSendMsg);
			if(szRetMsg==null)
			{
				szRetMsg="Socket Failed.";
			}
			else if(szRetMsg.contains("##OKAY##LNEND##")==true)
			{
				szSendMsg="##LNRELEASEPROCESSUPDATING####LNEND##";
				szRetMsg=objSocketClient.sendMsgToServer(szIPAdd,iPort,szSendMsg);
				if(szRetMsg==null)
				{
					szRetMsg="Socket Failed.";
				}
				else if(szRetMsg.contains("##OKAY##LNEND##")==true)
				{
					szSendMsg="##LNRELEASEPROCESSEND####LNEND##";
					szRetMsg=objSocketClient.sendMsgToServer(szIPAdd,iPort,szSendMsg);
					if(szRetMsg==null)
					{
						szRetMsg="Socket Failed.";
					}
				}
			}
			
			//		##LNRELEASEPROCESSSTART####LNEND##
			//		##LNRELEASEPROCESSUPDATING####LNEND##
			//		##LNRELEASEPROCESSEND####LNEND##
			//		"##OKAY##LNEND##"			------->	For Return Data	
			//		"##FAILED##LNEND##"			------->	For Return Data	
		} 
		catch (Exception e)
		{
			szRetMsg="Exception ReLoad Msg :: "+ e.toString();	
		//	e.printStackTrace();
		}
		return szRetMsg;
		
	}
	
	public String sendSpecificMsgToParser(String szInIpAddress,int iInPortNo,String szInMsg)
	{
		String szRetMsg="";
		try
		{
			
			String szIPAdd="";
			int iPort=0;
			String szSendMsg="";
			
			szIPAdd=szInIpAddress;
			iPort=iInPortNo;
			szSendMsg=szInMsg;
			
			SocketClient objSocketClient=new SocketClient();
			szRetMsg=objSocketClient.sendMsgToServer(szIPAdd,iPort,szSendMsg);
			if(szRetMsg==null)
			{
				szRetMsg="Socket Failed.";
			}
		} 
		catch (Exception e)
		{
			szRetMsg="Exception Specific Msg :: "+ e.toString();
		}	
		return szRetMsg;
	}
	
	private final Pattern PATTERN_IpAddress = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	public boolean validate_IpAddress(final String ip) 
	{
	    return PATTERN_IpAddress.matcher(ip).matches();
	}
	
	
}
