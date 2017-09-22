package data;

import java.util.ArrayList;

public class TableLine {

	/**
	 * IMPORTANT 2D STRING ARRAY. IT HOLDS ALL TABLE INFORMATION
	 */
	String[] lineData;

	static ArrayList<Integer> indexes = new ArrayList<Integer>();

	String[] columnNames;
	
	// -------------------------------------------------------- //
	
	/**
	 * FIRST CALL THIS
	 */
	public static void initIndexArray(){	
		for(int i = 0; i < 300; i++){
			indexes.add(i, 0);
		}
	}

	public TableLine(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void setEntry(String[] data) {
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
	public String getLineDataAtPosition(int column) {
		return lineData[column];
	}
	
	public String[] getLineData(){
		return this.lineData;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getIndexValue(int position){
		return indexes.get(position);
	}
	
	/**
	 * 
	 */
	public void incrementIndexValue(int position){
		indexes.set(position, indexes.get(position)+1);
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @param element
	 */
	public void setLineDataAtPosition(int row, int column, String element) {
		//lineData[row][column] = element;
	}
}