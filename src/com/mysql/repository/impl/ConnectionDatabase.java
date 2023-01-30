package com.mysql.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/world?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";

    public ConnectionDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}

