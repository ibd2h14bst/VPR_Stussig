package model;
import java.util.ArrayList;
import java.util.HashMap;

import db.DatabaseEntitySQLite;
import db.IDatabase;

public class StudentClass extends DatabaseEntitySQLite
{
	
	private static final String TABLE_NAME = "Klassen";
	
	private String className;			// Klassenbezeichnung
	private String classProfessor;		// Klassendozent

	public StudentClass(String className, String classProfessor) 
	{
		this.className = className;
		this.classProfessor = classProfessor;
	}
	
	public String getClassName(String className)
	{
		return className;
	}
	
	public String getProfessor()
	{
		return classProfessor;
	}
	
	public void addStudent(Student student)
	{
		
	}
	
	public ArrayList<Student> getStudentList()
	{
		return Student.getByClassName(this.className);
	}
	
	public void changeProfessor(String classProfessor)
	{
		this.classProfessor = classProfessor;
	}

	@Override
	protected HashMap<String, Object> getValueMap() {
		// TODO Auto-generated method stub
		return null;
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
	
//	public static classGetAll(){}
//	public StudentIsUniqueCheck(){
//		
//	}
}
