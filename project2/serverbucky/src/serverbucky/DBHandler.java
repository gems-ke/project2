package serverbucky;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class DBHandler {
	
	FileReader fr = null;
	private boolean doesExist = false;
	File Ofile = new File(System.getProperty("user.home") + File.separator + "benchInit.bat");
	File Ofile2 = new File(System.getProperty("user.home") + File.separator + "benchRead.bat");
	File OfileD = new File(System.getProperty("user.home") + File.separator + "filename.txt");
	File OfileTemp = new File(System.getProperty("user.home") + File.separator + "tempfile.bat");
	File OfileTemp2 = new File(System.getProperty("user.home") + File.separator + "tempfile2.bat");
	File OfileTempPortBat = new File(System.getProperty("user.home") + File.separator + "porttempfile.bat");
	File OfileTempPort = new File(System.getProperty("user.home") + File.separator + "porttemp.txt");
	
	private int decodeKey = 44651;
	private int[] decodeFragment = {6,7,2,5,8,9,0,6,7,5,6,2,0,3,3,6,5,1,6,7};

	
	private int osType = 32;
	private String defaultPath = "defaultPath";
	
	private boolean repeatRequested = false;
	
	static boolean noRegData = false;
	static boolean gotData = false;
	static String postProcessDefPath = "default";
	static int firstRun = 0;
	static int globalPort = 6789;
	
	public DBHandler(){
		
		getInitBatch();

	}
	
	private void instanciate(String requestedPath){
		
		if(System.getProperty("os.arch").equals("amd64")){
			
			osType = 64;
			
		}
		
		try {
			try {
				System.out.println("Instanciation request initiated.");
				doesExist = !Ofile.createNewFile();
				if(doesExist){
					System.out.println("Instanciation not required. Savefile localized.");
				}else{
					System.out.println("Instanciation required and Completed!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			fr = new FileReader(Ofile);
		} catch (FileNotFoundException e) {
			System.out.println("Instanciation of Savefile failed!");
			doesExist = false;
			e.printStackTrace();
		}
		System.out.println("Instanciation Request Successfully executed.");
		if(!doesExist){
			System.out.println("Writing default values to new Savefile Instance.");
			writeInitBatch(osType, requestedPath);
		}
		
			System.out.println("Reading default values from Savefile Instance.");
			getInitBatch();
		
	}
	
	private void writeInitBatch(int osArch, String defPath){
		
	try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(Ofile), "utf-8"))) {
	   writer.write("@echo off");
	   ((BufferedWriter) writer).newLine();
	   writer.write("REG ADD HKCU\\SOFTWARE /v BenchmarkInitialized /t REG_SZ /d 1");
	   ((BufferedWriter) writer).newLine();
	   writer.write("REG ADD HKCU\\SOFTWARE /v BenchmarkDefDBPath /t REG_SZ /d " + defPath);
	   ((BufferedWriter) writer).newLine();
	   writer.write("exit");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	try {
		Runtime.getRuntime().exec("cmd /c start C:\\Users\\%USERNAME%\\benchInit.bat");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
	
	private void getInitBatch(){
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(Ofile2), "utf-8"))) {
			writer.write("FOR /F \"skip=2 tokens=2,*\" %%A IN ('reg.exe query \"HKCU\\SOFTWARE\" /v \"BenchmarkInitialized\"') DO set \"DFMT=%%B\"");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO InitBool >C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO %DFMT% >>C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("FOR /F \"skip=2 tokens=2,*\" %%A IN ('reg.exe query \"HKCU\\SOFTWARE\" /v \"BenchmarkDefDBPath\"') DO set \"DFMG=%%B\"");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO DefPath >>C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO %DFMG%>>C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("FOR /F \"skip=2 tokens=2,*\" %%A IN ('reg.exe query \"HKCU\\SOFTWARE\" /v \"DefaultPort\"') DO set \"DFMG=%%B\"");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO DefPort >>C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO %DFMG%>>C:\\Users\\%USERNAME%\\filename.txt");
			((BufferedWriter) writer).newLine();
			writer.write("exit");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("triggered last runtime execution");
			Runtime.getRuntime().exec("cmd /c start C:\\Users\\%USERNAME%\\benchRead.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getData(){
		
		if(OfileD.exists()){

		try {
			fr = new FileReader(OfileD);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load file saves!");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line = "first";
		String[] templine;
		
		while (true) {
			System.out.println("pre:" + line);
			if(line.startsWith("InitBool"))
			{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(line.startsWith("ECHO")){
					noRegData = true;
					break;
				}else{
				System.out.println("post:" + line);
				templine = line.split(" ");
				firstRun = Integer.parseInt(templine[0]);
				try {
					reader.mark(500);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				}
			}else{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	  }
		do{
			
			try {
				fr = new FileReader(OfileD);
			} catch (FileNotFoundException e) {
				System.err.println("Couldn't load file saves!");
				e.printStackTrace();
			}
			
		if(!noRegData){
		while (true) {
			System.out.println("2pre:" + line);
			
			if(line == null){
				idle(1000);
				
				try {
					fr = new FileReader(OfileD);
				} catch (FileNotFoundException e) {
					System.err.println("Couldn't load file saves!");
					e.printStackTrace();
				}
				
				reader = new BufferedReader(fr);
				
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repeatRequested = true;
				break;
			}

			if(line.startsWith("DefPath"))
			{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("2post:" + line);
				postProcessDefPath = line;
				repeatRequested = false;
				break;
			}else{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			gotData = true;
	
		}
	}else{
			
		 gotData = false;
	}
		}while(repeatRequested);
		
		}
			
	}
	
	public void callData(){
		getData();
	}
	
	public void initData(String path){
		instanciate(path);
	}
	
	public void setDefaultDBPath(String path){
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(OfileTemp), "utf-8"))) {
	   writer.write("REG ADD HKCU\\SOFTWARE /f /v BenchmarkDefDBPath /t REG_SZ /d " + path);
	   ((BufferedWriter) writer).newLine();
	   writer.write("exit");
	   
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		try {
			System.out.println("triggered tempfile execution");
			Runtime.getRuntime().exec("cmd /c start C:\\Users\\%USERNAME%\\tempfile.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createDBDirectory(String path){
		boolean success = (new File(path + "\\BenchmarkingDatabase\\Tables")).mkdirs();
		success = (new File(path + "\\BenchmarkingDatabase\\UserData")).mkdirs();
		success = (new File(path + "\\BenchmarkingDatabase\\Backup")).mkdirs();
		try {
			success = new File(path + "\\BenchmarkingDatabase\\ChangeLog.txt").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void cleanUp(){
		Boolean isDeleted1 = Ofile.delete();
		Boolean isDeleted2 = Ofile2.delete();
		Boolean isDeleted3 = OfileD.delete();
		Boolean isDeleted4 = OfileTemp.delete();
		Boolean isDeleted5 = OfileTemp2.delete();
		Boolean isDeleted6 = OfileTempPortBat.delete();
	}
	
	public void setPort(int port){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(OfileTemp2), "utf-8"))) {
	   writer.write("@echo off");
	   ((BufferedWriter) writer).newLine();
	   writer.write("REG ADD HKCU\\SOFTWARE /f /v DefaultPort /t REG_SZ /d " + port);
	   ((BufferedWriter) writer).newLine();
	   writer.write("exit");
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		try {
			System.out.println("triggered last tempfile2port execution");
			Runtime.getRuntime().exec("cmd /c start C:\\Users\\%USERNAME%\\tempfile2.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getPortBatch(){
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(OfileTempPortBat), "utf-8"))) {
			writer.write("FOR /F \"skip=2 tokens=2,*\" %%A IN ('reg.exe query \"HKCU\\SOFTWARE\" /v \"DefaultPort\"') DO set \"DFMT=%%B\"");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO Port >C:\\Users\\%USERNAME%\\porttemp.txt");
			((BufferedWriter) writer).newLine();
			writer.write("ECHO %DFMT% >>C:\\Users\\%USERNAME%\\porttemp.txt");
			((BufferedWriter) writer).newLine();
			writer.write("exit");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("triggered last runtime execution");
			Runtime.getRuntime().exec("cmd /c start C:\\Users\\%USERNAME%\\porttempfile.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getPort(){
		
		getPortBatch();
		
		
		if(OfileTempPort.exists()){

		try {
			fr = new FileReader(OfileTempPort);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load file saves!");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line = "first";
		String[] templine;
		
		while (true) {
			if(line == null){
				reader = new BufferedReader(fr);
			}
			System.out.println("pre3:" + line);
			if(line.startsWith("Port"))
			{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(line.startsWith("ECHO")){
					noRegData = true;
					break;
				}else{
				System.out.println("post3:" + line);
				templine = line.split(" ");
				globalPort = Integer.parseInt(templine[0]);
				break;
				}
			}else{
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	  }

		}

	}
	
	public boolean checkTable(String tablename){
		
		File requestedTable = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "Tables" + File.separator + tablename + ".txt");
		
		return requestedTable.exists();
	}
	
	public boolean createTable(String tablename){
		
		boolean created = false;
		
		File requestedTable = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "Tables" + File.separator + tablename + ".txt");
		
		try{
			created = requestedTable.createNewFile();
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
		
		if(created){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public boolean overwriteTable(String tablename){
		
		boolean created = false;
		
		File requestedTable = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "Tables" + File.separator + tablename + ".txt");
		
		requestedTable.delete();
		
		try{
			created = requestedTable.createNewFile();
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
		
		if(created){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public void idle(int mstime){
		
		long timeStart = System.currentTimeMillis();
		
		while(true){
			if (timeStart - System.currentTimeMillis() <= - mstime){
				break;
			}
		}
		
	}
	
	public boolean createUser(String name, int[] codedPass){
		
		boolean created = false;
		
		File requestedUser = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "UserData" + File.separator + name + ".txt");
		
		try{
			created = requestedUser.createNewFile();
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(requestedUser), "utf-8"))) {
			writer.write(name);
			for(int i = 0; i < codedPass.length; i++){
				writer.write(" " + Integer.toString(codedPass[i]));
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(created){
			return true;
		}else{
			return false;
		}
		

	}
	
	public boolean checkUser(String username){
		
		File requestedUser = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "UserData" + File.separator + username + ".txt");
		
		return requestedUser.exists();
	}
	
	public boolean loginAdmin(String username, int[] codedpass){
		
		boolean accepted = false;
			
		File requestedUser = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "UserData" + File.separator + username + ".txt");
			
		try {
			fr = new FileReader(requestedUser);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load file saves!");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line = "first";
		String userDataRaw = new String();
			
		try {
			userDataRaw = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userDataRaw);
		
		String[] userDataExtractArray = userDataRaw.split(" ");
		
		int[] userDataPasswordArray = new int[userDataExtractArray.length-1];
		
		for(int i = 0; i < userDataPasswordArray.length; i++){
			userDataPasswordArray[i] = Integer.parseInt(userDataExtractArray[i+1]);
		}
		
		System.out.println(userDataPasswordArray.length);
		
		//password conversion
		String receivedPassword = decode(codedpass);
		String databasePassword = decode(userDataPasswordArray);
		
		System.out.println("DB: " + databasePassword + " received: " + receivedPassword);
		
		if(databasePassword.equals(receivedPassword)){
			accepted = true;
		}
		
		return accepted;
	}
	
	public boolean loginUser(String name, String password){
		boolean accepted = false;
		
		File requestedUser = new File(postProcessDefPath + File.separator + "BenchmarkingDatabase" + File.separator + "UserData" + File.separator + name + ".txt");
		
		try {
			fr = new FileReader(requestedUser);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load file saves!");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line = "first";
		String userDataRaw = new String();
			
		try {
			userDataRaw = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userDataRaw);
		
		String[] userDataExtractArray = userDataRaw.split(" ");
		
		System.out.println("DB: " + userDataExtractArray[1] + " received: " + password);
		
		if(userDataExtractArray[1].equals(password)){
			accepted = true;
		}
		
		return accepted;
	}
	
	private String decode(int code[]){
		String decodedString;
		ArrayList<Integer> decodedInt = new ArrayList<Integer>();
		ArrayList<Character> decodedCharList = new ArrayList<Character>();
		//begin decoding - decode int array to readable charvalue array
		for(int i = 0; i < code.length; i++){
			decodedInt.add(code[i] - decodeKey - decodeFragment[i]);
			System.out.println(i + ": " + decodedInt.get(i));
		}
		//convert charvalue array stored in int array to char array
		
		for(int i = 0; i < decodedInt.size(); i++){
			decodedCharList.add((char) (int)decodedInt.get(i));
		}
		
		Character[] decodedCharacter = new Character[decodedCharList.size()];
		decodedCharList.toArray(decodedCharacter);
		
		char[] decodedChar = new char[decodedCharacter.length];
		
		for(int i = 0; i < decodedCharacter.length; i++){
			decodedChar[i] = (char)decodedCharacter[i];
		}
		
		decodedString = new String(decodedChar);
		
		return decodedString;
	}
	
	private int[] encode(String toCode){
		
		char[] toCodeChars = toCode.toCharArray();
		int[] encoded = new int[toCodeChars.length];
		
		for(int i = 0; i < toCodeChars.length; i++){
			encoded[i] = (int)toCodeChars[i] + decodeKey + decodeFragment[i];
		}
		
		return encoded;
	}
		
}
