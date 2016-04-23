package requests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive {

	private int _port;
	private ServerSocket _socketToListen;
	private Thread _thisThread;
	
	public Receive(final int portToSocket){
		
		_thisThread = new Thread(new Runnable() {
			public void run() {
				_port = portToSocket;
				try {
					_socketToListen = new ServerSocket(_port);
					
					while(true){
						Socket client = _socketToListen.accept();
						ReceiveRequests rr = new ReceiveRequests(client);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		_thisThread.start();
	}
}
