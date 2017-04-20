package configuration;

import java.io.FileInputStream;
import java.util.Properties;


public class Configuration {

	public long TIME_WINDOW_SENSOR_LIGHT_SEC;
	public long TIME_WINDOW_LAMP_SEC;
	public long JOIN_TIME_SEC;
	public long WATERMARK_INTERVAL_SEC;


	public int FOLD_PARALLELISM;
	public int FILTER_PARALLELISM;
		
	public Double MIN_PERCENTAGE_LIGHT_DOUBLE;
	
	public String LOCAL_ZOOKEEPER_HOST;
	public String LOCAL_KAFKA_BROKER;
	
	public String LAMP_TOPIC;
	public String CONTROL_TOPIC;
	public String SENSOR_TOPIC;
	
    public static final String FILENAME = "configuration/config.properties";

	
	public Configuration(){
		try {
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream(FILENAME);
            
            prop.load(inputStream);
                     			  		
			TIME_WINDOW_SENSOR_LIGHT_SEC=Long.parseLong(prop.getProperty("TIME_WINDOW_SENSOR_LIGHT_SEC"));
			TIME_WINDOW_LAMP_SEC=Long.parseLong(prop.getProperty("TIME_WINDOW_LAMP_SEC"));
		    JOIN_TIME_SEC=Long.parseLong(prop.getProperty("JOIN_TIME_SEC"));
		    WATERMARK_INTERVAL_SEC=Long.parseLong(prop.getProperty("WATERMARK_INTERVAL_SEC"));
		    FOLD_PARALLELISM=Integer.parseInt(prop.getProperty("FOLD_PARALLELISM"));
		    FILTER_PARALLELISM=Integer.parseInt(prop.getProperty("FOLD_PARALLELISM"));
	       	MIN_PERCENTAGE_LIGHT_DOUBLE=Double.parseDouble(prop.getProperty("MIN_PERCENTAGE_LIGHT_DOUBLE"));
	       	LOCAL_ZOOKEEPER_HOST=prop.getProperty("LOCAL_ZOOKEEPER_HOST");
	       	LOCAL_KAFKA_BROKER=prop.getProperty("LOCAL_KAFKA_BROKER");
			LAMP_TOPIC=prop.getProperty("LAMP_TOPIC");
	       	SENSOR_TOPIC=prop.getProperty("SENSOR_TOPIC");
			CONTROL_TOPIC=prop.getProperty("CONTROL_TOPIC");
	    
		} 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
