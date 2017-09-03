package edu.ap.races;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import edu.ap.jdbc.*;


public class RacesResource extends ServerResource{

	@Get
	public String getAllRaces() {
		
		//Philippe manier
		JDBConnection c = JDBConnection.getJDBConnection();
		c.openConnection("races", "root", "root");
		ArrayList<String> resultArray = c.selectAll();
		c.closeConnection();
		
		JSONObject json = new JSONObject();
		json.put("operation", "selectAll");
		json.put("length", resultArray.size());
		JSONArray jsonArray = new JSONArray();
		
		int i = 0;
		for(String s : resultArray) {
			JSONObject obj = new JSONObject();
			obj.put("" + i, s);
			jsonArray.put(obj);
			i++;
		}
		
		json.put("result", jsonArray);

		return json.toString();
		
		//Jasper Manier
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
}
