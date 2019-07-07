package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtil {
    public static String toPrettyJson(Object object) throws JsonProcessingException{
        return toJson(object,true, false);
    }

    public static String toJson(Object object, boolean isPrettyJson, boolean isIncludeNullValues)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(!isIncludeNullValues){
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if(isPrettyJson){
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return mapper.writeValueAsString(object);
    }

    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)mapper.readValue(json, clazz);
    }
}
