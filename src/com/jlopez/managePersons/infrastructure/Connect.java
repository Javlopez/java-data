package com.jlopez.managePersons.infrastructure;

import java.sql.*;

public class Connect {

    private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/articles?useSSL=false&serverTimezone=UTC";
    private final static String JDBC_USER = "root";
    private final static String JDBC_PASS = "rootroot";
    private static Driver driver = null;

    public static synchronized Connection getConnection() throws SQLException {

        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch(Exception e) {
                System.out.println("The JDBC connection has a trouble, please check the logs");
                e.printStackTrace();
            }
        }

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet result) {
        try {
            if(result != null) {
                result.close();
            }

        } catch ( SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt){
        try {
            if(stmt != null) {
                stmt.close();
            }

        } catch ( SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void close(Connection conn){
        try {
            if(conn != null) {
                conn.close();
            }

        } catch ( SQLException sqle){
            sqle.printStackTrace();
        }
    }


}
