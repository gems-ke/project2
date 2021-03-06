package data;

import java.util.ArrayList;

public class TableData {

	String tableName = "";
	ArrayList<String> columnNames = new ArrayList<String>();
	static ArrayList<TableLine> lineList = new ArrayList<TableLine>();
	
	//gr��e abfragen
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
}
