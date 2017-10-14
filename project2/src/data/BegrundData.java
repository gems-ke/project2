package data;

import java.util.ArrayList;

public class BegrundData {
	
	private String tablename = new String();
	
	private ArrayList<String> actives = new ArrayList<String>();
	private ArrayList<String> inactives = new ArrayList<String>();
	
	public BegrundData(String tablename, ArrayList<String> activeData, ArrayList<String> inactiveData){
		this.tablename = tablename;
		this.actives = activeData;
		this.inactives = inactiveData;
	}
	
	public void addToActives(String begrundung){
		actives.add(begrundung);
	}
	
	public void addToInactives(String begrundung){
		inactives.add(begrundung);
	}
	
	public ArrayList<String> getActives(){
		return this.actives;
	}
	
	public ArrayList<String> getInactives(){
		return this.inactives;
	}
	
	public String getTableName(){
		return this.tablename;
	}

}
