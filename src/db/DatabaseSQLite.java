package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Cornelia Stussig
 *
 */
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
	
	private ResultSet execute(String sql) throws SQLException {
		Statement stmt = connection.createStatement();
		
		ResultSet resultSet = stmt.executeQuery(sql);
		
		stmt.close();
		
		return resultSet;
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName) {
		String sql = "SELECT * From " + tableName;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, String where ) {
		String sql = "SELECT * From " + tableName + 
						" WHERE " + where;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, String value ) {
		String sql = "SELECT * From " + tableName + 
						" WHERE " + key + " " + operator + " " + value;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> count(String tableName) {
		String sql = "SELECT COUNT (*) From " + tableName;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> count(String tableName, String where ) {
		String sql = "SELECT COUNT (*) From " + tableName + 
						" WHERE " + where;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> count(String tableName, String key, String operator, String value ) {
		String sql = "SELECT COUNT (*) From " + tableName + 
						" WHERE " + key + " " + operator + " " + value;
		ArrayList<HashMap<String, Object>> result = internalGet(sql);
		
		return result;
	}
	private ArrayList<HashMap<String, Object>> internalGet(String sql) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		try {
			ResultSet resultSet = execute(sql);
			while(resultSet.next()){
				HashMap<String, Object> entry = new HashMap<String, Object>();
				for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
					String columnLabel = resultSet.getMetaData().getColumnLabel(i);
					Object returnvalue = resultSet.getObject(columnLabel);
					entry.put(columnLabel, returnvalue);
				}
				result.add(entry);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void delete(String tableName, String where) {
		String sql = "DELETE From " + tableName + 
				" WHERE " + where;
		try {
			execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(String tableName, String key, String operator, String value ) {
		String sql = "DELETE From " + tableName + 
				" WHERE " + key + " " + operator + " " + value;
		try {
			execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String tableName, Object where, String key, String value) {
		String sql = "UPDATE " + tableName +
					" SET " + key +
					" = " + value+
					" WHERE " + where;
		try {
			execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	
}
