package utils.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.LightSensor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import java.io.IOException;

/**
 * Created by maurizio on 21/03/17.
 * 
 * Implements a SerializationSchema and DeserializationSchema for Lamp for Kafka data sources and sinks.
 */
public class LightSensorSchema implements DeserializationSchema<LightSensor>, SerializationSchema<LightSensor> {

    public ObjectMapper mapper;

    @Override
    public byte[] serialize(LightSensor element) {

        this.mapper = new ObjectMapper();

        String jsonInString = new String("");
        try {

            jsonInString = mapper.writeValueAsString(element);
            return jsonInString.getBytes();

        } catch (JsonProcessingException e) {

            return null;

        }

    }

    @Override
    public LightSensor deserialize(byte[] message) {

        String jsonInString = new String(message);
        this.mapper = new ObjectMapper();
        //Lamp lamp = new Lamp();
        try {

        	LightSensor lightSensor = this.mapper.readValue(jsonInString, LightSensor.class);
            return lightSensor;

        }  catch (IOException e) {

            /**
             * .readValue catch exception over any kind of data (double, int, objects ecc ecc)
             * return null value to discard invalid tuple
             */
            return null;

        }

    }


	@Override
	public boolean isEndOfStream(LightSensor nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeInformation<LightSensor> getProducedType() {
		// TODO Auto-generated method stub
		return null;
	}
}