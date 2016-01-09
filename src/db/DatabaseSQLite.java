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

	private static DatabaseSQLite instance;

	public static DatabaseSQLite getInstance() {
		if (instance == null) {
			instance = new DatabaseSQLite();
		}
		return instance;
	}

	private DatabaseSQLite() {

	}

	public void connect(String url, String user, String pwd) {
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
			if (!keys.equals("")) {
				keys += ",";
			}
			keys += key;
			if (!values.equals("")) {
				values += ",";
			}
			if (value instanceof String) {
				values += "'" + value + "'";
			} else if (value instanceof Integer) {
				values += value;
			}
		}
		String sql = "INSERT INTO " + tableName + " (" + keys + ")" + " VALUES (" + values + ");";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName) {
		String sql = "SELECT * FROM " + tableName + ";";
		ArrayList<HashMap<String, Object>> result = internalGet(sql);

		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, String where) {
		String sql = null;
		if(where == null || where.equals("")) {
			sql = "SELECT * FROM " + tableName + ";";
		} else {
			sql = "SELECT * FROM " + tableName + " WHERE " + where + ";";
		}
		ArrayList<HashMap<String, Object>> result = internalGet(sql);

		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, String value) {
		String sql = "SELECT * FROM " + tableName + " WHERE " + key + " " + operator + " '" + value + "';";
		ArrayList<HashMap<String, Object>> result = internalGet(sql);

		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, int value) {
		String sql = "SELECT * FROM " + tableName + " WHERE " + key + " " + operator + " " + value + ";";
		ArrayList<HashMap<String, Object>> result = internalGet(sql);

		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> get(String tableName, HashMap<String, Object> fields) {
		String where = "";
		for (String key : fields.keySet()) {
			if (!where.equals("")) {
				where += " AND ";
			}
			where += key + " = ";
			if (fields.get(key) instanceof String) {
				where += "'" + fields.get(key) + "'";
			} else if (fields.get(key) instanceof Integer) {
				where += fields.get(key);
			}
		}

		String sql = "SELECT * FROM " + tableName + " WHERE " + where + ";";		
		ArrayList<HashMap<String, Object>> result = internalGet(sql);

		return result;
	}

	private ArrayList<HashMap<String, Object>> internalGet(String sql) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		try {
			Statement stmt = connection.createStatement();
			System.out.println(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				HashMap<String, Object> entry = new HashMap<String, Object>();
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					String columnLabel = resultSet.getMetaData().getColumnLabel(i);
					Object returnvalue = resultSet.getObject(columnLabel);
					entry.put(columnLabel, returnvalue);
				}
				result.add(entry);
			}
			System.out.println("\tfetched " + result.size() + " rows");
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int count(String tableName) {
		String sql = "SELECT COUNT (*) FROM " + tableName + ";";
		int result = internalCount(sql);

		return result;
	}

	@Override
	public int count(String tableName, String where) {
		String sql = "SELECT COUNT (*) FROM " + tableName + " WHERE " + where + ";";
		int result = internalCount(sql);
		return result;
	}

	@Override
	public int count(String tableName, String key, String operator, String value) {
		String sql = "SELECT COUNT (*) FROM " + tableName + " WHERE " + key + " " + operator + " " + value + ";";
		int result = internalCount(sql);
		return result;
	}
	
	private int internalCount(String sql) {
		int result = 0;
		try {
			Statement stmt = connection.createStatement();
			System.out.println(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet.next()){
				result = resultSet.getInt(1);
			}
			System.out.println(result);
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void delete(String tableName, String where) {
		String sql = "DELETE FROM " + tableName + " WHERE " + where + ";";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String tableName, String key, String operator, String value) {
		String sql = "DELETE FROM " + tableName + " WHERE " + key + " " + operator + " " + value + ";";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String tableName, HashMap<String, Object> fields) {
		String where = "";
		for (String key : fields.keySet()) {
			if (!where.equals("")) {
				where += " AND ";
			}
			where += key + " = ";
			if (fields.get(key) instanceof String) {
				where += "'" + fields.get(key) + "' ";
			} else if (fields.get(key) instanceof Integer) {
				where += fields.get(key) + " ";
			}
		}

		String sql = "DELETE FROM " + tableName + " WHERE " + where + ";";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String tableName, HashMap<String, Object> primaryKeyFields, HashMap<String, Object> fields) {
		String set = "";
		for (String key : fields.keySet()) {
			if (!set.equals("")) {
				set += " , ";
			}
			set += key + " = ";
			if (fields.get(key) instanceof String) {
				set += "'" + fields.get(key) + "' ";
			} else if (fields.get(key) instanceof Integer) {
				set += fields.get(key) + " ";
			}
		}
		String where = "";
		for (String key : primaryKeyFields.keySet()) {
			if (!where.equals("")) {
				where += " AND ";
			}
			where += key + " = ";
			if (primaryKeyFields.get(key) instanceof String) {
				where += "'" + primaryKeyFields.get(key) + "' ";
			} else if (primaryKeyFields.get(key) instanceof Integer) {
				where += primaryKeyFields.get(key) + " ";
			}
		}

		String sql = "UPDATE " + tableName + " SET " + set + " WHERE " + where + ";";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String tableName, Object where, String key, String value) {
		String sql = "UPDATE " + tableName + " SET " + key + " = " + value + " WHERE " + where + ";";
		try {
			executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void executeUpdate(String sql) throws SQLException {
		Statement stmt = connection.createStatement();

		System.out.println(sql);
		
		int rowCount = stmt.executeUpdate(sql);

		System.out.println("\taffected " + rowCount + " rows");
		
		stmt.close();
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
