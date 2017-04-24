package configuration;

import java.io.FileInputStream;
import java.util.Properties;


public class Configuration {

	public long JOIN_TIME_SEC;
	public long MEDIAN_WINDOW_SLIDE;
	public long MEDIAN_WINDOW_SIZE;
	public long SLEEP_TIME_TRAFFIC_MLS;

	public long WATERMARK_INTERVAL_SEC;


	public int FOLD_PARALLELISM;
	public int FILTER_PARALLELISM;
		
	public Double MIN_PERCENTAGE_LIGHT_DOUBLE;
	
	public String LOCAL_ZOOKEEPER_HOST;
	public String LOCAL_KAFKA_BROKER;
	
	public String LAMP_TOPIC;
	public String CONTROL_TOPIC;
	public String SENSOR_TOPIC;
	
	public String TRAFFIC_HOST;
	
    public static final String FILENAME = "configuration/config.properties";

	
	public Configuration(){
		try {
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream(FILENAME);
            
            prop.load(inputStream);
                     			  		
			JOIN_TIME_SEC=Long.parseLong(prop.getProperty("JOIN_TIME_SEC"));
			SLEEP_TIME_TRAFFIC_MLS=Long.parseLong(prop.getProperty("SLEEP_TIME_TRAFFIC_MLS"));
			MEDIAN_WINDOW_SLIDE=Long.parseLong(prop.getProperty("MEDIAN_WINDOW_SLIDE"));
			MEDIAN_WINDOW_SIZE=Long.parseLong(prop.getProperty("MEDIAN_WINDOW_SIZE"));
		    WATERMARK_INTERVAL_SEC=Long.parseLong(prop.getProperty("WATERMARK_INTERVAL_SEC"));
		    FOLD_PARALLELISM=Integer.parseInt(prop.getProperty("FOLD_PARALLELISM"));
		    FILTER_PARALLELISM=Integer.parseInt(prop.getProperty("FILTER_PARALLELISM"));
	       	MIN_PERCENTAGE_LIGHT_DOUBLE=Double.parseDouble(prop.getProperty("MIN_PERCENTAGE_LIGHT_DOUBLE"));
	       	LOCAL_ZOOKEEPER_HOST=prop.getProperty("LOCAL_ZOOKEEPER_HOST");
	       	LOCAL_KAFKA_BROKER=prop.getProperty("LOCAL_KAFKA_BROKER");
			LAMP_TOPIC=prop.getProperty("LAMP_TOPIC");
	       	SENSOR_TOPIC=prop.getProperty("SENSOR_TOPIC");
			CONTROL_TOPIC=prop.getProperty("CONTROL_TOPIC");
			TRAFFIC_HOST=prop.getProperty("TRAFFIC_HOST");
	    
		} 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
