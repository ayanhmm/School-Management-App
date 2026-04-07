package org.javaSchool.configs.reader;

import jakarta.annotation.PostConstruct;
import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfig;
import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfigLoader;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConfigReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);

    private static final Set<String> QUERIES_CONFIGS_PATHS = Set.of(
            "configs/studentDaoConfigs/studentDao_queries_config.json"
    );

    private static final Set<String> MISCELLANEOUS_CONFIGS_PATHS = Set.of(
    );

    private static void loadAllConfigs(){
        for(String filePath : QUERIES_CONFIGS_PATHS) loadQueryConfigs(filePath);
        for(String filePath : MISCELLANEOUS_CONFIGS_PATHS) loadQueryConfigs(filePath);
    }

    private static void loadQueryConfigs(String filePath){
        QueriesConfig queriesConfig = QueriesConfigLoader.load(filePath);
        String type = queriesConfig.getType();
        String subtype = queriesConfig.getSubtype();
        var ParamDatatype = queriesConfig.getParams();
        var queryMap = queriesConfig.getQueries();
        for(var query : queryMap.entrySet()){
            String queryName = query.getKey();
            QueryDefinition value = query.getValue();
            value.setParamDatatype(ParamDatatype);
            ConfigRegistry.addConfigToRegistry(type, subtype, queryName, value);
        }
    }

    @PostConstruct
    public static void init() {
        LOGGER.info("Loading Query Config...");
        loadAllConfigs();
        LOGGER.info("Query Registry Loaded of size : {} ", ConfigRegistry.getRegistry().size());
    }

}
