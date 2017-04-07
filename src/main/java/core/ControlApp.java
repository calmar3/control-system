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

import java.util.ArrayList;
import java.util.List;

import model.HashMapStreetTraffic;
import model.Lamp;
import model.LightAdjustment;
import model.LightSensor;
import operator.join.ComputeIntensity;
import operator.key.LampKey;
import operator.key.LightSensorKey;
import operator.time.LampTSExtractor;
import operator.time.LightSensorTSExtractor;
import operator.window.AvgIntensityLight;
import operator.window.LightSensorWindowFunction;
import operator.window.SumIntensityFoldFunction;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;

import utils.connector.KafkaConfigurator;
import utils.traffic.ThreadCallTraffic;
import control.EnvConfigurator;


/*valerio*/

public class ControlApp {
	
	
	public static void main(String[] args) throws Exception {

		ThreadCallTraffic tl = new ThreadCallTraffic();
		tl.start();

		// set up the streaming execution environment
		final StreamExecutionEnvironment env = EnvConfigurator.setupExecutionEnvironment();
		
		FlinkKafkaConsumer010<Lamp> kafkaConsumer = KafkaConfigurator.getConsumer();

		// assign a timestamp extractor to the consumer
		FlinkKafkaConsumerBase<Lamp> kafkaConsumerTS = kafkaConsumer.assignTimestampsAndWatermarks(new LampTSExtractor());
		
		FlinkKafkaConsumer010<LightSensor> kafkaConsumerSensor = KafkaConfigurator.getConsumerSensor();

		// assign a timestamp extractor to the consumer
		FlinkKafkaConsumerBase<LightSensor> kafkaConsumerLS = kafkaConsumerSensor.assignTimestampsAndWatermarks(new LightSensorTSExtractor());


		DataStream<Lamp> lampStream = env.addSource(kafkaConsumerTS);
		DataStream<LightSensor> sensorStream = env.addSource(kafkaConsumerLS);

		// compute partial rank 
		
		WindowedStream filteredSensorById = sensorStream.keyBy(new LightSensorKey()).timeWindow(Time.seconds(10));

		DataStream<LightSensor> AvgSensorLight = filteredSensorById.fold(new Tuple2<>(null, (long) 0), new SumIntensityFoldFunction(), new LightSensorWindowFunction());
		
		DataStream<LightAdjustment> lightAdjustmentStream= lampStream.join(AvgSensorLight)
	    .where(new LampKey()).equalTo(new LightSensorKey()).window(TumblingEventTimeWindows.of(Time.seconds(11)))
	    .apply(new ComputeIntensity());
				
		// publish result on Kafka topic
		
		KafkaConfigurator.getProducerAdjustmentIntensity(lightAdjustmentStream);
		
		env.execute("Control System");
	}
}