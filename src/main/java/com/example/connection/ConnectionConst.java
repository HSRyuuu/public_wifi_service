package com.example.connection;

public abstract class ConnectionConst {
    public static final String dbFileSQLite = "C:\\dev\\SQLite_DB\\mission1db.db";

    //FIXME : 사용하는 DB에 따라 url만 변경
    public static final String url = "jdbc:sqlite:" + dbFileSQLite;
    //public static final String url = "jdbc:mariadb://localhost:3306/mission1db";
    public static final String dbUserId = "testuser";
    public static final String dbPassword = "0000";
}
