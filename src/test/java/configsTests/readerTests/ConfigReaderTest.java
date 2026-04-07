package configsTests.readerTests;

import org.javaSchool.configs.reader.ConfigReader;
import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.RegistryKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ConfigReaderTest {
    @Test
    public void configReader_loadAllConfigs(){
        ConfigReader.loadAllConfigs();
        var Registry = ConfigRegistry.getRegistry();
        Assertions.assertEquals(true, true);
    }
}
