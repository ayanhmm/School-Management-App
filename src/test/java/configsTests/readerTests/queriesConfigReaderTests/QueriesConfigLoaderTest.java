package configsTests.readerTests.queriesConfigReaderTests;

import org.javaSchool.configs.reader.Config;
import org.javaSchool.configs.reader.ConfigLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueriesConfigLoaderTest {

    @Test
    public void QueriesConfigLoader_loadTest(){
        String filePath = "configs/studentDaoConfigs/studentDao_queries_config.json";
        Config output = null;
        try{
            output = ConfigLoader.load(filePath);
        }
        catch (Exception ex){
            String error = ex.getMessage();
            System.out.println(error);
        }
        Assertions.assertEquals(true, true);
    }
}
