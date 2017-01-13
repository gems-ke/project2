package gui;

import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Handles the column and row stuff. Saves Data as ArrayLists
 */
public class ListManager {
	/**
	 * The column ArrayList String
	 */
	static ArrayList<String> columnNames = new ArrayList<String>();
	
	/**
	 * The column ArrayList Width
	 */
	static ArrayList<Integer> columnWidth = new ArrayList<Integer>();
	
	// 	static ArrayList<String> status = new ArrayList<String>();
	
	//static ArrayList<DefaultTableCellRenderer> columnAlignment = new ArrayList<DefaultTableCellRenderer>();
	
	// -------------------------- Definition Done -------------------------- //
	
	public static void firstCall(){
		columnNames.add("#ID");
		columnNames.add("Datum");
		columnNames.add("Uhrzeit");
		columnNames.add("Name");
		columnNames.add("Zugabe");
		columnNames.add("Stoff");
		columnNames.add("Begr�ndung");
		columnNames.add("H");
		columnNames.add("I");
		columnNames.add("J");
		columnNames.add("K");
		columnNames.add("L");
		columnNames.add("M");
		columnNames.add("N");
		columnNames.add("O");
		columnNames.add("P");
		columnNames.add("Q");
		columnNames.add("R");
		columnNames.add("S");
		columnNames.add("T");
		
		for(int i = 0; i < columnNames.size();++i){
			columnWidth.add(75);
		}

		//change width of #id and 'Begr�ndung'
		columnWidth.set(0,25);
		columnWidth.set(6,200);
	}
	
	/**
	 * Get the column width for the given integer
	 * @param i the parameter value of the array
	 * @return the element of the list
	 */
	public static int getColumnWidthElement(int i){
		return columnWidth.get(i);
	}
	
	/**
	 * Adds a new String Element for table columns
	 * @param name the new table column header name
	 */
	public static void addColumnName(String name){
		columnNames.add(name);
	}
	
	/**
	 * Get the total table column size number
	 * @return the table column size
	 */
	public static int getColumnNameCount(){
		return columnNames.size();
	}
	
	/**
	 * Get the column name for the given integer
	 * @param i the parameter value of the array
	 * @return the element of the list
	 */
	public static String getColumnNameElement(int i){
		return columnNames.get(i);
	}
}