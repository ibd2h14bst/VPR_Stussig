package db;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabase {
	public void insert(String tableName, HashMap<String, Object> fields);
	
	public ArrayList<HashMap<String, Object>> get(String tableName);
	public ArrayList<HashMap<String, Object>> get(String tableName, String where);
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, String value);

	public ArrayList<HashMap<String, Object>> count(String tableName);
	public ArrayList<HashMap<String, Object>> count(String tableName, String where);
	public ArrayList<HashMap<String, Object>> count(String tableName, String key, String operator, String value);
	
	public void delete(String tableName, String where);
	public void delete(String tableName, String key, String operator, String value);
	
	public void update(String tableName, Object where, String key, String value);
}
