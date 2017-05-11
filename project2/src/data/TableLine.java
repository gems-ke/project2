package data;

public class TableLine {

	/**
	 * IMPORTANT 2D STRING ARRAY. IT HOLDS ALL TABLE INFORMATION
	 */
	static String[][] lineData;

	static int id = 0;

	String[] columnNames;

	public TableLine(String[] columnNames) {
		this.columnNames = columnNames;
		lineData = new String[this.columnNames.length][];
	}

	public void setEntry(String[][] data) {
		this.lineData = data;
	}

	/**
	 * Get the line data at row x and column x
	 * 
	 * @param row
	 *            the row index of the element
	 * @param column
	 *            the columnt index of the element
	 * @return the line data element from row x and column x
	 */
	public static String getLineDataAtPosition(int row, int column) {
		return lineData[row][column];
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getIndex(){
		return id;
	}
	
	/**
	 * 
	 */
	public static void incrementIndex(){
		id++;
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @param element
	 */
	public static void setLineDataAtPosition(int row, int column, String element) {
		lineData[row][column] = element;
	}
}