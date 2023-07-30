package com.example.test.reset_tables;

import com.example.connection.DBConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetBookmark {

    @Test
    void resetBookmarkTable() throws InterruptedException {
        System.out.println("reset table Bookmark");
        System.out.println("5초뒤에 reset됩니다.");
        Thread.sleep(5000);
        System.out.println("reset 시작");
        Thread.sleep(1000);

        dropTable();
        createTable();
        System.out.println("테이블 reset 완료");
    }

    private void dropTable(){
        String sql = " drop table if exists Bookmark ;";
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
        String sql = "CREATE TABLE `Bookmark` (\n" +
                "\t`id`                  INTEGER      PRIMARY key, -- ID\n" +
                "\t`bookmark_group_name` VARCHAR(100) NULL,     -- 북마크이름\n" +
                "\t`wifi_manage_number`  VARCHAR(100) NULL,     -- 와이파이관리번호\n" +
                "\t`wifi_name`           VARCHAR(100) NULL,     -- 와이파이명\n" +
                "\t`date_time`           VARCHAR(100) NULL      -- 등록일자\n" +
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
