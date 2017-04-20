package operator.filter;

import model.Lamp;
import model.LightSensor;

import org.apache.flink.api.common.functions.FilterFunction;

/**
 * Created by marco on 25/03/17.
 */
public final class LightSensorFilter implements FilterFunction<LightSensor> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean filter(LightSensor lightSensor) throws Exception {

        /**
         * discard invalid tuple
         */
        if (lightSensor == null) {
            return false;
        }
        /**
         * discard tuple not in avl
         */
        /*if (LampsAvl.getInstance().get(new Long(lamp.getId())) == null)
            return false;
        else {
            //lamp.toString();
            return true;
        }*/
        else {
            //System.out.println("\n\n\n\n\n\n\n\n\n" + lamp.toString() + "\n\n\n\n\n\n\n\n");
            return true;
        }

    }
}