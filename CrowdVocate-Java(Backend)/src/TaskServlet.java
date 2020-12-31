

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TaskServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//==============================================================================		
		//Return all users
//==============================================================================
	
		response.addHeader("Access-Control-Allow-Origin","http://localhost:4200");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers","Origin, Content-Type, Accept");
		
		JSONArray allTasks = new JSONArray();
		JSONArray jsonResponse = new JSONArray();
		allTasks = TaskManager.getAllTasks();
		//jsonResponse.add(allTasks);
		response.getWriter().append(allTasks.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		JSONArray jsonResponse = new JSONArray();
		JSONObject map = new JSONObject();
		JSONObject body = getBodyParams(request);
		if(action.equals("getUser")) {
			//return specific user
			int id;
			try {
				id = body.getInt("id");
				JSONObject task = new JSONObject(TaskManager.getTaskById(id));
				jsonResponse.add(task);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		else {
			//create new user
			String name;
			int queryResult = -1;
			try {
				name = body.getString("name");
				String description = body.getString("description");
				String status = body.getString("status");
				String startDate = body.getString("startDate");
				String endDate = body.getString("endDate");
				if(endDate.length()>0) {
					queryResult = TaskManager.createTask(name, description, status, startDate, endDate);
				}else {
					queryResult = TaskManager.createTask(name, description, status, startDate);
				}
				if(queryResult >= 0) {
					
					map.put("success","success");
					jsonResponse.add(map);	
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		response.getWriter().append(jsonResponse.toJSONString());
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonResponse = new JSONArray();
		JSONObject map = new JSONObject();
		JSONObject body = getBodyParams(request);
	
		int queryResult = -1;
		try {
			int id = body.getInt("id");
			String name = body.getString("name");
			String description = body.getString("description");
			String status = body.getString("status");
			String startDate = body.getString("startDate");
			String endDate = null;
			if(body.get("endDate").equals(null) || body.get("endDate").equals("")) {
				queryResult = TaskManager.updateTask(id,name, description, status, startDate);
			}
			else {
				endDate = body.getString("endDate");
				queryResult = TaskManager.updateTask(id,name, description, status, startDate, endDate);
			}
			if(queryResult >= 0) {
				
				map.put("success","success");
				jsonResponse.add(map);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		jsonResponse.toJSONString()
		response.getWriter().append(map.toString());

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		TaskManager.deleteTaskById(id);

	}
	
	
	
	
	
	
	
	
//======================================================================================	
	
	
	protected JSONObject getBodyParams(HttpServletRequest request) {
		//============================================================================
		//	this function extracts to body of the request
		//  and returns it as JSONObject
		//============================================================================
		StringBuilder sb = new StringBuilder();
		String line = null;
		JSONObject json = null;

		BufferedReader reader;
		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
			try {
				json = new JSONObject(sb.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return json;

	}

}
