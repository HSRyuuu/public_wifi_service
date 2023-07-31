package com.example.wifi;

import com.example.connection.DBConnectionUtil;
import com.example.dto.LocationDTO;
import com.example.dto.WifiDTO;
import com.example.dto.WifiApiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class WifiRepository {

    /**
     * api로부터 얻어온 데이터를 db에 모두 저장
     */
    public void loadAll(List<WifiApiDTO[]> list) throws SQLException {

        String sql = getInsertQueryForLoadAll();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            conn.setAutoCommit(false);
            for(WifiApiDTO[] wifiApiDTOS : list){
                for (WifiApiDTO wi : wifiApiDTOS) {
                    setPstmtForInsert(wi, pstmt);
                }
                pstmt.executeBatch();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            close(conn, pstmt, null);
        }
    }

    /**
     * loadAll 메서드에서 사용할 String sql 반환
     */
    private String getInsertQueryForLoadAll() {
        return " insert into wifi" +
                " (manage_number, district, name, addr1, addr2, install_floor, install_type, install_corp, service_type, network_type, install_year, in_or_out_door, wifi_access_env ,lat ,lnt ,work_date_time )" +
                " values" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * wifiApiDTO를 받아서 pstmt.addBatch()로 배치 처리
     * @param wifiApiDTO
     */
    private void setPstmtForInsert(WifiApiDTO wifiApiDTO, PreparedStatement pstmt) throws SQLException {
        double lnt = Double.parseDouble(wifiApiDTO.getLNT());//x좌표
        double lat = Double.parseDouble(wifiApiDTO.getLAT());//y좌표
        pstmt.setString(1, wifiApiDTO.getX_SWIFI_MGR_NO());
        pstmt.setString(2, wifiApiDTO.getX_SWIFI_WRDOFC());
        pstmt.setString(3, wifiApiDTO.getX_SWIFI_MAIN_NM());
        pstmt.setString(4, wifiApiDTO.getX_SWIFI_ADRES1());
        pstmt.setString(5, wifiApiDTO.getX_SWIFI_ADRES2());
        pstmt.setString(6, wifiApiDTO.getX_SWIFI_INSTL_FLOOR());
        pstmt.setString(7, wifiApiDTO.getX_SWIFI_INSTL_TY());
        pstmt.setString(8, wifiApiDTO.getX_SWIFI_INSTL_MBY());
        pstmt.setString(9, wifiApiDTO.getX_SWIFI_SVC_SE());
        pstmt.setString(10, wifiApiDTO.getX_SWIFI_CMCWR());
        pstmt.setString(11, wifiApiDTO.getX_SWIFI_CNSTC_YEAR());
        pstmt.setString(12, wifiApiDTO.getX_SWIFI_INOUT_DOOR());
        pstmt.setString(13, wifiApiDTO.getX_SWIFI_REMARS3());
        pstmt.setDouble(14, lnt);
        pstmt.setDouble(15, lat);
        pstmt.setString(16, wifiApiDTO.getWORK_DTTM());

        pstmt.addBatch();
    }

    /**
     * @param loc : 이용자 현재 위치
     */
    public List<WifiDTO> selectTop30Wifi(LocationDTO loc) {
        double lat = loc.getLat();
        double lnt = loc.getLnt();

        List<WifiDTO> wifiList = new ArrayList<>();

        //SQLite에서는 삼각함수를 쓸 수 없어서 피타고라스 공식을 이용해 근사치를 기준을 30개를 불러온 뒤,
        // 이후에 WifiService에서 데이터 재가공 / 신뢰성을 높이려면 limit 개수를 더 크게하면 됨.
        String sql = "select * from wifi " +
                " order by sqrt( power(lat-?,2) + power(lnt-?, 2)) " +
                " limit 30; ";

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

    /**
     * result set으로부터 WifiDTO를 만들어서 wifiList에 저장
     */
    private void getResult20(List<WifiDTO> wifiList, ResultSet rs) throws SQLException {
        while (rs.next()) {
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

    public WifiDTO findByManageNumber(String key) {
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

    /**
     * result set으로부터 WifiDTO에 값을 저장
     */
    private WifiDTO getOneWifi(ResultSet rs) throws SQLException {
        WifiDTO wifiDTO = new WifiDTO();
        if (rs.next()) {
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
        } else {
            throw new NoSuchElementException("WIFI not found");
        }
        return wifiDTO;
    }

    /**
     * Open API로부터 와이파이 정보를 가져올 때, 테이블의 모든 내용을 지우고 새로 가져옴.
     */
    public void deleteAll() {
        String sql = "delete from wifi";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
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
