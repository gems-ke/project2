package serverbucky;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private DBHandler dbhandler = new DBHandler();
	
	private String temppath = "lulpath";
	
	public Server(){
		super("Instincts Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					sendMessage(event.getActionCommand());
					userText.setText("");
					
				}
					
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(500,800);
		setVisible(true);
	}
	
	public void startRunning(){
		
		showMessage("Startup-Routine initiated. \n");
		
		setupDatabase();
		
		try{
			server = new ServerSocket(dbhandler.globalPort, 100);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					dbhandler.cleanUp();
					whileChatting();
				}catch(EOFException eofException){
					showMessage("\n Server ended the connection! ");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
		showMessage(" Waiting for someone to connect at port " + dbhandler.globalPort + "... \n");
		connection = server.accept();
		showMessage(" Now connected to " + connection.getInetAddress().getHostName());
	}
	
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now Setup! \n");
	}
	
	private void setupDatabase(){
		showMessage("Database-check initiated. \n");
		dbhandler.callData();
		dbhandler.getPort();
		if(dbhandler.gotData && !dbhandler.postProcessDefPath.equals("lulpath") ){
			showMessage("Server found existing Database-data at: " + dbhandler.postProcessDefPath + "! \n");
		}else if(dbhandler.postProcessDefPath.equals("lulpath")){
			showMessage("DB-Error: default initiation. Please call support from the developer to continue. Error Code: 0001. Skype: lukas_44instinct \n");
		}
		
		else{
			showMessage("Server Database-initialization requested. \n");
			dbhandler.initData(temppath);
			showMessage("DB-Error: default initiation. Please call support from the developer to continue. Error Code: 0001. Skype: lukas_44instinct \n");
		}
	}
	
	private void whileChatting() throws IOException{
		String message = " You are now Connected! \n";
		sendMessage(message);
		ableToType(true);
		do{
			try{
				message = (String) input.readObject();
				if(message.startsWith("!")){
					
					operateCommand(message);
					
				}
				showMessage("\n" + message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n idk wtf that user sent!");
			}
		}while(!message.equals("CLIENT - END"));
	}
	
	private void closeCrap(){
		showMessage("\n Closing connections... \n");
		ableToType(false);
		dbhandler.cleanUp();
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	private void sendMessage(String message){
		
		try{
			if(message.startsWith("!")){
				output.writeObject(message);
			}else{
				output.writeObject("SERVER - " + message);
			}
			output.flush();
			showMessage("\nSERVER - " + message);
		}catch(IOException ioException){
			chatWindow.append("\n ERROR: Message could not be sent!");
		}
		
	}
	
	private void showMessage(final String text){
		
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					chatWindow.append(text);
				}
			}
		);
		
	}
	
	private void ableToType(final boolean tof){
		
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					userText.setEditable(tof);
				}
			}
		);
		
	}
	
	private void operateCommand(String command){
		
		if(command.equals("!respond")){
			sendMessage("Server responds successfully.");
		}
		
		if(command.startsWith("!createDirectory")){
			String[] path;
			path = command.split(" ");
			System.out.println(path[1]);
			dbhandler.setDefaultDBPath(path[1]);
			dbhandler.createDBDirectory(path[1]);
			showMessage("Directory created! ");
		}
		
		if(command.startsWith("!setPort")){
			String[] portString;
			int port;
			portString = command.split(" ");
			port = Integer.parseInt(portString[1]);
			dbhandler.setPort(port);
			showMessage("Default Port set. Please restart to redirect.");
		}
		
		if(command.startsWith("!createTable")){
			String[] tablename;
			tablename = command.split(" ");
			if(dbhandler.checkTable(tablename[1])){
				showMessage("\n Table already exists. If you want to overwrite an existing table, please use the !overwriteTable command. Table not created.");
			}else{
				if(dbhandler.createTable(tablename[1])){
					showMessage("\n Table successfully added to the Database.");
				}else{
					showMessage("\n Error: Table could not be added to the Filesystem.");
				}
			}
		}
		
		if(command.startsWith("!overwriteTable")){
			String[] tablename;
			tablename = command.split(" ");
			if(dbhandler.checkTable(tablename[1])){
				showMessage("\n Table already exists. Overwriting initiated.");
				if(dbhandler.overwriteTable(tablename[1])){
					showMessage("\n Table successfully overwritten.");
				}else{
					showMessage("\n Error: Table could not be added to the Filesystem.");
				}
			}else{
					showMessage("\n Table does not Exist. Please use the !createTable command.");
			}
		}
		
		if(command.startsWith("!createUser")){
			String[] username;
			username = command.split(" ");
			if(dbhandler.checkUser(username[1])){
				showMessage("\n User already exists.");
			}else{
				
				int[] extractedPass = new int[username.length-2];
				
				for(int i = 0; i < extractedPass.length; i++){
					extractedPass[i] = Integer.parseInt(username[i+2]);
					System.out.println(extractedPass[i]);
				}
				
				if(dbhandler.createUser(username[1], extractedPass)){
					showMessage("\n User successfully added to the Database.");
				}else{
					showMessage("\n Error: User could not be added to the Filesystem.");
				}
			}
		}
		
		if(command.startsWith("!login")){
			String[] username;
			username = command.split(" ");
			if(!dbhandler.checkUser(username[1])){
				showMessage("\n User doesn't exist.");
				sendMessage("!invalidUser");
			}else{
				
			if(username[2].startsWith("4")){
				int[] extractedPass = new int[username.length-2];
				
				for(int i = 0; i < extractedPass.length; i++){
					extractedPass[i] = Integer.parseInt(username[i+2]);
					System.out.println(extractedPass[i]);
				}
				
				if(dbhandler.loginAdmin(username[1], extractedPass)){
					showMessage("\n User successfully logged in.");
					sendMessage("!SuccessfulAdminLogin: " + username[1]);
				}else{
					sendMessage("!wrongPass");
					showMessage("\n Error: User Password wrong.");
				}
			}else{
				
				if(dbhandler.loginUser(username[1], username[2])){
					showMessage("\n User" + username[1] + "successfully logged in.");
					sendMessage("!SuccessfulUserLogin: " + username[1]);
				}else{
					sendMessage("!wrongPass");
					showMessage("\n Error: User Password wrong.");
				}
				
			}
			}
		}
		
		if(command.startsWith("!appendTableLine")){
			
		}
		
	}

}
