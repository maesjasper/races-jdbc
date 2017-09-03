package edu.ap.races;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class RacesServer {
	
	public static void main(String[] args) {
		
		try {		
		    Component component = new Component();
		    component.getServers().add(Protocol.HTTP, 8086);	    
		    component.getDefaultHost().attach("/races", new RacesApplication());
			component.start();
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
