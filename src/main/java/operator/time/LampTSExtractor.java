package operator.time;

import model.Lamp;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

public class LampTSExtractor extends BoundedOutOfOrdernessTimestampExtractor<Lamp> {

	private static final long serialVersionUID = 1L;
	private static final long MAX_EVENT_DELAY = 1;

    public LampTSExtractor() {
        super(Time.seconds(MAX_EVENT_DELAY));
    }

    @Override
    public long extractTimestamp(Lamp lamp) {
        if(lamp != null)
            return lamp.getTimestamp();
        else
            return 0;
    }
}