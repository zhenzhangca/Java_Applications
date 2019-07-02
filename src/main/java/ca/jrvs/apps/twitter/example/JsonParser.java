package ca.jrvs.apps.twitter.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    /**
     * Convert a java object to JSON String
     * @param object input object
     * @return JSON String
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws
            JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();


        return null;

    }
    /**
     * Parse JSON String to a java object
     * @param json JSON Str
     * @param clazz object class
     * @param <T> Type
     * @return object
     * @throws java.io.IOException
     *
     */
    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException{
        return null;
    }
}
