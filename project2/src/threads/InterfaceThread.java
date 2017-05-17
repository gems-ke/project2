package threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;

import data.ListManager;
import gui.Login;
import gui.Menu;
import main.Main;
import data.WinRegistry;

public class InterfaceThread extends Thread {

	static WinRegistry regedit = new WinRegistry();

	static int state = 0;
	static String version = ("0.0.1");

	public Login dialog;
	public Menu mainmenu;

	private boolean ctIsRunning = false;

	public void run() {

		requestStateChange(0);

	}

	public void requestStateChange(int requestedState) {
		if (requestedState == 0) {
			state = 1;
			loginState();
		}

		if (requestedState == 2) {
			state = 3;
			dialog.progressBar.setValue(70);
			waitForResponse("login");
		}

		if (requestedState == 4) {
			dialog.progressBar.setValue(100);
			state = 5;
			dialog.dispose();
			mainState(Main.ct.checkResponse("login"));
			System.out.println("jetzt würde das hauptprogramm aufpoppen...");
		}
	}

	private void loginState() {

		dialog = new Login();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		String defServerIP = "";
		try {
			defServerIP = regedit.readString(0x80000001, "SOFTWARE", "DefaultServerIP");
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		if (defServerIP == null) {

		}

		dialog.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			/*	if (!ctIsRunning) {
					Main.ct.start();
					ctIsRunning = true;
				}

				waitForResponse("connect");

				String loginParameter = "!login ";// encrypted, wenn checkbox
													// für admin login
													// aktiviert!

				String pass = new String(dialog.passwordField.getPassword());

				String message = loginParameter + dialog.textField.getText() + " " + pass;

				dialog.progressBar.setString("Übermittle Daten...");
				dialog.progressBar.setValue(30);

				// nicht auskommentiert, außer du bist kevin!
				Main.ct.transmit(message);

				dialog.progressBar.setValue(50);*/

				// TODO - änder zu 2
				requestStateChange(4);

			}
		});
	}

	private void mainState(String currentUser) {

		ListManager.firstCall();
		mainmenu = new Menu(currentUser);
		mainmenu.setTitle("Benchmarking v. " + version + " - " + currentUser);
		mainmenu.setVisible(true);

		mainmenu.btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String msg = "!appendTableLine*" + "benchmark1" + "*" +
				// mainmenu.textField.getText() + "*" +
				// mainmenu.textField_1.getText() + "*" +
				// mainmenu.textField_2.getText();
				// Main.ct.transmit(msg);

			}
		});
	}

	private void waitForResponse(String position) {

		System.out.println("waiting for response..." + position);

		long frequencyMS;
		long timeStart = System.currentTimeMillis();

		if (position.equals("login")) {
			int progress = 30;

			frequencyMS = 50;

			dialog.progressBar.setValue(80);

			while (true) {

				if (timeStart - System.currentTimeMillis() <= -frequencyMS) {
					timeStart = System.currentTimeMillis();
					progress++;

					String[] checkUp = Main.ct.checkResponse("login").split(" ");

					System.out.println("checkup equals: " + checkUp[0]);

					if (!checkUp[0].equals("nul")) {
						if (checkUp[0].equals("invalidUser")) {
							System.out.println("client: invalid user!");
							break;
						} else if (checkUp[0].equals("wrongPass")) {
							System.out.println("client: wrong pass!");
							break;
						} else {
							System.out.println("client: login!");
							requestStateChange(4);
							break;
						}
					}
				}
			}
		}

		if (position.equals("connect")) {
			System.out.println("connect triggered!");

			int progress = 30;

			frequencyMS = 50;

			dialog.progressBar.setValue(80);

			while (true) {

				if (timeStart - System.currentTimeMillis() <= -frequencyMS) {
					timeStart = System.currentTimeMillis();
					progress++;

					String checkUp = Main.ct.checkResponse("connect");

					System.out.println(checkUp);

					if (!checkUp.equals("nul")) {

						System.out.println(checkUp + ": second");

						if (checkUp.startsWith("true")) {
							System.out.println("connected!!!");
							break;
						} else {
							System.out.println("cant connect lul");
						}
					}
				}
			}
		}
	}
}
