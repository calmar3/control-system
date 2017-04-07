package operator.join;
import org.apache.flink.api.common.functions.JoinFunction;

import model.HashMapStreetTraffic;
import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;

public class ComputeIntensity implements JoinFunction<Lamp, LightSensor, LightAdjustment>{

	private static final long serialVersionUID = 1L;

	@Override
	public LightAdjustment join(Lamp lamp, LightSensor lightSensor) throws Exception {
		double intensityLight=lamp.getLightIntensity()+lightSensor.getLightIntensity();
		LightAdjustment la = new LightAdjustment();
		la.setLightIntensityAdjustment(0);
		la.setLampId(lamp.getId());
		double trafficPercentual= HashMapStreetTraffic.getInstance().get(lamp.getAddress()); 
		if(intensityLight>1){
			if(lightSensor.getLightIntensity()>=0.5){
				la.setLightIntensityAdjustment(lamp.getLightIntensity()*(-1));
			}
			la.setLightIntensityAdjustment((intensityLight-1)*(-1));
		}
		if(intensityLight==1){
			if(lightSensor.getLightIntensity()>=0.5){
				la.setLightIntensityAdjustment(lamp.getLightIntensity()*(-1));
			}
		}
		if(intensityLight<1){
			if(lightSensor.getLightIntensity()>=0.5){
				la.setLightIntensityAdjustment(lamp.getLightIntensity()*(-1));
			}else{
				if(trafficPercentual>0.5){
					la.setLightIntensityAdjustment(1-lamp.getLightIntensity()-lamp.getLightIntensity());
				}
								
			}	
		}
		return la;
	}

}
