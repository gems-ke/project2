package data;

import java.util.ArrayList;

public class TableLine {
	
	String[] lineData;
	String[] columnNames;
	
	public TableLine(String[] columnNames){
		this.columnNames = columnNames;
		lineData = new String[this.columnNames.length];
	}
	
	public void setEntry(String[] data){
		/*for(int i = 0; i < data.size(); i++){
			lineData[i] = data.get(i);
		}*/
		this.lineData = data;
	}
	
	public String getEntry(String columnName){
		String entry = "";
		
		for(int i = 0; i < columnNames.length; i++){
			if(columnNames[i].equals(columnName)){
				entry = lineData[i];
			}
		}
		
		return entry;
	}

}