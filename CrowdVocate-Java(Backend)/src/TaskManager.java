import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TaskManager {

	public static JSONArray getAllTasks(){
		JSONArray allTasks = new JSONArray();
		JSONObject json = new JSONObject();	
		String query = "SELECT * FROM task;";
		DBManager.runSelect(query, (res)->{
			try {
			LinkedHashMap<String,String> tasks = new LinkedHashMap<String,String>();
			tasks.put("id",String.valueOf(res.getInt("id")));
			tasks.put("name",res.getString("name"));
			tasks.put("description",res.getString("description"));
			tasks.put("status",res.getString("status"));
			tasks.put("startDate",res.getString("startDate"));
			tasks.put("endDate",res.getString("endDate"));
			allTasks.add(tasks);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return allTasks;
	}
	
	public static HashMap<String,String> getTaskById(int id) {
		String query = String.format("SELECT * FROM task WHERE id=%d;", id);
		HashMap<String,String> task = new HashMap<String,String>();
		DBManager.runSelect(query, (res)->{
			try {
				task.put("id",String.valueOf(res.getInt("id")));
				task.put("name",res.getString("name"));
				task.put("description",res.getString("description"));
				task.put("status",res.getString("status"));
				task.put("startDate",res.getString("startDate"));
				task.put("endDate",res.getString("endDate"));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return task;
	}
	
	public static int createTask(String name,String description,String status,String startDate,String endDate) {
		String query = String.format("INSERT INTO task(name,description,status,startDate,endDate) VALUES"
				+ "('%s','%s','%s','%s','%s');", name,description,status,startDate,endDate);
		int x = DBManager.runExecute(query);
		return x;
	}
	
	public static int createTask(String name,String description,String status,String startDate) {
		String query = String.format("INSERT INTO task(name,description,status,startDate) VALUES"
				+ "('%s','%s','%s','%s');", name,description,status,startDate);
		int x = DBManager.runExecute(query);
		return x;
	}
	
	public static int updateTask(int id,String name,String description,String status,String startDate,String endDate) {
		String query = String.format("UPDATE task SET name = '%s', description = '%s', "
				+ "status = '%s', startDate = '%s', endDate = '%s' WHERE id = %d;",
				name,description,status,startDate,endDate,id);
		int x = DBManager.runExecute(query);
		return x;
	}
	
	public static int updateTask(int id,String name,String description,String status,String startDate) {
		String query = String.format("UPDATE task SET name = '%s', description = '%s', "
				+ "status = '%s', startDate = '%s', endDate = null WHERE id = %d;",
				name,description,status,startDate,id);
		int x = DBManager.runExecute(query);
		return x;
	}
	
	public static int deleteTaskById(int id) {
		String query = String.format("DELETE FROM task WHERE id = %d ;", id);
		int x = DBManager.runExecute(query);
		return x;
	}
}
