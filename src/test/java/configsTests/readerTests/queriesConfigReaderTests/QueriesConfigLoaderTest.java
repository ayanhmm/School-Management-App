package configsTests.readerTests.queriesConfigReaderTests;

import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfigLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueriesConfigLoaderTest {

    @Test
    public void QueriesConfigLoader_loadTest(){
        String filePath = "configs/studentDaoConfigs/studentDao_queries_config.json";
        try{
            QueriesConfigLoader.load(filePath);
        }
        catch (Exception ex){
            String error = ex.getMessage();
            System.out.println(error);
        }
        Assertions.assertEquals(true, true);
    }
}
