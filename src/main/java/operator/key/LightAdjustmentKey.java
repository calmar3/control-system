package operator.key;

import org.apache.flink.api.java.functions.KeySelector;
import model.LightAdjustment;

public class LightAdjustmentKey implements KeySelector<LightAdjustment, Long> {

	private static final long serialVersionUID = 1L;

	@Override
    public Long getKey(LightAdjustment lightAdjustment) throws Exception {
        return lightAdjustment.getLampId();
    }
}
