package threads;

import networking.Client;

public class ClientThread extends Thread {

	public static Client client;

	private String a = "192.168.2.102";
	private String b = "127.0.0.1";

	public void run() {
		try {
			client = new Client(b);
			client.startRunning();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void transmit(String message) {
		client.sendMessage(message);
	}

	public String checkResponse(String expectation) {
		String response = ("nul");

		if (expectation.equals("login")) {
			if (!client.currentUserName.equals("nul")) {
				response = client.currentUserName + " - " + client.currentUserStatus;
			}
		}

		if (expectation.equals("connect")) {
			if (client.isConnected) {
				response = "true";
			}
		}
		return response;
	}
}