package utils.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.LightSensor;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import java.io.IOException;

public class LightSensorSchema implements DeserializationSchema<LightSensor>, SerializationSchema<LightSensor> {

	private static final long serialVersionUID = 1L;

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
        //System.out.println("\n\n\n\n\n\n\n\n" + jsonInString + "\n\n\n\n\n\n\n\n");
        this.mapper = new ObjectMapper();

        try {

        	LightSensor lightSensor = this.mapper.readValue(jsonInString, LightSensor.class);
            return lightSensor;

        }  catch (IOException e) {

            /**
             * .readValue catch exception over any kind of data (double, int, objects ecc ecc)
             * return null value to discard invalid tuple
             */
            System.out.println("\n\n\n\n\n\n\n\n");
            e.printStackTrace();
            System.out.println("\n\n\n\n\n\n\n\n");
            return null;

        }

    }

    @Override
    public boolean isEndOfStream(LightSensor nextElement) {
        return false;
    }

    @Override
    public TypeInformation<LightSensor> getProducedType() {
        return TypeExtractor.getForClass(LightSensor.class);
    }
}