package main;

import javax.swing.JDialog;

import networking.ClientThread;
import networking.InterfaceThread;

public class Main {
	
	public static InterfaceThread it = new InterfaceThread();
	public static ClientThread ct = new ClientThread();

	public static void main(String[] args) {
		
		it.start();
		//ct.start();
		
	}

}
