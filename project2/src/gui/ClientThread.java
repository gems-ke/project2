package gui;

import javax.swing.JDialog;

public class ClientThread extends Thread{
	
	static Client client;
	
	public void run(){
		
		try {
			
			client = new Client("127.0.0.1");
			client.startRunning();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void transmit(String message){
		client.sendMessage(message);
	}

	public String checkResponse(String expectation){
		String response = ("nul");
		
		if(expectation.equals("login")){
			if(!client.currentUser.equals("nul")){
				response = client.currentUser;
			}
		}
		
		return response;
	}
}
