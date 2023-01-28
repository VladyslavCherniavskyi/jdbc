package com.mysql;

import java.sql.*;

public class Application {
    public static void main(String[] args) {
        String sqlSelectAllPersons = "select * from country limit 10";
        String connectionUrl = "jdbc:mysql://localhost:3306/world?useSSL=false";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "rootroot");
             Statement ps = conn.createStatement();
             ResultSet rs = ps.executeQuery(sqlSelectAllPersons)) {

            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                Integer population = (Integer) rs.getInt("Population");
                Integer indepYear = (Integer) rs.getObject("IndepYear");
                System.out.println("Code = '" + code + "', Name = '" + name + "'," + " Continent = '" + continent + "'," + " Population = " + population + ", " + " IndepYear = " + indepYear + "");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
