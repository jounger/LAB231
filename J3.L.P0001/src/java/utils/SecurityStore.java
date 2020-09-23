/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import common.Constant;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author nguyenvanan
 */
public class SecurityStore {

    public static void saveAuth(HttpSession session, User user) {
        session.setAttribute(Constant.AUTH_USER_ATTR, user);
    }

    public static User getAuth(HttpSession session) {
        User user = (User) session.getAttribute(Constant.AUTH_USER_ATTR);
        return user;
    }

    public static void clearAuth(HttpSession session) {
        session.invalidate();
    }
}
