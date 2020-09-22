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
public class SQLServerConnection {

    public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException, NamingException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(Env.getVariable("datasource.url"), Env.getVariable("datasource.username"), Env.getVariable("datasource.password"));
        return conn;
    }

}
