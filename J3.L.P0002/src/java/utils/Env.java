/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author nguyenvanan
 */
public class Env {
    
    public static String getVariable(String name) throws NamingException {
        InitialContext init = new InitialContext();
        Context ctx = (Context) init.lookup("java:/comp/env");
        return (String) ctx.lookup(name);
    }
}
