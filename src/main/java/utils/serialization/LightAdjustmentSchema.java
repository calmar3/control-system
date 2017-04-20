package utils.serialization;

import java.io.IOException;

import model.Lamp;
import model.LightAdjustment;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LightAdjustmentSchema implements DeserializationSchema<LightAdjustment>, SerializationSchema<LightAdjustment> {

	private static final long serialVersionUID = 1L;
	
	public ObjectMapper mapper;

    @Override
    public byte[] serialize(LightAdjustment element) {

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
	public LightAdjustment deserialize(byte[] message) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEndOfStream(LightAdjustment nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeInformation<LightAdjustment> getProducedType() {
		// TODO Auto-generated method stub
        return TypeExtractor.getForClass(LightAdjustment.class);

	}

}