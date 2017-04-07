package operator.window;

import model.LightSensor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


//WindowFunction<input, output, key, window>
public class AvgIntensityLight implements WindowFunction<LightSensor, LightSensor, Long, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
    public void apply (Long key, TimeWindow window, Iterable<LightSensor> input, Collector<LightSensor> out) throws Exception {

        long totalIntensity = 0;
        int n = 0;
        LightSensor lightSensor = null;
        for (LightSensor tmplightSensor: input) {
            if (lightSensor == null)
            	lightSensor = tmplightSensor;
            totalIntensity += tmplightSensor.getLightIntensity();
            System.out.println(tmplightSensor.toString());
            n++;
        }
        out.collect(new LightSensor(key, totalIntensity/n,lightSensor.getPosition()));
    }
}