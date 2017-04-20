package operator.filter;

import model.LightAdjustment;
import org.apache.flink.api.common.functions.FilterFunction;


public class IntensityFilter implements FilterFunction<LightAdjustment> {
		
	private static final long serialVersionUID = 1L;
	
	private LightAdjustment lastLightAdjustment=null;
	
	@Override
	public boolean filter(LightAdjustment lightAdjustment) throws Exception {
	    if(lastLightAdjustment != null) {
	        if(lastLightAdjustment.getLightIntensityAdjustment() == lightAdjustment.getLightIntensityAdjustment())
	            return false;
	        else
	            return true;
	    }
	    else {
	    	lastLightAdjustment = lightAdjustment;
	        return true;
	    }
	}
}
