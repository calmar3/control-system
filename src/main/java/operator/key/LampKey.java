package operator.key;

import org.apache.flink.api.java.functions.KeySelector;
import model.Lamp;

public class LampKey implements KeySelector<Lamp, Long> {

   
	private static final long serialVersionUID = 1L;

	@Override
    public Long getKey(Lamp lamp) throws Exception {
        return lamp.getLampId();
    }
}
