/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author nguyenvanan
 */
public class DBConnection {

    public static Connection getConnection(){
        try {
            return SQLServerConnection.getSQLServerConnection();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static void closeConnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException ex) {
        }
    }
}
