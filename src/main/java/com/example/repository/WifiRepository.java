package com.example.repository;

import com.example.connection.DBConnectionUtil;
import com.example.entity.WifiDTO;
import com.example.json_utils.WifiInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WifiRepository {

    public void loadAll(WifiInfo[] wifiInfos) {

        String sql = getInsertSQL();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
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

    public List<WifiDTO> selectTop20Wifi(double lnt, double lat){

        List<WifiDTO> wifiList = new ArrayList<>();

        String sql = "select * from wifi " +
                " order by sqrt(power(lnt-?, 2) + power(lat-?,2)) " +
                " limit 20; ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, lnt);
            pstmt.setDouble(2, lat);

            rs = pstmt.executeQuery();
            getResult20(wifiList, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }

        return wifiList;
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
    private void getResult20(List<WifiDTO> wifiList, ResultSet rs) throws SQLException {
        while(rs.next()){
            WifiDTO wifiDTO = new WifiDTO();
            wifiDTO.setManageNumber(rs.getString("manage_number"));
            wifiDTO.setDistrict(rs.getString("district"));
            wifiDTO.setName(rs.getString("name"));
            wifiDTO.setAddr1(rs.getString("addr1"));
            wifiDTO.setAddr2(rs.getString("addr2"));
            wifiDTO.setInstallFloor(rs.getString("install_floor"));
            wifiDTO.setInstallType(rs.getString("install_type"));
            wifiDTO.setInstallCorp(rs.getString("install_corp"));
            wifiDTO.setServiceType(rs.getString("service_type"));
            wifiDTO.setNetworkType(rs.getString("network_type"));
            wifiDTO.setInstallYear(rs.getString("install_year"));
            wifiDTO.setInOrOutDoor(rs.getString("in_or_out_door"));
            wifiDTO.setWifiAccessEnv(rs.getString("wifi_access_env"));
            wifiDTO.setLnt(rs.getString("lnt"));
            wifiDTO.setLat(rs.getString("lat"));
            wifiDTO.setWorkDateTime(rs.getString("work_date_time"));
            wifiList.add(wifiDTO);
        }
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
