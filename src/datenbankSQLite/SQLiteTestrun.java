package datenbankSQLite;

import java.sql.*;
import java.util.ArrayList;

import db.DatabaseSQLite;
import model.Professor;
import model.Student;
import model.StudentClass;

public class SQLiteTestrun
{
	public static void main( String args[] )
	{
		// 0) configure db connection
		
		// MySQL
		/*
		final String DB_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/DBNAME";
		*/
		
		// ORACLE
		/*
		// fields inbetween [] are optional
		final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String DB_URL = "jdbc:oracle:driver_type:[username/password]@[//]host_name[:port][:ORCL]"
		// e.g. final String DB_URL = "jdbc:oracle:thin:root/toor@localhost"
		*/
		
		// SQLite
		final String DB_URL = "jdbc:sqlite:testDB.db";
		
		final String USER = "";
		final String PWD = "";
		
		DatabaseSQLite database = new DatabaseSQLite(DB_URL, USER, PWD);
		StudentClass.database = database;
		Student.database = database;
		Professor.database = database;
		
		Student student = new Student();
		student.firstName = "Conny";
		student.insert();
		
		StudentClass studentClass = new StudentClass(null, null);
		ArrayList<Student> students = studentClass.getStudentList();
		
		/*Student[] students = Student.getAll();
		for (int i = 0; i < students.length; i++) {
			students[i].delete();
		}
		
		try {
			
			
			// 3) Execute query / Process results
			addStudent(c);		// Von Gruppen selbs implementierte Methoden
			getStudent(c);
			
			
			// 4) Cleanup
			c.close();
			
		}catch ( SQLException e ) {
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		finally{
			System.exit(0);
		}*/
	}
		
	// ---------------------------------------------------
	
	
	private static void addStudent(Connection c){
		try{
			// 1) Statement
			Statement stmt = c.createStatement();
			
			// 2) SQL command
			String clean = "delete from STUDENTEN;";
			String student1 = "INSERT INTO STUDENTEN (StudentenID,NAME,Nachname,BILD,KLASSE)" +
							"VALUES (1, 'Ellerbrock', 'Dominik','','IBD2H14B');";
			String student2 = "INSERT INTO STUDENTEN (StudentenID,NAME,Nachname,BILD,KLASSE)" +
					"VALUES (2, 'Lenard', 'Hiller','','IBD2H14B');";
			String student3 = "INSERT INTO STUDENTEN (StudentenID,NAME,Nachname,BILD,KLASSE)" +
					"VALUES (3, 'Nick', 'Kötter','','IBD2H14B');";
			String student4 = "INSERT INTO STUDENTEN (StudentenID,NAME,Nachname,BILD,KLASSE)" +
					"VALUES (4, 'Cornelia', 'Stussig','','IBD2H14B');";
			
				   
			// 3) Batchexecution
			// build a batch of commands and execute statement only once --> performance
			stmt.addBatch(clean);
			stmt.addBatch(student1);
			stmt.addBatch(student2);
			stmt.addBatch(student3);
			stmt.addBatch(student4);
			stmt.executeBatch();
			System.out.println("Entry insertion successfully...");
			
			// 4) Cleanup
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}	


	// ---------------------------------------------------
	
	
	private static void getStudent(Connection c){
		try{
			// 0)
			// Autocommit-default: true
			// c.setAutoCommit(false);
			// c.commit();
			
			// 1) Statement
			Statement stmt = c.createStatement();
			
			// 2) SQL command
			String sql = "SELECT * FROM Studenten;";		// Studenten auslesen"; 
					   
			// 3) Execution
			// Get results from db / issue a query:
			ResultSet r = stmt.executeQuery(sql);
			
			// 4) Process queryresults
			while( r.next() ){
				// retrieve results according to type
				int sid			= r.getInt("StudentenID");
				String  name 	= r.getString("name");
				String  vorname	= r.getString("Nachname");
				String  bild	= r.getString("bild");
				String klasse  	= r.getString("klasse");
				
				System.out.println( "RESULT: "+sid+" "+name+" "+vorname+" "+bild+" "+klasse);
			}
			System.out.println("Entry selection successfully...");
			
			// 5) Cleanup
			r.close();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}
}