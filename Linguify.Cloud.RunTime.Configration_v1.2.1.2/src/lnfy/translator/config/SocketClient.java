package lnfy.translator.config;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SocketClient {

	// ReentrantLock named lock to implement locking for thread safety
	public Lock lock;

	public SocketClient() {
		// initializes the lock with a new instance of ReentrantLock.
		this.lock = new ReentrantLock();
	}

	// method for sending a message to the server
	// parameters: szIPAdd (IP address of the server), iPort (port number), and
	// szMsg (message to be sent to the server).
	public String sendMsgToServer(String szIPAdd, int iPort, String szMsg) {

		String szServerName = "";
		String szOutData = ""; // used to store the response
		int iTmpPort = 0;
		// String szFunctionName ="sendMsgToServer";
		// Socket objSocketClient = null;
		// OutputStream objOutputStreamForServer = null;
		// DataOutputStream objDataOutputStreamut = null;
		// InputStream objInputStreamForServer = null;
		// DataInputStream objDataInputStream = null;
		try {

			String szTmp = "";
			byte[] btDataInputStream = null;
			int iIndex = 0;
			String szTmp1 = "";
			szServerName = szIPAdd;
			szOutData = szMsg;
			iTmpPort = iPort;

			// the method tries to obtain a lock with a 10-millisecond timeout using
			// lock.tryLock
			if (lock.tryLock(10, TimeUnit.MILLISECONDS)) // to prevent multiple threads from trying to establish a
															// connection simultaneously.
			{
				// a socket connection is established with the server using the provided IP
				// address and port.
				try (Socket objSocketClient = new Socket(szServerName, iTmpPort);) {
					objSocketClient.setSoTimeout(500);// InputStream associated with this Socket will block for only
														// this amount of time If the timeoutexpires, a java.net.SocketTimeoutException
					// (szMsg) is sent to the server using an OutputStream and a DataOutputStream.
					try (OutputStream objOutputStreamForServer = objSocketClient.getOutputStream();
							DataOutputStream objDataOutputStreamut = new DataOutputStream(objOutputStreamForServer);) {
						szTmp = szMsg;
						objDataOutputStreamut.write(szTmp.getBytes(Charset.defaultCharset())); // default ISO-8859-1(Windows), UTF-8(Linux)
						// response from the server is read using an InputStream and a DataInputStream.
						try (InputStream objInputStreamForServer = objSocketClient.getInputStream();
								DataInputStream objDataInputStream = new DataInputStream(objInputStreamForServer);) {
							btDataInputStream = new byte[10000];
							iIndex = objDataInputStream.read(btDataInputStream);
							if (btDataInputStream.length > 0 && iIndex > 0) {
								szTmp1 = new String(btDataInputStream, 0, iIndex, Charset.defaultCharset());
								szOutData = szTmp1; // response is stored in the szOutData variable.
							} else {
								szOutData = "";
							}
							try {
								if (objDataOutputStreamut != null) {
									objDataOutputStreamut.close();
								}
								if (objDataInputStream != null) {
									objDataInputStream.close();
								}
								if (objSocketClient != null) {
									objSocketClient.close();
								}
							} catch (Exception e) {
								// LnfyLogger.writeExceptionLog(szFunctionName, e);
								szOutData = null;
							}
						}
					}
				}
			}
		}
//		catch (SocketTimeoutException e) 
//		{
//			//LnfyLogger.writeExceptionLog(szFunctionName, e);
//			szOutData=null;
//		}
//		catch (InterruptedException e) 
//		{
//			//LnfyLogger.writeExceptionLog(szFunctionName, e);
//			szOutData=null;
//		}
//		catch(IOException e)
//		{
//			//LnfyLogger.writeExceptionLog(szFunctionName, e);
//			szOutData=null;
//		} 
		catch (Exception e) {
			// LnfyLogger.writeExceptionLog(szFunctionName, e);
			szOutData = null;
		} finally {
//			try
//			{
//				if(objDataOutputStreamut!=null)
//				{
//					objDataOutputStreamut.close();
//				}
//				if(objDataInputStream!=null)
//				{
//					objDataInputStream.close();
//				}
//				if(objSocketClient!=null)
//				{
//					objSocketClient.close();
//				}
//			}
//			catch (IOException e) 
//			{
//			//	LnfyLogger.writeExceptionLog(szFunctionName, e);
//				szOutData=null;
//			}
			// release the lock once the communication is done.
			lock.unlock();
		}
		// returns the server's response (or null in case of exceptions).
		return szOutData;
	}
}