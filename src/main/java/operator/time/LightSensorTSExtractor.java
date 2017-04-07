package operator.time;

import model.LightSensor;

import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;


public class LightSensorTSExtractor extends BoundedOutOfOrdernessTimestampExtractor<LightSensor> {

	private static final long serialVersionUID = 1L;
	
	private static final long MAX_EVENT_DELAY = 1 ;

    public LightSensorTSExtractor() {
        super(Time.seconds(MAX_EVENT_DELAY));
    }

    @Override
    public long extractTimestamp(LightSensor lightSensor) {
            //System.out.println(lightSensor.getTimestamp());
            return lightSensor.getTimeStamp();
    }
}