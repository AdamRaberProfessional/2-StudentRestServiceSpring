package com.example.springrestservice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceUtils {
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:./src/main/resources/studentDb.sqlite");
    }
}
