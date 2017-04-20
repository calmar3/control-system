package operator.window;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import model.Lamp;

public class LampWindowFunction implements WindowFunction<Tuple2<Lamp, Long>, Lamp, Long, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
    public void apply (Long key, TimeWindow timeWindow, Iterable<Tuple2<Lamp, Long>> input, Collector<Lamp> out) throws Exception {

        Tuple2<Lamp, Long> totIntensity = input.iterator().next();
        out.collect( new Lamp(key,((Lamp)totIntensity.f0).getLightIntensity()/ totIntensity.f1, ((Lamp)totIntensity.f0).getAddress(),((Lamp)totIntensity.f0).getTimestamp()));
	}    

}
