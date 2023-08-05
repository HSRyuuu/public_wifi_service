package com.example.bookmark;

import com.example.connection.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository {

    public Bookmark findById(long id) {
        String sql = "select * from Bookmark where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Bookmark(rs.getLong("id"),
                        rs.getString("bookmark_group_name"),
                        rs.getString("wifi_manage_number"),
                        rs.getString("wifi_name"),
                        rs.getString("date_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return new Bookmark();
    }

    public void save(Bookmark bookmark) {
        String sql = " insert into Bookmark " +
                " (bookmark_group_name, wifi_manage_number, wifi_name, date_time) " +
                " values " +
                " (?, ?, ?, ?); ";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bookmark.getBookmarkGroupName());
            pstmt.setString(2, bookmark.getWifiManageNumber());
            pstmt.setString(3, bookmark.getWifiName());
            pstmt.setString(4, bookmark.getDateTime());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
    }

    public List<Bookmark> findAll() {
        List<Bookmark> list = new ArrayList<>();
        String sql = "select * from Bookmark limit 20;";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getLong("id"));
                bookmark.setBookmarkGroupName(rs.getString("bookmark_group_name"));
                bookmark.setWifiManageNumber(rs.getString("wifi_manage_number"));
                bookmark.setWifiName(rs.getString("wifi_name"));
                bookmark.setDateTime(rs.getString("date_time"));
                list.add(bookmark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return list;
    }

    public void delete(long id) {
        String sql = "delete from Bookmark where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con, pstmt, null);
        }
    }

    public void deleteByBookmarkGroupName(String name){
        String sql = "delete from Bookmark where bookmark_group_name=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con, pstmt, null);
        }
    }

    public void updateBookmarkGroupName(String before, String after){
        String sql = " update Bookmark " +
                " set bookmark_group_name=? " +
                " where bookmark_group_name=?;";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, after);
            pstmt.setString(2, before);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        DBConnectionUtil.close(conn, pstmt, rs);
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
