/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author An
 */
public class Dao {

    private static String url = "jdbc:mysql://localhost:3306/ontap?useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);
    }

    private Dao() {
    }
}
