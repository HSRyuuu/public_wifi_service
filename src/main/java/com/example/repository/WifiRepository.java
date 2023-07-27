package com.example.repository;

import com.example.connection.DBConnectionUtil;
import com.example.json_utils.WifiInfo;

import java.sql.*;


public class WifiRepository {

    public void loadAll(WifiInfo[] wifiInfos) {

        String sql = getInsertSQL();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setPstmt();
            for(WifiInfo wi : wifiInfos){
                setPstmtForInsert(wi, pstmt); //pstmt 세팅
            }
            pstmt.executeBatch();//쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
    }

    public void deleteAll(){
        String sql = "delete from wifi";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(con,pstmt,null);
        }
    }

    private void setPstmt(){

    }

    private void setPstmtForInsert(WifiInfo wifiInfo, PreparedStatement pstmt) throws SQLException {
        double lnt = Double.parseDouble(wifiInfo.getLNT());//x좌표
        double lat = Double.parseDouble(wifiInfo.getLAT());//y좌표
        pstmt.setString(1, wifiInfo.getX_SWIFI_MGR_NO());
        pstmt.setString(2, wifiInfo.getX_SWIFI_WRDOFC());
        pstmt.setString(3, wifiInfo.getX_SWIFI_MAIN_NM());
        pstmt.setString(4, wifiInfo.getX_SWIFI_ADRES1());
        pstmt.setString(5, wifiInfo.getX_SWIFI_ADRES2());
        pstmt.setString(6, wifiInfo.getX_SWIFI_INSTL_FLOOR());
        pstmt.setString(7, wifiInfo.getX_SWIFI_INSTL_TY());
        pstmt.setString(8, wifiInfo.getX_SWIFI_INSTL_MBY());
        pstmt.setString(9, wifiInfo.getX_SWIFI_SVC_SE());
        pstmt.setString(10, wifiInfo.getX_SWIFI_CMCWR());
        pstmt.setString(11, wifiInfo.getX_SWIFI_CNSTC_YEAR());
        pstmt.setString(12, wifiInfo.getX_SWIFI_INOUT_DOOR());
        pstmt.setString(13, wifiInfo.getX_SWIFI_REMARS3());
        pstmt.setDouble(14, lnt);
        pstmt.setDouble(15, lat);
        pstmt.setString(16, wifiInfo.getWORK_DTTM());

        pstmt.addBatch();
    }
    private String getInsertSQL(){
        return " insert into wifi" +
                " (manage_number, district, name, addr1, addr2, install_floor, install_type, install_corp, service_type, network_type, install_year, in_or_out_door, wifi_access_env ,lnt ,lat ,work_date_time )" +
                " values" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
