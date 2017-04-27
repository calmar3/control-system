package model;

import java.io.Serializable;

/**
 * Created by olga.
 */

public class TrafficStreet implements Serializable {

	private static final long serialVersionUID = 1L;
    private double trafficIntensity;
    private String position;

    public TrafficStreet() {}

    public TrafficStreet(String position,double trafficIntensity){
        this.setTrafficIntensity(trafficIntensity);
        this.setPosition(position);
    }

	public double getTrafficIntensity() {
		return trafficIntensity;
	}

	public void setTrafficIntensity(double trafficIntensity) {
		this.trafficIntensity = trafficIntensity;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	 public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----");
        sb.append(this.trafficIntensity).append(", ");
        sb.append(this.position);
        sb.append("-----");
        
        return sb.toString();
    }

}
