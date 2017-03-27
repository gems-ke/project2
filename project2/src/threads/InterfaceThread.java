package threads;

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
	public Menu mainmenu;
	
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
			dialog.progressBar.setValue(70);
			waitForResponse("login");
		}
		
		if(requestedState == 4){
			dialog.progressBar.setValue(100);
			state = 5;
			dialog.dispose();
			mainState(Main.ct.checkResponse("login"));
			System.out.println("jetzt würde das hauptprogramm aufpoppen...");
		}
	}
	
	private void loginState(){
		
		dialog = new Login();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		dialog.okButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)  {
				  
				  String loginParameter = "!login ";//encrypted, wenn checkbox für admin login aktiviert!
				  
				  if(Login.chckbxAdmin.isSelected()){
					  loginParameter = "!encryptedlogin ";
				  }
				  
				  String pass = new String(dialog.passwordField.getPassword());
				  
				  String message = loginParameter + dialog.textField.getText() + " " + pass;
				  
				  dialog.progressBar.setString("Übermittle Daten...");
				  dialog.progressBar.setValue(30);
				  
				  Main.ct.transmit(message);
				  
				  dialog.progressBar.setValue(50);
				  
				  requestStateChange(2);
				  
			  } 
		});
	}
	
	private void mainState(String currentUser){
		
		ListManager.firstCall();
		mainmenu = new Menu(currentUser);
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
			
			dialog.progressBar.setValue(80);
			
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
