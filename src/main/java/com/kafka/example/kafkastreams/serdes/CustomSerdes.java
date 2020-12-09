package com.kafka.example.kafkastreams.serdes;

import com.kafka.example.kafkastreams.pojo.UssdEvent;
import com.kafka.example.kafkastreams.serializer.JsonDeserializer;
import com.kafka.example.kafkastreams.serializer.JsonSerializer;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    static public final class UssdEventSerde extends Serdes.WrapperSerde<UssdEvent> {
        public UssdEventSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(UssdEvent.class));
        }
    }

    public static Serde<UssdEvent> UssdEvent() {
        return new CustomSerdes.UssdEventSerde();
    }

}