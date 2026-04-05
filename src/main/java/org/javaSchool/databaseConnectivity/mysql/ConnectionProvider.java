package org.javaSchool.databaseConnectivity.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.javaSchool.studentManagement.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDao.class);
    static Connection connection;

    public static Connection createConnection() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/student_manage";

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            LOGGER.warn("Unable to establish connection to MySQL Database: {}", ex.getMessage());
            throw ex;
        }
        return connection;
    }
}
