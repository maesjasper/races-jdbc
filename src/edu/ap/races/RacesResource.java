package edu.ap.races;


import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import edu.ap.jdbc.*;


public class RacesResource extends ServerResource{

	@Get
	public String getAllRaces() {
		
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
	}
}
