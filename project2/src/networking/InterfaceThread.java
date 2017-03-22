package networking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import data.ListManager;
import gui.Login;
import gui.Menu;
import main.Main;

public class InterfaceThread extends Thread{
	
	static int state = 0;
	static String version = ("0.0.1");
	
	public Login dialog;
	Menu mainmenu;
	
	public void run(){
		
		requestStateChange(0);
		
	}
	
	public void requestStateChange(int requestedState){
		if(requestedState == 0){
			state = 1;
			loginState();
		}
		
		if(requestedState == 2){
			state = 3;
			waitForResponse("login");
		}
		
		if(requestedState == 4){
			state = 5;
			dialog.dispose();
			mainState(Main.ct.checkResponse("login"));
		}
	}
	
	private void loginState(){
		
		dialog = new Login();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		dialog.okButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)  {
				  
				  String adminRequest = "!login ";//encrypted, wenn checkbox für admin login aktiviert!
				  
				  String pass = new String(dialog.passwordField.getPassword());
				  
				  String message = adminRequest + dialog.textField.getText() + " " + pass;
				  
				  dialog.progressBar.setString("Übermittle Daten...");
				  dialog.progressBar.setValue(30);
				  
				  Main.ct.transmit(message);
				  
				  requestStateChange(2);
				  
			  } 
		});
	}
	
	private void mainState(String currentUser){
		
		ListManager.firstCall();
		mainmenu = new Menu();
		mainmenu.setTitle("Benchmarking v. " + version + " - " + currentUser);
		mainmenu.setVisible(true);
		
	}
	
	private void waitForResponse(String position){
		
		System.out.println("waiting for response...");
		
		long frequencyMS;
		long timeStart = System.currentTimeMillis();
		
		if(position.equals("login")){
			int progress = 30;
			
			frequencyMS = 50;
			
			while(true){
				
				if (timeStart - System.currentTimeMillis() <= - frequencyMS){
					timeStart = System.currentTimeMillis();
					progress++;
					
					String[] checkUp = Main.ct.checkResponse("login").split(" ");
					
					if(!checkUp[0].equals("nul")){
						if(checkUp[0].equals("invalidUser")){
							System.out.println("client: invalid user!");
							break;
						}else if(checkUp[0].equals("wrongPass")){
							System.out.println("client: wrong pass!");
							break;
						}else{
							System.out.println("client: login!");
							requestStateChange(4);
							break;
						}
					}
				}
				
			}
		}
		
	}
	

}
