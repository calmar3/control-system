package model;


public class LightSensor implements Cloneable{

	private long lightSensorId;
    private double lightIntensity;
    private long timestamp;
    private String address;

    public LightSensor() {}

    public LightSensor(long lightSensorId, double lightIntensity, String address){
        this.setLightSensorId(lightSensorId);
        this.setAddress(address);
        this.setLightIntensity(lightIntensity);
    }
    
    public LightSensor(long lightSensorId, double lightIntensity,long timestamp, String address){
        this.setLightSensorId(lightSensorId);
        this.setAddress(address);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public LightSensor clone() throws CloneNotSupportedException {
	      return (LightSensor) super.clone();
	}
	   
	 public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----");
        sb.append(this.lightSensorId).append(", ");
        sb.append(this.lightIntensity).append(", ");
        sb.append(this.address);
        sb.append("-----");
        
        return sb.toString();
    }

}
