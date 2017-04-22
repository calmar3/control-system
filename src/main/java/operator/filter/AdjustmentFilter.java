package operator.filter;

import model.HashMapLightAdjustment;
import model.LightAdjustment;

import java.util.HashMap;

import org.apache.flink.api.common.functions.FilterFunction;


public class AdjustmentFilter implements FilterFunction<LightAdjustment> {
		
	private static final long serialVersionUID = 1L;
		
	@Override
	public boolean filter(LightAdjustment lightAdjustment) throws Exception {
		HashMap<Long, Double> LA= HashMapLightAdjustment.getInstance();
		Double adjustment= LA.get(lightAdjustment.getLampId());
		if(adjustment==null){
			LA.put(lightAdjustment.getLampId(),lightAdjustment.getLightIntensityAdjustment());
			 return true;
		}
		else if(Double.compare(adjustment,lightAdjustment.getLightIntensityAdjustment())==0)
	            return false;
	   else{
		   	LA.replace(lightAdjustment.getLampId(), lightAdjustment.getLightIntensityAdjustment());
	        return true;
	   }
	}
}
