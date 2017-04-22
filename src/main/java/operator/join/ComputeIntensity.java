package operator.join;
import java.math.BigDecimal;

import org.apache.flink.api.common.functions.JoinFunction;

import configuration.Configuration;
import model.HashMapStreetTraffic;
import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;

public class ComputeIntensity implements JoinFunction<Lamp, LightSensor, LightAdjustment>{

	private static final long serialVersionUID = 1L;

	@Override
	public LightAdjustment join(Lamp lamp, LightSensor lightSensor) throws Exception {
		Configuration config= new Configuration();
				
		LightAdjustment la = new LightAdjustment();
		la.setLightIntensityAdjustment(0.0);
		la.setLampId(lamp.getLampId());
		
		double trafficPercentual= HashMapStreetTraffic.getInstance().get(lamp.getAddress()); 
		
		if(lightSensor.getLightIntensity()>=0.5){
			la.setLightIntensityAdjustment((lamp.getLightIntensity())*(-1));
			return la;
		}
		else if(trafficPercentual<config.MIN_PERCENTAGE_LIGHT_DOUBLE){
			BigDecimal bg = new BigDecimal((Math.abs(config.MIN_PERCENTAGE_LIGHT_DOUBLE-lightSensor.getLightIntensity()))-lamp.getLightIntensity()); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(intensity);
			return la;
		}
		else if(lamp.getLightIntensity()!=trafficPercentual-lightSensor.getLightIntensity()){
			BigDecimal bg = new BigDecimal(Math.abs(trafficPercentual-lightSensor.getLightIntensity())-lamp.getLightIntensity()); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(intensity);
			return la;
		}
		return la;
	}
}