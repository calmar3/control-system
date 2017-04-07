package model;

import java.io.Serializable;

public class LightSensor implements Serializable {

	private static final long serialVersionUID = 1L;
	private long lightSensorId;
    private double lightIntensity;
    private long timeStamp;
    private String position;

    public LightSensor() {}

    public LightSensor(long lightSensorId, double lightIntensity, String position){
        this.setLightSensorId(lightSensorId);
        this.setPosition(position);
        this.setLightIntensity(lightIntensity);
    }
    
    public LightSensor(long lightSensorId, double lightIntensity,long timeStamp, String position){
        this.setLightSensorId(lightSensorId);
        this.setPosition(position);
        this.setTimeStamp(timeStamp);
        this.setLightIntensity(lightIntensity);
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

	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
