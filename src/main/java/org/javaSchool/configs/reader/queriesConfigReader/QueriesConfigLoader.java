package org.javaSchool.configs.reader.queriesConfigReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class QueriesConfigLoader {
    private static Logger LOGGER = LoggerFactory.getLogger(QueriesConfigLoader.class);

    public static QueriesConfig load(String filePath){
        try {
        ObjectMapper mapper = new ObjectMapper();

        // load file from resources
        InputStream is = QueriesConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(filePath);

        if (is == null) {
            LOGGER.warn("config not found in resources");
            throw new RuntimeException("config not found in resources");
        }

        // JSON → Java object
        QueriesConfig output = mapper.readValue(is, QueriesConfig.class);
        return output;

        } catch (Exception e) {
            LOGGER.warn("Failed to load query config");
            throw new RuntimeException("Failed to load query config", e);
        }
    }
}
