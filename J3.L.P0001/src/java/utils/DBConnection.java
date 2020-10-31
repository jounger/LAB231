/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author nguyenvanan
 */
public class DBConnection {

    public static Connection getConnection() {
        try {
//            return SQLServerConnection.getSQLServerConnection();
            return MySQLConnection.getSQLServerConnection();
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnect(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
