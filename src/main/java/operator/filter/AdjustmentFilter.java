package operator.filter;

import model.LightAdjustment;


import org.apache.flink.api.common.functions.FilterFunction;


public class AdjustmentFilter implements FilterFunction<LightAdjustment> {
		
	private static final long serialVersionUID = 1L;
		
	@Override
	public boolean filter(LightAdjustment lightAdjustment) throws Exception {
	
	   if(Double.compare(lightAdjustment.getLightIntensityAdjustment(),0.0)==0)
	            return false;
	   else{
	        return true;
	   }
	}
}
