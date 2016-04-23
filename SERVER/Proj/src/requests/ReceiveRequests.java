package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiveRequests {
	private Thread _thisThread;
	private Socket _client;
	
	private BufferedReader _inReader;
	private PrintWriter _outWriter;
	
	private String _request;
	
	public ReceiveRequests(Socket clientReceived){
		_client = clientReceived;
		_request = "";
		
		try {
        	// Get input and output streams to talk to the client
        	_inReader = new BufferedReader(new InputStreamReader(_client.getInputStream()));
			_outWriter = new PrintWriter(_client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		_thisThread = new Thread(new Runnable() {
			public void run() {
		        this.receive();
		        this.parse();
		        this.reply();
		        
		        String line;
		        try {
					while((line = _inReader.readLine()) != null){
						if (line.length() == 0)
					        break;
						
						_request += line;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void reply() {
				// TODO Auto-generated method stub
				
			}

			private void parse() {
				// TODO Auto-generated method stub
				
			}

			private void receive() {
				// TODO Auto-generated method stub
				
			}
		});
		
		_thisThread.start();
	}
}
