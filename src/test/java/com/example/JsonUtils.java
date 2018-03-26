package com.example;

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
}
