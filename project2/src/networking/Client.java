package networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.Popup;
import main.Main;

public class Client extends JFrame {

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;

	static int messageCount = 0;
	public static String currentUserName = "nul";
	public static String currentUserStatus = "nul";
	public static boolean isConnected = false;

	public Client(String host) {

		super("Instinct's Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendMessage(event.getActionCommand());
				userText.setText("");
			}
		}

		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(300, 150);
		setVisible(true);

	}

	public void startRunning() {
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		} catch (EOFException eofException) {
			showMessage("\n Client terminated connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeCrap();
		}
	}

	private void connectToServer() throws IOException {
		showMessage("Attempting connection \n");
		connection = new Socket(InetAddress.getByName(serverIP), 5000);
		showMessage("Connected to:" + connection.getInetAddress().getHostName());
	}

	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams set up.");
	}

	private void whileChatting() throws IOException {
		ableToType(true);
		do {

			try {
				message = (String) input.readObject();

				messageCount++;

				System.out.println("message incoming:" + message + " " + message.startsWith("!"));

				if (message.startsWith("!") || message.startsWith(" You are now Connected!")) {

					operateResponse(message);

				}
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("\n Invalid Object type!");
			}
		} while (!message.equals("SERVER - END"));
	}

	private void closeCrap() {
		showMessage("\n Closing connection and streams...");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			if (message.startsWith("!")) {
				output.writeObject("flushmessage");
				System.out.println("flush sent:");
				output.flush();
				String command = operateCommand(message);
				output.writeObject(command);
				System.out.println("command sent:" + command);
				output.flush();
			} else {
				output.writeObject("flushmessage");
				System.out.println("flush sent:");
				output.flush();
				output.writeObject("CLIENT - " + message);
				System.out.println("message sent:" + message);
				output.flush();
			}
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Message could not be sent!");
		}
	}

	private void showMessage(final String m) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				chatWindow.append(m);
			}
		});
	}

	private void ableToType(boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				userText.setEditable(tof);
				;
			}
		});
	}

	public void idle(int mstime) {

		long timeStart = System.currentTimeMillis();

		while (true) {
			if (timeStart - System.currentTimeMillis() <= -mstime) {
				break;
			}
		}

	}

	
	  /*public void responseReceived(String message){
	  
	  System.out.println("response received: " + message);
	  
	  String[] messageDefract = message.split(" ");
	  
	  if(message.startsWith("SERVER - Benutzer")){
	  
	  new MainMenu().setVisible(true); dispose();
	  
	  }else if(messageDefract[0].startsWith("SERVER - Diese")){
	  
	  Main.it.dialog.progressBar.setValue(0); Thread popup = new Thread(new
	  PopupWindow("Dieser Benutzer existiert nicht."));
	  
	  }else if(messageDefract[0].startsWith("SERVER - Passwor")){
	  
	  Main.it.dialog.progressBar.setValue(0); Thread popup = new Thread(new
	  PopupWindow("Das eingegebene Passwort ist inkorrekt."));
	  
	  }else{ System.out.println("CHEATER"); }
	  
	  }*/
	 

	private String decode(int code[]) {
		String decodedString = "";

		return decodedString;
	}

	private String encode(String toCode) {
		String encodedString = "";

		return encodedString;
	}

	public String operateCommand(String command) {
		String encryptedCommand = command;

		if (command.startsWith("!login")) {
			encryptedCommand = "";
			String[] preperationString = command.split(" ");

			encryptedCommand = ("!login " + preperationString[1] + " user " + preperationString[2]);
		}
		

		return encryptedCommand;
	}

	public void operateResponse(String response) {

		if (response.startsWith("!SuccessfulUserLogin")) {
			String[] preperationString = response.split(" ");
			System.out.println("login response: " + response);
			currentUserName = preperationString[1];
			currentUserStatus = preperationString[2];
		}

		if (response.startsWith("!wrongPass")) {
			Main.it.dialog.progressBar.setValue(0);
			Thread popup = new Thread(new Popup("Das eingegebene Passwort ist inkorrekt."));
			System.out.println("wrongpass triggered");
			currentUserName = "wrongPass";
		}

		if (response.startsWith("!invalidUser")) {
			Main.it.dialog.progressBar.setValue(0);
			Thread popup = new Thread(new Popup("Dieser Benutzer existiert nicht."));
			System.out.println("invaliduser triggered");
			currentUserName = "invalidUser";
		}
		
		if (response.startsWith("!isOnline")) {
			Main.it.dialog.progressBar.setValue(0);
			Thread popup = new Thread(new Popup("Dieser Benutzer ist bereits eingeloggt."));
			currentUserName = "invalidUser";
		}
		
		if (response.startsWith("!updateOnlineUsers")) {
			System.out.println("onlineusers gets triggered!1");
			String[] userlist = response.split(" ");
			Main.it.mainmenu.updateUserList(userlist);
		}
		
		if (response.startsWith("!updateDirectory")) {
			System.out.println("updatedirectory gets triggered!");
			String[] userlist = response.split(" ");
			Main.it.mainmenu.fillTree(userlist);
		}
		
		if (response.startsWith("!updateExistingUsers")) {
			System.out.println("updateexistingUsers gets triggered!");
			Main.it.mainmenu.updateExistingUsers(response);
		}
		
		if (response.startsWith(" You are now Connected!")){
			System.out.println("connected.");
			isConnected = true;
		}
		
		if (response.startsWith("!appendedLine")) {
			Thread popup = new Thread(new Popup("Eintrag hinzugefï¿½gt!"));
		}
		
		if (response.startsWith("!tableData")) {
			System.out.println("onlineusers gets triggered!1");
			String[] userlist = response.split(" ");
			Main.it.mainmenu.updateUserList(userlist);
		}
		
		if (response.startsWith("!userAddSuccess")) {
			Thread popup = new Thread(new Popup("Benutzer erfolgreich hinzugefügt!"));
		}
		
		if (response.startsWith("!userAddExists")) {
			Thread popup = new Thread(new Popup("Benutzer existiert bereits!"));
		}
		
		if (response.startsWith("!userAddError")) {
			Thread popup = new Thread(new Popup("Benutzererstellung ERROR!"));
		}
	}
}
