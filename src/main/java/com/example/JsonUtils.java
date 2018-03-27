package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static String asJson(String filename) throws Exception {
        URL url = JsonUtils.class.getResource("/" + filename);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

    public static String toJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error creating JSON string from object: " + e);
        }
    }
}
