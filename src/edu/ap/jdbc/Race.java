package edu.ap.jdbc;

public class Race {

	private int id;
	private String name;
	private int distance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Race(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}
	public Race() {
	}
		
}
