package com.example.wifi;

import com.example.connection.DBConnectionUtil;
import com.example.dto.LocationDTO;
import com.example.dto.WifiDTO;
import com.example.dto.WifiInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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

    public List<WifiDTO> selectTop20Wifi(LocationDTO loc){
        double lat = loc.getLat();
        double lnt = loc.getLnt();

        List<WifiDTO> wifiList = new ArrayList<>();

        String sql = "select * from wifi " +
                " order by sqrt( power(lat-?,2) + power(lnt-?, 2)) " +
                " limit 20; ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);

            rs = pstmt.executeQuery();
            getResult20(wifiList, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return wifiList;
    }
    public WifiDTO findByManageNumber(String key){
        String sql = "select * from wifi " +
                " where manage_number=? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, key);

            rs = pstmt.executeQuery();

            return getOneWifi(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return new WifiDTO();
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
            wifiDTO.setLat(rs.getString("lat"));
            wifiDTO.setLnt(rs.getString("lnt"));
            wifiDTO.setWorkDateTime(rs.getString("work_date_time"));
            wifiList.add(wifiDTO);
        }
    }
    private WifiDTO getOneWifi(ResultSet rs) throws SQLException {
        WifiDTO wifiDTO = new WifiDTO();
        if(rs.next()){
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
        }else{
            throw new NoSuchElementException("WIFI not found");
        }
        return wifiDTO;
    }
    private String getInsertSQL(){
        return " insert into wifi" +
                " (manage_number, district, name, addr1, addr2, install_floor, install_type, install_corp, service_type, network_type, install_year, in_or_out_door, wifi_access_env ,lat ,lnt ,work_date_time )" +
                " values" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
       DBConnectionUtil.close(conn, pstmt, rs);
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
