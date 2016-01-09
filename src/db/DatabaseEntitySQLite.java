package db;

import java.util.HashMap;

public abstract class DatabaseEntitySQLite {
	
	private boolean isInDB = false;
	
	public void save() {
		if(isInDB) {
			update();
		} else {
			insert();
		}
	}
	
	private void insert() {
		HashMap<String, Object> fields = getValueMap();
		DatabaseSQLite.getInstance().insert(getTableName(), fields);
		isInDB = true;
	}
		
	private void update(){
		HashMap<String, Object> primaryKeyFields = getPrimaryKey();
		HashMap<String, Object> fields = getValueMap();
		DatabaseSQLite.getInstance().update(getTableName(), primaryKeyFields, fields);	
	}
	
	public void delete(){
		HashMap<String, Object> fields = getPrimaryKey();
		DatabaseSQLite.getInstance().delete(getTableName(), fields);	
		isInDB = false;
	}
	
	protected abstract HashMap<String, Object> getValueMap();
	
	protected abstract HashMap<String, Object> getPrimaryKey();
	
	protected abstract String getTableName();
		
}
