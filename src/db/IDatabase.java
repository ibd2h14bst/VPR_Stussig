package db;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabase {
	public void insert(String tableName, HashMap<String, Object> fields);
	
	public ArrayList<HashMap<String, Object>> get(String tableName);
	public ArrayList<HashMap<String, Object>> get(String tableName, String where);
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, String value);
	public ArrayList<HashMap<String, Object>> get(String tableName, String key, String operator, int value);
	public ArrayList<HashMap<String, Object>> get(String tableName, HashMap<String, Object> fields);
	
	public int count(String tableName);
	public int count(String tableName, String where);
	public int count(String tableName, String key, String operator, String value);
	
	public void delete(String tableName, String where);
	public void delete(String tableName, String key, String operator, String value);
	public void delete(String tableName, HashMap<String, Object> field);
	
	public void update(String tableName, Object where, String key, String value);
	public void update(String tableName, HashMap<String, Object> primaryKeyFields, HashMap<String, Object> fields);


}
