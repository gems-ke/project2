package main;

import threads.ClientThread;
import threads.InterfaceThread;

public class Main {
	
	public static InterfaceThread it = new InterfaceThread();
	public static ClientThread ct = new ClientThread();
	
	public static void main(String[] args) {
		
		it.start();
		
	}
}