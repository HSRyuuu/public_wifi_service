package com.example.connection;

public abstract class ConnectionConst {
    private static final String dbFileSQLite = "C:\\dev\\SQLite_DB\\mission1db.db";
    public static final String url = "jdbc:sqlite:" + dbFileSQLite;
    public static final String dbUserId = "testuser";
    public static final String dbPassword = "0000";
}
