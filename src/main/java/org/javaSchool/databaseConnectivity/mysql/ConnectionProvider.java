package org.javaSchool.databaseConnectivity.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    static Connection connection;

    public static Connection createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/student_manage";

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ignore) {

        }
        return connection;
    }
}
