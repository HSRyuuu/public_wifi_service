package com.example.test.reset_tables;

import com.example.connection.DBConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetHistory {


    @Test
    void resetHistoryTable() throws InterruptedException {
        System.out.println("reset table History");
        System.out.println("5초뒤에 reset됩니다.");
        Thread.sleep(5000);
        System.out.println("reset 시작");
        Thread.sleep(1000);

        dropTable();
        createTable();
        System.out.println("테이블 reset 완료");
    }

    private void dropTable(){
        String sql = " drop table if exists history ;";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally{
            close(con,pstmt,null);
        }
    }
    private void createTable(){
        String sql = "CREATE TABLE `History` ( " +
                " `id`               INTEGER       PRIMARY KEY, " +
                " `lat`              DECIMAL(10,6) NULL, " +
                " `lnt`              DECIMAL(10,6) NULL, " +
                " `search_date_time` VARCHAR(100)  NULL " +
                ");";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally{
            close(con,pstmt,null);
        }
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        DBConnectionUtil.close(conn, pstmt, rs);
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
