package operator.join;
import java.math.BigDecimal;

import org.apache.flink.api.common.functions.JoinFunction;

import configuration.Configuration;
import model.HashMapStreetTraffic;
import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;

/**
 * Created by olga.
 */

public class ComputeIntensity implements JoinFunction<Lamp, LightSensor, LightAdjustment>{

	private static final long serialVersionUID = 1L;

	@Override
	public LightAdjustment join(Lamp lamp, LightSensor lightSensor) throws Exception {
		Configuration config= new Configuration();
				
		LightAdjustment la = new LightAdjustment();
		la.setLightIntensityAdjustment(0.0);
		la.setLampId(lamp.getLampId());
		
		double trafficPercentual= HashMapStreetTraffic.getInstance().get(lamp.getAddress()); 
		
		BigDecimal bg = new BigDecimal(lightSensor.getLightIntensity()); 
		bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
		double lightIntensity= bg.doubleValue();
		
		if(Double.compare(lightIntensity,0.5)>0 || Double.compare(lightIntensity,0.5)==0){
			bg = new BigDecimal((lamp.getLightIntensity())*(-1)); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double Intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(Intensity);
			return la;
		} 
		else if(Double.compare(trafficPercentual,config.MIN_PERCENTAGE_LIGHT_DOUBLE)<0 && Double.compare(config.MIN_PERCENTAGE_LIGHT_DOUBLE,lightSensor.getLightIntensity())>0){
			bg = new BigDecimal((Math.abs(config.MIN_PERCENTAGE_LIGHT_DOUBLE-lightSensor.getLightIntensity()))-lamp.getLightIntensity()); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(intensity);
			return la;
		}
		else if(Double.compare(lamp.getLightIntensity(),trafficPercentual-lightSensor.getLightIntensity())!=0 && Double.compare(trafficPercentual,lightSensor.getLightIntensity())>0){
			bg = new BigDecimal(Math.abs(trafficPercentual-lightSensor.getLightIntensity())-lamp.getLightIntensity()); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(intensity);
			return la;
		}
		else if(Double.compare(config.MIN_PERCENTAGE_LIGHT_DOUBLE,lightSensor.getLightIntensity())>0 || Double.compare(trafficPercentual,lightSensor.getLightIntensity())>0){
			bg = new BigDecimal((lamp.getLightIntensity())*(-1)); 
			bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			double Intensity= bg.doubleValue();
			la.setLightIntensityAdjustment(Intensity);
			return la;
		} 
		return la;
	}
}