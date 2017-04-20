package operator.window;

import model.Lamp;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class SumLampIntensityFoldFunction implements FoldFunction<Lamp, Tuple2<Lamp, Long>> {

	private static final long serialVersionUID = 1L;

	@Override
    public Tuple2<Lamp, Long> fold(Tuple2<Lamp, Long> in, Lamp l) throws Exception {
        if(in.f0 != null) {
            return new Tuple2<Lamp, Long>(new Lamp(l.getLampId(), (in.f0).getLightIntensity() + l.getLightIntensity(),l.getAddress(),l.getTimestamp()), in.f1 + 1);
        }  
        else {
            return new Tuple2<>(l, (long)1);
        }
    }
}
