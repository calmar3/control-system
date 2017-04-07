package utils.connector;

import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import utils.serialization.LampSchema;
import utils.serialization.LightAdjustmentSchema;
import utils.serialization.LightSensorSchema;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * Created by maurizio on 28/03/17.
 */
public class KafkaConfigurator {

    private static final String LOCAL_ZOOKEEPER_HOST = "localhost:2181";
    private static final String LOCAL_KAFKA_BROKER = "localhost:9092";
    private static final String LAMP_TOPIC = "lampInfo";
    private static final String SENSOR_TOPIC = "sensorInfo";


    public static final FlinkKafkaConsumer010<Lamp> getConsumer() {

        // configure the Kafka consumer
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("zookeeper.connect", LOCAL_ZOOKEEPER_HOST);
        kafkaProps.setProperty("bootstrap.servers", LOCAL_KAFKA_BROKER);
        kafkaProps.setProperty("group.id", "myGroup");

        // always read the Kafka topic from the start
        kafkaProps.setProperty("auto.offset.reset", "earliest");

        // create a Kafka consumer
        FlinkKafkaConsumer010<Lamp> consumer = new FlinkKafkaConsumer010<>(
                LAMP_TOPIC,          //kafka topic
                new LampSchema(),   //deserialization schema
                kafkaProps);        //consumer configuration


        // assign a timestamp extractor to the consumer
        //consumer.assignTimestampsAndWatermarks(new LampTSExtractor());

        return consumer;
    }

    public static final FlinkKafkaConsumer010<LightSensor> getConsumerSensor() {

        // configure the Kafka consumer
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("zookeeper.connect", LOCAL_ZOOKEEPER_HOST);
        kafkaProps.setProperty("bootstrap.servers", LOCAL_KAFKA_BROKER);
        kafkaProps.setProperty("group.id", "myGroup");

        // always read the Kafka topic from the start
        kafkaProps.setProperty("auto.offset.reset", "earliest");

        // create a Kafka consumer
        FlinkKafkaConsumer010<LightSensor> consumer = new FlinkKafkaConsumer010<>(
                SENSOR_TOPIC,          //kafka topic
                new LightSensorSchema(),   //deserialization schema
                kafkaProps);        //consumer configuration


        // assign a timestamp extractor to the consumer
        //consumer.assignTimestampsAndWatermarks(new LampTSExtractor());

        return consumer;
    }


     
    public static final void getProducerAdjustmentIntensity(DataStream<LightAdjustment> lightAdjustmentStream) {

        //write data to a Kafka sink
    	lightAdjustmentStream.addSink(new FlinkKafkaProducer010<>(
                LOCAL_KAFKA_BROKER,
                LAMP_TOPIC,
                new LightAdjustmentSchema()
        ));

        //print only for testing
        //lampStream.print();
    }
}
