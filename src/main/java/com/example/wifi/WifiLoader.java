package com.example.wifi;

import com.example.connection.DBConnectionUtil;
import com.example.dto.WifiApiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WifiLoader {
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
            for (WifiApiDTO[] wifiApiDTOS : list) {
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
     *
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

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        DBConnectionUtil.close(conn, pstmt, rs);
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
