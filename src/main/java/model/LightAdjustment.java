package model;

import java.io.Serializable;

public class LightAdjustment implements Serializable {

	private static final long serialVersionUID = 1L;
	private long lampId;
    private double lightIntensityAdjustment;

    public LightAdjustment() {}

    public LightAdjustment(long lampId, double lightIntensityAdjustment){
        this.setLampId(lampId);
        this.setLightIntensityAdjustment(lightIntensityAdjustment);
    }

	public double getLightIntensityAdjustment() {
		return lightIntensityAdjustment;
	}

	public void setLightIntensityAdjustment(double lightIntensityAdjustment) {
		this.lightIntensityAdjustment = lightIntensityAdjustment;
	}

	public Long getLampId() {
		return this.lampId;
	}

	public void setLampId(Long lampId) {
		this.lampId = lampId;
	}

	
	 public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----");
        sb.append(this.lampId).append(", ");
        sb.append(this.lightIntensityAdjustment).append(", ");
        sb.append("-----");
        
        return sb.toString();
    }

}
