package com.example.connection;




import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


public class ConnectionTest {
    @Test
    void driverManager() throws SQLException {
        Connection con = DBConnectionUtil.getConnection();
        System.out.println(con.getClass());

        assertThat(con).isNotNull();

        con.close();
    }

}
