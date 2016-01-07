package model;

import java.util.ArrayList;
import java.util.HashMap;

import db.DatabaseSQLite;

public class Student {
	
	public static DatabaseSQLite database;

	public String firstName;
	public String secondName;
	
	public void insert() {
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put("Name", this.firstName);
		fields.put("Nachname", this.secondName);
		database.insert("student", fields);
	}

	public static ArrayList<Student> getByClassName(String className) {
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put("Klasse", className);
		ArrayList<HashMap<String, Object>> entries = database.get("student", fields);
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 0; i < entries.size(); i++) {
			Student s = new Student();
			s.firstName = (String) entries.get(i).get("Name");
			students.add(s);
		}
		return students;
	}
	
}
