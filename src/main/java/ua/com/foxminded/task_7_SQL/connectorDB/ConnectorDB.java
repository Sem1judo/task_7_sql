package ua.com.foxminded.task_7_SQL.connectorDB;

import ua.com.foxminded.task_7_SQL.exeptions.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectorDB {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql:school";
    static final String USER = "postgres";
    static final String PASS = "root";

    public static Connection getConnect() {
        Connection con;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {
            throw new DBConnectionException("Problem with connection to Database",e);
        }
        return con;
    }
}

