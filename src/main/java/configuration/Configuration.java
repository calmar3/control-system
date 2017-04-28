package configuration;

import java.io.FileInputStream;
import java.util.Properties;


public class Configuration {

	public long JOIN_TIME_SEC;
	public long MEDIAN_WINDOW_SIZE;
	public long SLEEP_TIME_TRAFFIC_MLS;
	public long WATERMARK_INTERVAL_SEC;


	public int FOLD_PARALLELISM;
	public int FILTER_PARALLELISM;
		
	public Double MIN_PERCENTAGE_LIGHT_DOUBLE;
	
	public String ZOOKEEPER_HOST;

	public String KAFKA_BROKER_LOCAL_CONTROLLER;
	public String KAFKA_BROKER_SENSOR_LIGHT;
	public String KAFKA_BROKER_LAMP;

	
	
	public String LAMP_TOPIC;
	public String CONTROL_TOPIC;
	public String SENSOR_TOPIC;
	
	public String TRAFFIC_HOST;
	
    public static final String FILENAME = "/home/ec2-user/config.properties";

	
	public Configuration(){
		try {
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream(FILENAME);
            
            prop.load(inputStream);
                     			  		
			JOIN_TIME_SEC=Long.parseLong(prop.getProperty("JOIN_TIME_SEC"));
			SLEEP_TIME_TRAFFIC_MLS=Long.parseLong(prop.getProperty("SLEEP_TIME_TRAFFIC_MLS"));
			MEDIAN_WINDOW_SIZE=Long.parseLong(prop.getProperty("MEDIAN_WINDOW_SIZE"));
		    WATERMARK_INTERVAL_SEC=Long.parseLong(prop.getProperty("WATERMARK_INTERVAL_SEC"));
		    FOLD_PARALLELISM=Integer.parseInt(prop.getProperty("FOLD_PARALLELISM"));
		    FILTER_PARALLELISM=Integer.parseInt(prop.getProperty("FILTER_PARALLELISM"));
	       	MIN_PERCENTAGE_LIGHT_DOUBLE=Double.parseDouble(prop.getProperty("MIN_PERCENTAGE_LIGHT_DOUBLE"));
	       	ZOOKEEPER_HOST=prop.getProperty("ZOOKEEPER_HOST");
	       	KAFKA_BROKER_LAMP=prop.getProperty("KAFKA_BROKER_LAMP");
	       	KAFKA_BROKER_LOCAL_CONTROLLER=prop.getProperty("KAFKA_BROKER_LOCAL_CONTROLLER");
	       	KAFKA_BROKER_SENSOR_LIGHT=prop.getProperty("KAFKA_BROKER_SENSOR_LIGHT");
			LAMP_TOPIC=prop.getProperty("LAMP_TOPIC");
	       	SENSOR_TOPIC=prop.getProperty("SENSOR_TOPIC");
			CONTROL_TOPIC=prop.getProperty("CONTROL_TOPIC");
			TRAFFIC_HOST=prop.getProperty("TRAFFIC_HOST");
			
			inputStream.close();
		} 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
