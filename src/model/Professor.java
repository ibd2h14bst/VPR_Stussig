package model;

import db.DatabaseSQLite;


public class Professor {
	
	public static DatabaseSQLite database;
	
	String dozentenID;
	String firstName;
	String secondName;
	String password;
	
	public Professor(String dozentenID, String firstName, String secondName, String password) 
	{
		this.dozentenID = dozentenID;
		this.firstName = firstName;
		this.secondName = secondName;
		this.password = password;
		
	}
}
