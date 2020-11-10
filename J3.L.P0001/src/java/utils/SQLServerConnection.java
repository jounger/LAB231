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

    // context.xml only avaiable on tomcat
    public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException, NamingException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Only use for tomcat server
//        String uri = Env.getVariable("datasource.url");
//        String username = Env.getVariable("datasource.username");
//        String password = Env.getVariable("datasource.password");
        // User for glassfish
        String uri = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=P0001";
        String username = "sa";
        String password = "Nguyenvanan98";
        Connection conn = DriverManager.getConnection(uri, username, password);
        return conn;
    }

}
