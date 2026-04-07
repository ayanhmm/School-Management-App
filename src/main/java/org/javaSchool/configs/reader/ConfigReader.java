package org.javaSchool.configs.reader;

import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfig;
import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfigLoader;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import  org.javaSchool.configs.reader.ConfigRegistry;

import java.util.HashSet;
import java.util.Set;

public class ConfigReader {
    private static final Set<String> QUERIES_CONFIGS_PATHS = Set.of(
            "configs/studentDaoConfigs/studentDao_queries_config.json"
    );

    private static final Set<String> MISCELLANEOUS_CONFIGS_PATHS = Set.of(
    );

    public static void loadAllConfigs(){
        for(String path : QUERIES_CONFIGS_PATHS){
            QueriesConfig queriesConfig = QueriesConfigLoader.load(path);
            String type = queriesConfig.getType();
            String subtype = queriesConfig.getSubtype();
            var queryMap = queriesConfig.getQueries();
            for(var query : queryMap.entrySet()){
                String queryName = query.getKey();
                QueryDefinition value = query.getValue();
                ConfigRegistry.addConfigToRegistry(type, subtype, queryName, value);
            }
        }
    }

}
