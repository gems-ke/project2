package data;

import java.util.ArrayList;

/**
 * Handles the column and row stuff. Saves Data as ArrayLists
 */
public class ListManager {
	/**
	 * The column ArrayList String
	 */
	public ArrayList<String> columnNames = new ArrayList<String>();
	
	public String tableName = new String();

	/**
	 * The column ArrayList Width
	 */
	public static ArrayList<Integer> columnWidth = new ArrayList<Integer>();

	// static ArrayList<String> status = new ArrayList<String>();

	// static ArrayList<DefaultTableCellRenderer> columnAlignment = new
	// ArrayList<DefaultTableCellRenderer>();

	// -------------------------- Definition Done -------------------------- //

	public void firstCall() {
		
		for (int i = 0; i < columnNames.size(); ++i) {
			columnWidth.add(50);
		}
	}

	/**
	 * Get the column width for the given integer
	 * 
	 * @param i
	 *            the parameter value of the array
	 * @return the element of the list
	 */
	public static int getColumnWidthElement(int i) {
		return columnWidth.get(i);
	}

	/**
	 * Adds a new String Element for table columns
	 * 
	 * @param name
	 *            the new table column header name
	 */
	public void addColumnName(String name) {
		columnNames.add(name);
	}

	/**
	 * Get the total table column size number
	 * 
	 * @return the table column size
	 */
	public int getColumnNameCount() {
		return columnNames.size();
	}

	/**
	 * Get the column name for the given integer
	 * 
	 * @param i
	 *            the parameter value of the array
	 * @return the element of the list
	 */
	public String getColumnNameElement(int i) {
		return columnNames.get(i);
	}
}
