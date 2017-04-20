package control;

import java.io.IOException;
import java.text.ParseException;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import configuration.Configuration;

/**
 * Created by olga.
 */
public class EnvConfigurator {

    public static final StreamExecutionEnvironment setupExecutionEnvironment(/*AppConfiguration config*/) throws IOException, ParseException {
    	Configuration config = new Configuration();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // generate a Watermark every second
        env.getConfig().setAutoWatermarkInterval(config.WATERMARK_INTERVAL_SEC);

        return env;
    }

}
