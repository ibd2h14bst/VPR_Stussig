package model;

import java.util.ArrayList;
import java.util.HashMap;

import db.DatabaseEntitySQLite;
import db.DatabaseSQLite;


public class Professor extends DatabaseEntitySQLite {
	
	public static final String TABLE_NAME = "Dozenten";

	private static final String DOZENTEN_ID = "DozentenID";
	private static final String NAME = "Name";
	private static final String FIRSTNAME = "Vorname";
	private static final String PASSWORD = "Passwort";
		
	private String dozentenID;			// DozentenID
	private String name;				// Name
	private String firstName;			// Vorname
	private String password;			// Passwort
	
	public Professor(String dozentenID, String name, String firstName, String password) 
	{
		this.dozentenID = dozentenID;
		this.name = name;
		this.firstName = firstName;
		this.password = password;		
	}	
	
	public Professor() {
	}
	
	public String getDozentenID() {
		return dozentenID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public static ArrayList<Professor> getByName(String name){
		String where = NAME + " LIKE '" + name + "'";
		return getByAttribute(where);
	}
	
	public static ArrayList<Professor> getByFirstame(String firstName){
		String where = FIRSTNAME + " LIKE '" + firstName + "'";
		return getByAttribute(where);
	}

	public static ArrayList<Professor> getByDozentenID(String dozentenID){
		String where = DOZENTEN_ID + " LIKE '" + dozentenID + "'";
		return getByAttribute(where);
	}

	public static ArrayList<Professor> get(){
		return getByAttribute("");
	}

	private static ArrayList<Professor> getByAttribute(String where) {
		ArrayList<HashMap<String, Object>> entries = DatabaseSQLite.getInstance().get(TABLE_NAME, where);
		ArrayList<Professor> professoren = new ArrayList<Professor>();
		for (int i = 0; i < entries.size(); i++) {
			Professor p = new Professor();
			p.dozentenID = (String) entries.get(i).get(DOZENTEN_ID);
			p.name = (String) entries.get(i).get(NAME);
			p.firstName = (String) entries.get(i).get(FIRSTNAME);
			p.password = (String) entries.get(i).get(PASSWORD);
			professoren.add(p);
		}
		return professoren;
	}

	@Override
	protected HashMap<String, Object> getValueMap() {
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put(DOZENTEN_ID, this.dozentenID);
		fields.put(NAME, this.name);
		fields.put(FIRSTNAME, this.firstName);
		fields.put(PASSWORD, this.password);
		return fields;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected HashMap<String, Object> getPrimaryKey() {
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put(DOZENTEN_ID, this.dozentenID);
		return fields;
	}
}
