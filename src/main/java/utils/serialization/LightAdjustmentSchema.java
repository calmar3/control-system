package utils.serialization;

import model.LightAdjustment;
import org.apache.flink.streaming.util.serialization.SerializationSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LightAdjustmentSchema implements SerializationSchema<LightAdjustment> {

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
	}