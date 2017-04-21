package model;

import java.util.HashMap;

public class HashMapLightAdjustment {
	
	private static HashMap<Long, Double> instance = null;
	
	protected HashMapLightAdjustment(){}
	
	public synchronized static final HashMap<Long, Double> getInstance(){
		
		if(instance == null)
			instance = new HashMap<Long, Double>();
		
		return instance;
	}

}