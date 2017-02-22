package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class InterfaceThread extends Thread{
	
	static int state = 0;
	static String version = ("0.0.1");
	
	LoginDialog dialog;
	MainMenu mainmenu;
	
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
		
		dialog = new LoginDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		dialog.okButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)  {
				  
				  String pass = new String(dialog.passwordField.getPassword());
				  
				  String message = "!login " + dialog.textField.getText() + " " + pass;
				  
				  dialog.progressBar.setString("Übermittle Daten...");
				  dialog.progressBar.setValue(30);
				  
				  Main.ct.transmit(message);
				  
				  requestStateChange(2);
				  
			  } 
		});
	}
	
	private void mainState(String currentUser){
		
		mainmenu = new MainMenu();
		mainmenu.setTitle("Benchmarking v. " + version + " - " + currentUser);
		mainmenu.setVisible(true);
		
		ListManager.firstCall();
		
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
					
					String checkUp = Main.ct.checkResponse("login");
					
					if(!checkUp.equals("nul")){
						if(checkUp.equals("invalidUser")){
							System.out.println("client: invalid user!");
							break;
						}else if(checkUp.equals("wrongPass")){
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
