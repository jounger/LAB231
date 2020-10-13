/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nguyenvanan
 */
public class Router {

    public static final List<String> NOT_AUTH = Arrays.asList("/static/", "/home", "/login", "/logout", "/registration", "/error");
    public static final List<String> AUTH = Arrays.asList("/booking", "/manage-booking", "/search-booking", "/search-result");
}
