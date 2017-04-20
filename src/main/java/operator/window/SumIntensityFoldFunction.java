package operator.window;

import model.HashMapStreetTraffic;
import model.LightSensor;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class SumIntensityFoldFunction implements FoldFunction<LightSensor, Tuple2<LightSensor, Long>> {

	private static final long serialVersionUID = 1L;

	@Override
    public Tuple2<LightSensor, Long> fold(Tuple2<LightSensor, Long> in, LightSensor l) throws Exception {
        if(in.f0 != null) {
        	Double traffic= HashMapStreetTraffic.getInstance().get((in.f0).getPosition());
    		if(traffic==null){
    			HashMapStreetTraffic.getInstance().put((in.f0).getPosition(),0.1);
    		}
            return new Tuple2<LightSensor, Long>(new LightSensor(l.getLightSensorId(), (in.f0).getLightIntensity() + l.getLightIntensity(),l.getTimestamp(),l.getPosition()), in.f1 + 1);
        }  
        else {
        	Double traffic= HashMapStreetTraffic.getInstance().get(l.getPosition());
    		if(traffic==null){
    			HashMapStreetTraffic.getInstance().put(l.getPosition(),0.1);
    		}
            return new Tuple2<>(l, (long)1);
        }
    }
}
