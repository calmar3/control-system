package model;

import java.io.Serializable;

public class LightSensor implements Serializable {

	private static final long serialVersionUID = 1L;
	private long lightSensorId;
    private double lightIntensity;
    private long timestamp;
    private String position;

    public LightSensor() {}

    public LightSensor(long lightSensorId, double lightIntensity, String position){
        this.setLightSensorId(lightSensorId);
        this.setPosition(position);
        this.setLightIntensity(lightIntensity);
    }
    
    public LightSensor(long lightSensorId, double lightIntensity,long timestamp, String position){
        this.setLightSensorId(lightSensorId);
        this.setPosition(position);
        this.setTimestamp(timestamp);
        this.setLightIntensity(lightIntensity);
    }
    
    public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getLightIntensity() {
		return lightIntensity;
	}

	public void setLightIntensity(double lightIntensity) {
		this.lightIntensity = lightIntensity;
	}

	public Long getLightSensorId() {
		return this.lightSensorId;
	}

	public void setLightSensorId(Long lightSensorId) {
		this.lightSensorId = lightSensorId;
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
        sb.append(this.lightSensorId).append(", ");
        sb.append(this.lightIntensity).append(", ");
        sb.append(this.position);
        sb.append("-----");
        
        return sb.toString();
    }

}
