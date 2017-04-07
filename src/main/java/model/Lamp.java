package model;

/**
 * Created by maurizio on 21/03/17.
 */
public class Lamp {


    private long lampId;
    private double consumption;
    private String address;
    private long timestamp;
    private double lightIntensity;


    public Lamp() {}

    public Lamp(long lampId, double consumption){
        this.lampId = lampId;
        this.consumption = consumption;
    }

    public Lamp(long lampId,String address,double lightIntensity,long timestamp){
    	  this.lampId = lampId;
          this.lightIntensity = lightIntensity;
          this.address = address;
          this.timestamp = timestamp;
    	
    }
    
    public Lamp(long lampId, double consumption,String address){
        this.lampId = lampId;
        this.consumption = consumption;
        this.address = address;
    }

    public Lamp(long lampId, double consumption,String address, long timestamp){
        this.lampId = lampId;
        this.consumption = consumption;
        this.address = address;
        this.timestamp = timestamp;
    }

    public long getId() {
        return lampId;
    }

    public void setId(long lampId) {
        this.lampId = lampId;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public double getLightIntensity() {
		return this.lightIntensity;
	}
	
	public void setLightIntensity(double lightIntensity) {
		this.lightIntensity=lightIntensity;
	}


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lampId).append(",");
        sb.append(this.consumption).append(",");
        sb.append(this.lightIntensity).append(",");
        sb.append(this.address);
        sb.append(" timestamp : ").append(this.timestamp);

        return sb.toString();
    }




    public static Lamp fromString(String line) {

        String[] tokens = line.split(",");
        if (tokens.length != 2) {
            throw new RuntimeException("Invalid record: " + line);
        }

        Lamp ride = new Lamp();

        try {
            ride.lampId = Long.parseLong(tokens[0]);
            ride.consumption = Double.parseDouble(tokens[1]);

        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Invalid record: " + line, nfe);
        }

        return ride;
    }


}
