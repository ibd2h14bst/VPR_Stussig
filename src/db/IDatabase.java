package db;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabase {
	public void insert(String tableName, HashMap<String, Object> fields);
	public ArrayList<HashMap<String, Object>> get(String tableName, HashMap<String,Object> fields);
	public void delete(String tableName, Object primaryKey);
	public void update(String tableName, Object primaryKey, HashMap<String, Object> fields);
}
