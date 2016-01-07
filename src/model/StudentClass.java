package model;
import java.util.ArrayList;

import db.IDatabase;

public class StudentClass 
{
	
	public static IDatabase database;
	
	private ArrayList<Student> studentList;
	private String className;
	private Professor classProfessor;

	public StudentClass(String className, Professor classProfessor) 
	{
		this.className = className;
		this.classProfessor = classProfessor;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public Professor getProfessor()
	{
		return classProfessor;
	}
	
	public void addStudent(Student student)
	{
		studentList.add(student);
	}
	
	public ArrayList<Student> getStudentList()
	{
		return Student.getByClassName(this.className);
	}
	
	public void changeProfessor(Professor classProfessor)
	{
		this.classProfessor = classProfessor;
	}
	
//	public static classGetAll(){}
//	public StudentIsUniqueCheck(){
//		
//	}
}
