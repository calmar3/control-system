package operator.window;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import model.LightSensor;
import model.TDigestMedian;

public class MedianSensorWF implements WindowFunction<Tuple2<TDigestMedian, LightSensor>, LightSensor, Long, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
    public void apply(Long key, TimeWindow window, Iterable<Tuple2<TDigestMedian, LightSensor>> input, Collector<LightSensor> out) throws Exception {

    	Tuple2<TDigestMedian, LightSensor> medianLightSensor = input.iterator().next();
    	LightSensor clone = medianLightSensor.f1.clone();
    	clone.setLightIntensity(medianLightSensor.f0.getMedian());
    	out.collect(clone);
    }   

}