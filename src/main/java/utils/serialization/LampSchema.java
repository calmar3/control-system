package utils.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Lamp;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import java.io.IOException;

public class LampSchema implements DeserializationSchema<Lamp>, SerializationSchema<Lamp> {

	private static final long serialVersionUID = 1L;

	public ObjectMapper mapper;

    @Override
    public byte[] serialize(Lamp element) {

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
    public Lamp deserialize(byte[] message) {

        String jsonInString = new String(message);
        this.mapper = new ObjectMapper();

        try {

            Lamp lamp = this.mapper.readValue(jsonInString, Lamp.class);
            return lamp;

        }  catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public boolean isEndOfStream(Lamp nextElement) {
        return false;
    }

    @Override
    public TypeInformation<Lamp> getProducedType() {
        return TypeExtractor.getForClass(Lamp.class);
    }
}