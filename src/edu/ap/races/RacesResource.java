package edu.ap.races;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import edu.ap.jdbc.*;


public class RacesResource extends ServerResource{

	@Get
	public String getAllRaces() {
		
		
		JDBConnection c = JDBConnection.getJDBConnection();
		c.openConnection("races", "root", "root");
		List<Race> resultArray = c.selectAll();
		c.closeConnection();
		
		JSONObject json = new JSONObject();
		json.put("operation", "selectAll");
		json.put("length", resultArray.size());
		JSONArray jsonArray = new JSONArray();
		
		
		for(Race r : resultArray) {
			JSONObject obj = new JSONObject();
			obj.put("distance", r.getDistance());
			obj.put("name", r.getName());
			jsonArray.put(obj);
		}
		
		json.put("result", jsonArray);

		return json.toString();
		
		
		/*
		String races = "";
		System.out.println("test");
		JDBConnection jdbConnection = JDBConnection.getJDBConnection();
		jdbConnection.openConnection("races", "root", "root");
		
		List<Race> racesList = new ArrayList<Race>();
		racesList = jdbConnection.selectAll();
		jdbConnection.closeConnection();
		
		
		for (Race race : racesList) {
			races += "Race Name: " + race.getName() + "Race Distance: " + race.getDistance();
			races += "\n";
		}
		
		System.out.println(races);
		return races;
		*/
	}
	
	@Post("txt")
	public void newStudent(String json) {
		
		JSONObject newRace = new JSONObject(json);
		System.out.println(newRace.toString());
		String name = newRace.getString("name");
		int distance = newRace.getInt("distance");
		
		System.out.println(name+ " " + distance);		
		
		JDBConnection c = JDBConnection.getJDBConnection();
		c.openConnection("races", "root", "root");
		c.executeInsert("races", name, distance);
		c.closeConnection();
	}
}
