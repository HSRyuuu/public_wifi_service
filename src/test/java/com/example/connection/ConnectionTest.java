package com.example.connection;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.connection.ConnectionConst.*;


public class ConnectionTest {
    @Test
    void driverManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con1 = DriverManager.getConnection(url, dbUserId, dbPassword);
        Assertions.assertTrue(con1 != null);

    }
}
