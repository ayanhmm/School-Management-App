package org.javaSchool.databaseConnectivity.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.databaseConnectivityConfigsReader.ConnectionDefinition;
import org.javaSchool.models.DAO.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDao.class);
    static Connection connection;

    public static Connection createConnection() throws Exception{
        try{
            ConnectionDefinition connectionDefinition = (ConnectionDefinition) ConfigRegistry.getConfigFromRegistry("Connection_Properties_Config", "mysql", "Mysql_Connection_A");
            String classname = connectionDefinition.getClassname();
            Map<String, String> credentials = connectionDefinition.getCredentials();

            Class.forName(classname);

            String user = credentials.get("username");
            String password = credentials.get("password");
            String url = credentials.get("url");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            LOGGER.warn("Unable to establish connection to MySQL Database: {}", ex.getMessage());
            throw ex;
        }
        return connection;
    }
}
