package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseSQLite implements IDatabase {

	private static final String DB_DRIVER = "org.sqlite.JDBC";
	private Connection connection = null;
	
	public DatabaseSQLite(String url, String user, String pwd) {
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("Opened database successfully...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(String tableName, HashMap<String, Object> fields) {
		String keys = "";
		String values = "";
		for (String key : fields.keySet()) {
		    Object value = fields.get(key);
		    if(!keys.equals("")) {
		    	keys += ",";
		    }
		    keys += key;
		    if(!values.equals("")) {
		    	values += ",";
		    }
		    if(value instanceof String) {
		    	values += "'"+ value + "'";
		    }
		    else if(value instanceof Integer) {
		    	values += value;
		    }
		}
		String sql = "INSERT INTO " + tableName + " ("+keys+")" +
				"VALUES ("+values+");";
		try {
			execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void execute(String sql) throws SQLException {
		Statement stmt = connection.createStatement();
		
		stmt.addBatch(sql);
		stmt.executeBatch();
		
		stmt.close();
	}

	public ArrayList<HashMap<String, Object>> get(String tableName, HashMap<String,Object> fields) {
		// SELECT * FROM student WHERE Klasse = 'ibd2h14b';

		return null;
	}

	@Override
	public void delete(String tableName, Object primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String tableName, Object primaryKey, HashMap<String, Object> fields) {
		// TODO Auto-generated method stub
		
	}
	
}
