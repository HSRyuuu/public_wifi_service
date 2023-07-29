package com.example.connection;


import java.sql.*;

import static com.example.connection.ConnectionConst.*;

public class DBConnectionUtil {
    public static Connection getConnection(){
        try {
            //FIXME : 사용하는 DB에 따라 드라이버 선택
            Class.forName("org.sqlite.JDBC");
            //Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //FIXME : SQLlite 사용 시 url만 필요 / MariaDB 사용시 url, id, pw 필요
            //Connection connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
