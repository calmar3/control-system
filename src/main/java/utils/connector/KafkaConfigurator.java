package utils.connector;

import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;

import configuration.Configuration;
import utils.serialization.LampSchema;
import utils.serialization.LightAdjustmentSchema;
import utils.serialization.LightSensorSchema;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by olga.
 */
public class KafkaConfigurator {

	

    public static final FlinkKafkaConsumer010<Lamp> getConsumer() throws IOException, ParseException {
    	Configuration config = new Configuration();
        // configure the Kafka consumer
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("zookeeper.connect", config.LOCAL_ZOOKEEPER_HOST);
        kafkaProps.setProperty("bootstrap.servers", config.LOCAL_KAFKA_BROKER);
 
        // create a Kafka consumer
        FlinkKafkaConsumer010<Lamp> consumer = new FlinkKafkaConsumer010<>(
                config.LAMP_TOPIC,          //kafka topic
                new LampSchema(),   //deserialization schema
                kafkaProps);        //consumer configuration

        return consumer;
    }

    public static final FlinkKafkaConsumer010<LightSensor> getConsumerSensor() throws IOException, ParseException {
    	Configuration config = new Configuration();
    	
        // configure the Kafka consumer
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("zookeeper.connect", config.LOCAL_ZOOKEEPER_HOST);
        kafkaProps.setProperty("bootstrap.servers", config.LOCAL_KAFKA_BROKER);

        // create a Kafka consumer
        FlinkKafkaConsumer010<LightSensor> consumer = new FlinkKafkaConsumer010<>(
                config.SENSOR_TOPIC,          //kafka topic
                new LightSensorSchema(),   //deserialization schema
                kafkaProps);        //consumer configuration

        return consumer;
    }


     
    public static final void getProducerAdjustmentIntensity(DataStream<LightAdjustment> lightAdjustmentStream) throws IOException, ParseException {
    	Configuration config = new Configuration();
    	
        //write data to a Kafka sink
    	lightAdjustmentStream.addSink(new FlinkKafkaProducer010<>(
                config.LOCAL_KAFKA_BROKER,
                config.CONTROL_TOPIC,
                new LightAdjustmentSchema()
        ));
    }
}
