package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Pedro
 *
 * Mockup server. Sends messages between the mockup Family member app and the mockup gr, uh... Granny app.
 *
 */
public class Server {
	
	static ServerSocket grannySerSocket = null;
	static Socket grannySocket = null;
	static PrintWriter grannyWrite = null;
	static BufferedReader grannyRead = null;
	
	static ServerSocket familySerSocket = null;
	static Socket familySocket = null;
	static PrintWriter familyWrite = null;
	static BufferedReader familyRead = null;
	
	static Queue<String> sendGrannyPending = new LinkedList<String>();
	static Queue<String> sendFamilyPending = new LinkedList<String>();
	
	static int _PORT1 = 12345;
	static int _PORT2 = 12346;
	
	public static void main(String[] args) {

		System.out.println("<Press any key to stop executing>");
		System.out.println("Server application, please connect both the granny and the family member");
		
		familyLogicThread.start();
		grannyLogicThread.start();
		
		try {System.in.read();} 
			catch (IOException e) {e.printStackTrace();}
		
		return;
	}

	

	static Thread grannyLogicThread = new Thread(new Runnable() {		
		public void run() {
			while(true)
			{
				try 
				{
					grannySerSocket = new ServerSocket(_PORT1);
					System.out.println("   Granny Port: "+ InetAddress.getLocalHost().getHostAddress() + ":" + grannySerSocket.getLocalPort());
					
					grannySocket = grannySerSocket.accept();
					grannyRead = new BufferedReader(new InputStreamReader(grannySocket.getInputStream()));
					grannyWrite = new PrintWriter(grannySocket.getOutputStream(), true);
					
				} catch (IOException e) {e.printStackTrace();}
				
				updateGranny();
				
				try {
					while(grannySocket.isConnected())
					{
						String buffer;
						buffer = familyRead.readLine();
						System.out.println("Granny says: " + buffer);
					}
				} catch(IOException e){e.printStackTrace();}
				
				System.out.println("\n\n Granny has left us! Granny, come back! (Well that was some dark humor)");
			}
			
		}
		
		public void updateGranny()
		{
			while(!sendGrannyPending.isEmpty())
			{
				System.out.println("Sent <" + sendGrannyPending.peek() + "> to granny");
				grannyWrite.println(sendGrannyPending.poll());
			}
		}
		
	});
	
	static Thread familyLogicThread = new Thread(new Runnable() {
		
		public void run() {
			while(true)
			{
				try 
				{
					familySerSocket = new ServerSocket(_PORT2);
					System.out.println("   Family Port: "+ InetAddress.getLocalHost().getHostAddress() + ":" + familySerSocket.getLocalPort());
					
					familySocket = familySerSocket.accept();
					familyRead = new BufferedReader(new InputStreamReader(familySocket.getInputStream()));
					familyWrite = new PrintWriter(familySocket.getOutputStream(), true);					
				} catch (IOException e) {e.printStackTrace();}
				
				try {
					
					updateFamily();
					
					while(familySocket.isConnected())
					{
						if(familyRead.ready())
						{
							String buffer;
							buffer = familyRead.readLine();
							System.out.println("Family says: " + buffer);
						}
						
					}
				} catch(IOException e){e.printStackTrace();}
				
				System.out.println("\n\n Family has left us! At least you were used from having your father away. (That was also some dark humor)");
			}
			
		}
		
		public void updateFamily()
		{
			while(!sendFamilyPending.isEmpty())
			{
				System.out.println("Sent <" + sendFamilyPending.peek() + "> to family");
				familyWrite.println(sendFamilyPending.poll());
			}
		}
		
	});
	
}
