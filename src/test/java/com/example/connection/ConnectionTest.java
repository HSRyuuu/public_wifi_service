package com.example.connection;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.connection.ConnectionConst.*;


public class ConnectionTest {
    @Test
    void driverManager() {
        Connection con = DBConnectionUtil.getConnection();
        System.out.println(con.getClass());
        Assertions.assertTrue(con != null);
    }

}
