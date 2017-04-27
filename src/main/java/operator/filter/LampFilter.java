package operator.filter;

import model.Lamp;
import org.apache.flink.api.common.functions.FilterFunction;

/**
 * Created by olga.
 */

public final class LampFilter implements FilterFunction<Lamp> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean filter(Lamp lamp) throws Exception {

        if (lamp == null) {
            return false;
        }
        else {
            return true;
        }

    }
}