package com.example.history;

import com.example.connection.DBConnectionUtil;
import com.example.dto.LocationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public List<History> selectLatest20History(){
        String sql = "select * from history " +
                " order by id desc " +
                " limit 20; ";
        List<History> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()){
                History history = new History();
                history.setId(rs.getLong("id"));
                history.setLat(rs.getDouble("lat"));
                history.setLnt(rs.getDouble("lnt"));
                history.setDateTime(rs.getString("date_time"));
                list.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return list;
    }

    public void deleteById(long id){
        String sql = "delete from history where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally{
            close(con,pstmt,null);
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
