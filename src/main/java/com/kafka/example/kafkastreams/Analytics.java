package com.kafka.example.kafkastreams;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import com.kafka.example.kafkastreams.pojo.UssdEvent;
import com.kafka.example.kafkastreams.serdes.CustomSerdes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Analytics {

	private static Logger log = LoggerFactory.getLogger(Analytics.class.getName());

	public static void main(String[] args) throws Exception {

		// define your kaka properties
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		// Subscribe to incoming data topic
		final StreamsBuilder builder = new StreamsBuilder();
		KStream<String, UssdEvent> source = builder.stream("ussdevents",
				Consumed.with(Serdes.String(), CustomSerdes.UssdEvent()));

		// send data to another topic for consuming the data later on
		// source.flatMapValues(value -> Arrays.asList(value)).to("counts");
		source.map((key, value) -> new KeyValue<>(value.clientid, 0L))
				.groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).count(Materialized.as("active-user-counts"))
				.toStream().peek((k, v) -> System.out.println("K->" + k + " val ->" + v));

		final Topology topology = builder.build();
		final KafkaStreams streams = new KafkaStreams(topology, props);
		final CountDownLatch latch = new CountDownLatch(1);

		// attach shutdown handler to catch control-c
		Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
			@Override
			public void run() {
				streams.close();
				latch.countDown();
			}
		});

		try {
			streams.start();
			latch.await();
		} catch (Throwable e) {
			System.exit(1);
		}
		System.exit(0);
	}

}