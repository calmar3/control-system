package operator.window;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import model.Lamp;
import model.TDigestMedian;

public class MedianLampWF implements WindowFunction<Tuple2<TDigestMedian, Lamp>, Lamp, Long, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
    public void apply(Long key, TimeWindow window, Iterable<Tuple2<TDigestMedian, Lamp>> input, Collector<Lamp> out) throws Exception {

    	Tuple2<TDigestMedian, Lamp> medianLamp = input.iterator().next();
    	Lamp clone = medianLamp.f1.clone();
    	clone.setLightIntensity(medianLamp.f0.getMedian());
    	out.collect(clone);
    }   

}