package org.javaSchool.configs.reader;

import jakarta.annotation.PostConstruct;
import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfig;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Set;

@Component
public class ConfigReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);

    private static final Set<String> CONFIGS_FOLDER_PATHS = Set.of(
            "configs/studentDaoConfigs",
            "configs/databaseConnectivityConfigs"
    );

    private static void loadConfigs(){
        for(String folderPath : CONFIGS_FOLDER_PATHS){
            loadConfigs(folderPath);
        }
    }
    private static void loadConfigs(String folderPath){
        try {
            // Get the URL of the folder from the resources
            URL url = ConfigReader.class.getClassLoader().getResource(folderPath);
            if (url == null) return;

            File folder = new File(url.toURI());
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

            if (files != null) {
                for (File file : files) {
                    // Pass the relative path back to your loader
                    loadConfig(folderPath + "/" + file.getName());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to read folder: " + folderPath);
        }
    }
    private static void loadConfig(String filePath){
        Config config = ConfigLoader.load(filePath);

        String type = config.getType();
        String subtype = config.getSubtype();
        var componentMap = config.getComponents();

        for(var component : componentMap.entrySet()){
            String name = component.getKey();
            Object value = component.getValue();
            value = sanitizeLoadValue(value, config);
            ConfigRegistry.addConfigToRegistry(type, subtype, name, value);
        }
    }

    private static Object sanitizeLoadValue(Object value, Config config){
        if(value instanceof QueryDefinition queryDefinition){
            var ParamDatatype = ((QueriesConfig)config).getParamDatatype();
            queryDefinition.setParamDatatype(ParamDatatype);
        }
        return value;
    }

    @PostConstruct
    public static void init() {
        LOGGER.info("Loading Query Config...");
        loadConfigs();
        LOGGER.info("Query Registry Loaded of size : {} ", ConfigRegistry.getRegistry().size());
    }

}
