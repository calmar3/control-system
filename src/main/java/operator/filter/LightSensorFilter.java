package operator.filter;

import model.LightSensor;

import org.apache.flink.api.common.functions.FilterFunction;

public final class LightSensorFilter implements FilterFunction<LightSensor> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean filter(LightSensor lightSensor) throws Exception {

        if (lightSensor == null) {
            return false;
        }
        else {
            return true;
        }

    }
}