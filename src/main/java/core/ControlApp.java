package core;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import model.Lamp;
import configuration.Configuration;
import model.LightAdjustment;
import model.LightSensor;
import operator.filter.AdjustmentFilter;
import operator.filter.LampFilter;
import operator.filter.LightSensorFilter;
import operator.join.ComputeIntensity;
import operator.key.LampKey;
import operator.key.LightAdjustmentKey;
import operator.key.LightSensorKey;
import operator.time.LampTSExtractor;
import operator.time.LightSensorTSExtractor;
import operator.window.LampWindowFunction;
import operator.window.LightSensorWindowFunction;
import operator.window.SumIntensityFoldFunction;
import operator.window.SumLampIntensityFoldFunction;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

import utils.connector.KafkaConfigurator;
import utils.traffic.ThreadCallTraffic;
import control.EnvConfigurator;

/*olga*/

public class ControlApp {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public static void main(String[] args) throws Exception {
		Configuration config = new Configuration();
		final StreamExecutionEnvironment env = EnvConfigurator.setupExecutionEnvironment();
		
		/*List<Lamp> data = new ArrayList<>();
		List<LightSensor> data2 = new ArrayList<>();

		for(long i=1; i<=5; i++){
			data.add(new Lamp(1,i*0.1,"via palmiro togliatti",i*1100));
			data.add(new Lamp(2,i*0.1,"via palmiro togliatti",i*1200));
			data.add(new Lamp(3,i*0.1,"via tuscolana",i*1300));
			data.add(new Lamp(4,i*0.1, "via tuscolana",i*1400));
			
			data2.add(new LightSensor(1,0.3, i*1100, "via palmiro togliatti"));
			data2.add(new LightSensor(2,0.6, i*1200, "via palmiro togliatti"));
			data2.add(new LightSensor(3,0.8, i*1300, "via tuscolana"));
			data2.add(new LightSensor(4,0.1, i*1400, "via tuscolana"));

			
		}
		DataStream<Lamp> lampStream = env.fromCollection(data).assignTimestampsAndWatermarks(new LampTSExtractor());
		DataStream<LightSensor> sensorStream = env.fromCollection(data2).assignTimestampsAndWatermarks(new LightSensorTSExtractor());*/

	    ThreadCallTraffic tl = new ThreadCallTraffic();
		tl.start();
		
		// set up the streaming execution environment
		
		FlinkKafkaConsumer010<Lamp> kafkaConsumer = KafkaConfigurator.getConsumer();
		FlinkKafkaConsumer010<LightSensor> kafkaConsumerSensor = KafkaConfigurator.getConsumerSensor();

		// assign a timestamp extractor to the consumer
		FlinkKafkaConsumerBase<Lamp> kafkaConsumerTS = kafkaConsumer.assignTimestampsAndWatermarks(new LampTSExtractor());
		
		// assign a timestamp extractor to the consumer
		FlinkKafkaConsumerBase<LightSensor> kafkaConsumerLS = kafkaConsumerSensor.assignTimestampsAndWatermarks(new LightSensorTSExtractor());

		DataStream<Lamp> lampStream = env.addSource(kafkaConsumerTS);
		DataStream<LightSensor> sensorStream = env.addSource(kafkaConsumerLS);

		DataStream<Lamp> filteredLampById = lampStream.filter(new LampFilter());
		DataStream<LightSensor> filteredSensorById = sensorStream.filter(new LightSensorFilter());
	
        // compute avg light sensor
		WindowedStream groupSensorById = filteredSensorById.keyBy(new LightSensorKey()).timeWindow(Time.seconds(config.TIME_WINDOW_SENSOR_LIGHT_SEC));
		DataStream<LightSensor> AvgSensorLight = groupSensorById.fold(new Tuple2<>(null, (long) 0), new SumIntensityFoldFunction(), new LightSensorWindowFunction()).setParallelism(config.FOLD_PARALLELISM);
		
		WindowedStream groupLampId = filteredLampById.keyBy(new LampKey()).timeWindow(Time.seconds(config.TIME_WINDOW_LAMP_SEC));
		DataStream<Lamp> AvgLamp = groupLampId.fold(new Tuple2<>(null, (long) 0), new SumLampIntensityFoldFunction(), new LampWindowFunction()).setParallelism(config.FOLD_PARALLELISM);
		
        // compute adjustment
		DataStream<LightAdjustment> lightAdjustmentStream= AvgLamp.join(AvgSensorLight).where(new LampKey()).equalTo(new LightSensorKey()).window(TumblingEventTimeWindows.of(Time.seconds(config.JOIN_TIME_SEC)))
				.apply(new ComputeIntensity());
		
		DataStream<LightAdjustment> filterAdjustmentStream = lightAdjustmentStream.keyBy(new LightAdjustmentKey()).filter(new AdjustmentFilter()).setParallelism(config.FILTER_PARALLELISM);
				
		// publish result on Kafka topic
		
		KafkaConfigurator.getProducerAdjustmentIntensity(filterAdjustmentStream);
		env.execute("Control System");
	}
}