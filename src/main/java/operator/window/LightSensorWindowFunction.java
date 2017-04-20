package operator.window;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import model.LightSensor;


public class LightSensorWindowFunction implements WindowFunction<Tuple2<LightSensor, Long>, LightSensor, Long, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
    public void apply (Long key, TimeWindow timeWindow, Iterable<Tuple2<LightSensor, Long>> input, Collector<LightSensor> out) throws Exception {

        Tuple2<LightSensor, Long> totIntensity = input.iterator().next();

        out.collect(new LightSensor(key, ((LightSensor)totIntensity.f0).getLightIntensity()/ totIntensity.f1, ((LightSensor)totIntensity.f0).getTimestamp(), ((LightSensor)totIntensity.f0).getPosition()));
    }
}
