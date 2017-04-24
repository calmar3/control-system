package operator.window;

import model.HashMapStreetTraffic;
import model.LightSensor;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;

import model.TDigestMedian;


public class MedianSensorFF implements FoldFunction<LightSensor, Tuple2<TDigestMedian, LightSensor>> {

	private static final long serialVersionUID = 1L;

	@Override
    public Tuple2<TDigestMedian, LightSensor> fold(Tuple2<TDigestMedian, LightSensor> accumulator, LightSensor l) throws Exception {
        if(accumulator.f0 != null) {
        	Double traffic= HashMapStreetTraffic.getInstance().get((accumulator.f1).getAddress());
    		if(traffic==null){
    			HashMapStreetTraffic.getInstance().put((accumulator.f1).getAddress(),0.1);
    		}
            TDigestMedian median = new TDigestMedian();
            median.setTotalDigest(accumulator.f0.getTotalDigest());
            median.addDigest(l.getLightIntensity());
            return new Tuple2<>(median, l);
        }
        else {
        	Double traffic= HashMapStreetTraffic.getInstance().get(l.getAddress());
    		if(traffic==null){
    			HashMapStreetTraffic.getInstance().put(l.getAddress(),0.1);
    		}
            TDigestMedian median = new TDigestMedian();
            median.addDigest(l.getLightIntensity());
            return new Tuple2<>(median, l);
        }
    }

}
