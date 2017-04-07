package model;

import java.util.HashMap;


public class HashMapStreetTraffic {
	
	private static HashMap<String, Double> instance = null;
	
	protected HashMapStreetTraffic(){}
	
	public synchronized static final HashMap<String, Double> getInstance(){
		
		if(instance == null)
			instance = new HashMap<String, Double>();
		
		return instance;
	}
	
}