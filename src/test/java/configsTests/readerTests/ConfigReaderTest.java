package configsTests.readerTests;

import org.javaSchool.configs.reader.ConfigLoader;
import org.javaSchool.configs.reader.ConfigReader;
import org.javaSchool.configs.reader.ConfigRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfigReaderTest {
    @Test
    public void configReader_loadAllConfigs(){
        ConfigReader.init();
        var Registry = ConfigRegistry.getRegistry();
        Assertions.assertNotNull(Registry);
    }

    @Test
    public void configLoader_test(){
        String filePath = "configs/studentDaoConfigs/studentDao_queries_config.json";
        var config = ConfigLoader.load(filePath);
        Assertions.assertNotNull(config);
    }
}
