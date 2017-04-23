package operator.window;

import model.Lamp;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;

import model.Lamp;
import model.TDigestMedian;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class MedianConsLampFF implements FoldFunction<Lamp, Tuple2<TDigestMedian, Lamp>> {

    @Override
    public Tuple2<TDigestMedian, Lamp> fold(Tuple2<TDigestMedian, Lamp> accumulator, Lamp l) throws Exception {
        if(accumulator.f0 != null) {
            TDigestMedian median = new TDigestMedian();
            median.setTotalDigest(accumulator.f0.getTotalDigest());
            median.addDigest(l.getLightIntensity());
            return new Tuple2<>(median, l);
        }
        else {
            TDigestMedian median = new TDigestMedian();
            median.addDigest(l.getLightIntensity());
            return new Tuple2<>(median, l);
        }
    }

}