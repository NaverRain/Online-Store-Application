package com.naverrain.persistence.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String JDBC_LOCAL_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "naver_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0000";

    private DBUtils(){
    }



    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }

        try {
            return DriverManager.getConnection(JDBC_LOCAL_HOST + DB_NAME, USERNAME, PASSWORD);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
