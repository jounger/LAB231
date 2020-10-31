/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author nguyenvanan
 */
public class MySQLConnection {

    public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException, NamingException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String uri= "jdbc:mysql://127.0.0.1:3306/P0001?useUnicode=true&useSSL=false&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
        String username = "root";
        String password = "12345678";
        Connection conn = DriverManager.getConnection(uri, username, password);
        return conn;
    }

}
