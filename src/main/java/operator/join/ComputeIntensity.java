package operator.join;
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
		la.setLightIntensityAdjustment(lamp.getLightIntensity());
		la.setLampId(lamp.getLampId());
		
		double trafficPercentual= HashMapStreetTraffic.getInstance().get(lamp.getAddress()); 
		
		if(lightSensor.getLightIntensity()>=0.5){
			la.setLightIntensityAdjustment((lamp.getLightIntensity())*(-1));
			return la;
		}
		else if(trafficPercentual<config.MIN_PERCENTAGE_LIGHT_DOUBLE){
			la.setLightIntensityAdjustment((Math.abs(config.MIN_PERCENTAGE_LIGHT_DOUBLE-lightSensor.getLightIntensity()))-lamp.getLightIntensity());
			return la;
		}
		else if(lamp.getLightIntensity()!=trafficPercentual-lightSensor.getLightIntensity()){
			la.setLightIntensityAdjustment(Math.abs(trafficPercentual-lightSensor.getLightIntensity())-lamp.getLightIntensity());
			return la;
		}
		return la;
	}
}