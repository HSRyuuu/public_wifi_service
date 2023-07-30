package com.example.bookmark_group;

import com.example.connection.DBConnectionUtil;
import com.example.dto.BookmarkGroupDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookmarkGroupRepository {

    public BookmarkGroup findById(long id){
        String sql = "select * from BookmarkGroup where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery();
            if(rs.next()){
                return new BookmarkGroup(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("priority"),
                        rs.getString("create_date_time"),
                        rs.getString("edit_date_time"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(con,pstmt,rs);
        }
        return new BookmarkGroup();
    }

    public void addBookmarkGroup(BookmarkGroupDTO bookmarkGroup){
        String sql = " insert into BookmarkGroup " +
                " (name, priority, create_date_time) " +
                " values " +
                " (?, ?, ?); ";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bookmarkGroup.getName());
            pstmt.setInt(2, bookmarkGroup.getPriority());
            pstmt.setString(3, getDateTimeNow());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
    }
    public void edit(long id, BookmarkGroupDTO bookmarkGroupDTO){
        String sql = " update BookmarkGroup " +
                " set name=?, priority=?, edit_date_time=? " +
                " where id=?;";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bookmarkGroupDTO.getName());
            pstmt.setInt(2, bookmarkGroupDTO.getPriority());
            pstmt.setString(3, getDateTimeNow());
            pstmt.setLong(4, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally{
            close(con,pstmt,null);
        }
    }

    public void delete(long id){
        String sql = "delete from BookmarkGroup where id=?";

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

    public List<BookmarkGroup> findAll(){
        List<BookmarkGroup> list = new ArrayList<>();

        String sql = "select * from BookmarkGroup " +
                " order by priority " +
                " limit 20; ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()){
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getLong("id"));
                bookmarkGroup.setName(rs.getString("name"));
                bookmarkGroup.setPriority(rs.getInt("priority"));
                bookmarkGroup.setCreateDateTime(rs.getString("create_date_time"));
                bookmarkGroup.setEditDateTime(rs.getString("edit_date_time"));
                list.add(bookmarkGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }


        return list;
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
