package com.kafka.example.kafkastreams.helper;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ConvertObject {

    // Convert a string to an object
    public static <T> T stringToObject(Object object, Class className) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
            return (T) mapper.readValue(object.toString(), className);
        } catch (JsonProcessingException e) {
            // e.printStackTrace();
            return null;
        }
    }

    // Convert JSON String to JSON object
    private static JSONObject convertToJson(String value) {
        Object obj = JSONValue.parse(value);
        JSONObject data = (JSONObject) obj;
        return data;
    }
}
