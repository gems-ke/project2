package networking;

import gui.Login;

public class LoginRoutine {

	int loginStatus = 0;
	boolean passCheck = false;

	String xamplN = new String("lol");
	char[] xamplP = { 97, 98, 99 };

	public void start() {

		// check username and password

		passCheck = true;

		System.out.println("Example name: " + xamplN);
		System.out.println("Input name: " + Login.textField.getText());

		if (xamplN.equals(Login.textField.getText())) {
			System.out.println("name identical!");
		} else {
			System.out.println("name not identical!");
		}

		System.out.println("Example password chararray:");

		for (int i = 0; i < xamplP.length; i++) {
			System.out.println(i + ". value: " + xamplP[i]);
		}

		System.out.println("Input password chararray:");

		for (int i = 0; i < Login.passwordField.getPassword().length; i++) {
			System.out.println(i + ". value: " + Login.passwordField.getPassword()[i]);
		}

		if (xamplP.length == Login.passwordField.getPassword().length) {

			int ip = 0;
			while (passCheck && ip < Login.passwordField.getPassword().length) {

				if (xamplP[ip] == Login.passwordField.getPassword()[ip]) {
					System.out.println(ip + ". letter check = true");
				} else {
					System.out.println(ip + ". letter check = false");
					passCheck = false;
				}
				ip++;
			}

			System.out.println("PassCheck: " + passCheck);

			if (passCheck)
				this.loginStatus = 1;

		} else {
			System.out.println("password not identical!");
		}

		// update local database

		// finish login

	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setPassCheck() {
		passCheck = true;
	}

}
