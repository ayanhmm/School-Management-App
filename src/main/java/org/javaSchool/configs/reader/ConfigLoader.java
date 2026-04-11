package org.javaSchool.configs.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class ConfigLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Config load(String filePath){
        try {
            // load file from resources
            InputStream is = ConfigLoader.class
                    .getClassLoader()
                    .getResourceAsStream(filePath);

            if (is == null) {
                throw new RuntimeException("config not found in resources");
            }

            // JSON → Java object
            Config output = MAPPER.readValue(is, Config.class);
            return output;

        } catch (Exception e) {
            LOGGER.warn("Failed to load config {}", filePath, e);
            throw new RuntimeException("Failed to load config ", e);
        }
    }
}