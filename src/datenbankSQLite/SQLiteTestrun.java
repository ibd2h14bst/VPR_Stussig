package datenbankSQLite;

import java.sql.*;
import java.util.ArrayList;

import db.DatabaseSQLite;
import model.Professor;
import model.Student;
import model.StudentClass;

public class SQLiteTestrun
{
	public static void main( String args[] ) throws InterruptedException
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
		
		DatabaseSQLite.getInstance().connect(DB_URL, USER, PWD);
		
		ArrayList<Professor> allProfessors = Professor.get();
		for (int i = 0; i < allProfessors.size(); i++) {
			allProfessors.get(i).delete();
		}
		
		// Professor anlegen und bearbeiten
		Professor p = new Professor("DYC", "Dyck", "Zeugen", "abcd");
		p.save();				//neu hinzufügen
		
		DatabaseSQLite.getInstance().count(Professor.TABLE_NAME);
		Professor.get();
		
		p.setFirstName("Eugen");		// änderung
		p.save();					// abspeichern in DB
		p.setPassword("efgh");
		p.save();
		p.delete();					// Entity löschen 

		Thread.sleep(100);
		
		DatabaseSQLite.getInstance().count(Professor.TABLE_NAME);
		Professor.get();
		
		DatabaseSQLite.getInstance().close();
		
		System.exit(0);
		
	}
}