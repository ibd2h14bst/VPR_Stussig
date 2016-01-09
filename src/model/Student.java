package model;

import java.util.ArrayList;
import java.util.HashMap;

import db.DatabaseEntitySQLite;
import db.DatabaseSQLite;

public class Student extends DatabaseEntitySQLite {
	
	private static final String TABLE_NAME = "Studenten";
	public String firstName;
	public String secondName;
	
	public static ArrayList<Student> getByClassName(String className) {
		String where = "className LIKE '" + className + "'";
		ArrayList<HashMap<String, Object>> entries = DatabaseSQLite.getInstance().get(TABLE_NAME, where);
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 0; i < entries.size(); i++) {
			Student s = new Student();
			s.firstName = (String) entries.get(i).get("Name");
			students.add(s);
		}
		return students;
	}

	@Override
	protected HashMap<String, Object> getValueMap() {
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put("Name", this.firstName);
		fields.put("Nachname", this.secondName);
		return fields;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected HashMap<String, Object> getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
