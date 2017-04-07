package operator.key;


import org.apache.flink.api.java.functions.KeySelector;

import model.LightSensor;


public class LightSensorKey implements KeySelector<LightSensor, Long> {

	private static final long serialVersionUID = 1L;

	@Override
    public Long getKey(LightSensor lightSensor) throws Exception {
        return lightSensor.getLightSensorId();
    }
}
