package com.example.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.connection.ConnectionConst.*;

public class DBConnectionUtil {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
