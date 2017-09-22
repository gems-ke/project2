package data;

import java.io.IOException;
import java.util.ArrayList;

public class TableData {

	private String tableName = "";
	private ArrayList<String> columnNames = new ArrayList<String>();
	private ArrayList<TableLine> lineList = new ArrayList<TableLine>();
	
	//größe abfragen
	//addrows und columns nach doppelklick

	public TableData(String tableDataMessage) {
		convert(tableDataMessage);
	}
	
	public ArrayList<String> getTableColumnStringArray(){
		return columnNames;
	}

	private void convert(String tableDataMessage) {
		String[] tableData = tableDataMessage.split("&&&");
		String columnData = tableDataMessage.split("&&")[1];
		this.tableName = tableDataMessage.split("&")[1].replaceAll("\\*", "");

		String[] columnDataArray = columnData.split("\\*");
		System.out.println("cda lenght: " + columnDataArray.length);
		
		for(int i = 0; i < columnDataArray.length; i++){
			columnNames.add(columnDataArray[i]);
		}
		
		System.out.println("cn size: " + columnNames.size());

		TableLine line;
		String[] lineData;
		for (int i = 1; i < tableData.length; i++) {
			System.out.println("tabledata i: " + tableData[i]);
			lineData = tableData[i].split("\\*");
			line = new TableLine(columnDataArray);
			line.setEntry(lineData);
			lineList.add(line);
		}
		
		System.out.println("linelist size: " + lineList.size());
	}

	public String getTableName() {
		return this.tableName;
	}
	
	public String getColumnName(int columnNumber){
		String columnName = "";
		
		try{
			System.out.println("columnnumber size: " + columnNumber + "and " + columnNames.size());
			columnName = columnNames.get(columnNumber-1);
		}catch(ArrayIndexOutOfBoundsException exception){
			columnName = "invalid!";
		}
		
		return columnName;
	}
	
	public String getData(int line, String column){
		String data = "";
		
		int columnNumber = -1;
		for(int i = 0; i < columnNames.size(); i++){
			System.out.println("comparing columnNames: " + columnNames.get(i) + " to input: " + column);
			if(columnNames.get(i).equals(column)){
				columnNumber = i;
			}
		}
		if(columnNumber == -1){
			System.out.println("invalid Column Name!");
		}
		
		data = lineList.get(line - 1).getLineDataAtPosition(columnNumber);
		
		return data;
	}
	
	public String[] getLineArray(int lineNumber){
		String[] lineData = lineList.get(lineNumber).getLineData();
		
		return lineData;
	}
	
	public int getColumnCount(){
		return columnNames.size();
	}
	
	public int getLineCount(){
		return lineList.size();
	}
	
	public String[] getColumnArray(){
		String[] columns = new String[columnNames.size()];
		
		for(int i = 0; i < columnNames.size(); i++){
			columns[i] = columnNames.get(i);
		}
		
		return columns;
	}
	
	public void addLine(String[] newLine){
		String[] lineData = new String[columnNames.size()];
		
		String[] columns = new String[columnNames.size()];
		
		for(int i = 0; i < columnNames.size(); i++){
			columns[i] = columnNames.get(i);
		}
		
		TableLine tl = new TableLine(columns);
		tl.setEntry(newLine);
		
		lineList.add(tl);		
	}
}
