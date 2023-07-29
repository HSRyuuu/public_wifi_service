package com.example.repository;

import com.example.connection.DBConnectionUtil;
import com.example.dto.LocationDTO;
import com.example.dto.WifiInfo;
import com.example.entity.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryRepository {
    public void saveHistory(LocationDTO loc){
        String sql = " insert into history " +
                " (lat, lnt, date_time)" +
                " values " +
                " (?, ?, ?); ";
        double lat = loc.getLat();
        double lnt = loc.getLnt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);
            pstmt.setString(3, getDateTimeNow());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }




    }
    private String getDateTimeNow(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")).toString();
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        DBConnectionUtil.close(conn, pstmt, rs);
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
