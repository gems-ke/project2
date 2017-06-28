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

	private void convert(String tableDataMessage) {
		String[] tableData = tableDataMessage.split("&&&");
		String columnData = tableDataMessage.split("&&")[1];
		this.tableName = tableDataMessage.split("&")[1].replaceAll("\\*", "");

		String[] columnDataArray = columnData.split("\\*");

		TableLine line;
		String[] lineData;
		for (int i = 1; i < tableData.length; i++) {
			lineData = tableData[i].split("\\*");
			line = new TableLine(columnDataArray);
			lineList.add(line);
		}
	}

	public String getTableName() {
		return this.tableName;
	}
	
	public String getColumnName(int columnNumber){
		String columnName = "";
		
		try{
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
			if(columnNames.get(i).equals(column)){
				columnNumber = i;
			}
		}
		if(columnNumber == -1){
			System.out.println("invalid Column Name!");
		}
		
		//lineList.get
		
		return data;
	}
}
