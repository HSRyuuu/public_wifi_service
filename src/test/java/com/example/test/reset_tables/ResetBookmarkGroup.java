package com.example.test.reset_tables;

import com.example.connection.DBConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetBookmarkGroup {


    @Test
    void resetBookmarkGroupTable() throws InterruptedException {
        System.out.println("reset table BookmarkGroup");
        System.out.println("5초뒤에 reset됩니다.");
        Thread.sleep(5000);
        System.out.println("reset 시작");
        Thread.sleep(1000);

        dropTable();
        createTable();
        System.out.println("테이블 reset 완료");

    }

    private void dropTable(){
        String sql = " drop table if exists BookmarkGroup ;";
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
        String sql = "CREATE TABLE \"BookmarkGroup\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"name\"\tVARCHAR(100),\n" +
                "\t\"priority\"\tINTEGER,\n" +
                "\t\"create_date_time\"\tVARCHAR(100) DEFAULT ' ',\n" +
                "\t\"edit_date_time\"\tVARCHAR(100) DEFAULT ' ',\n" +
                "\tPRIMARY KEY(\"id\")\n" +
                ")";
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
