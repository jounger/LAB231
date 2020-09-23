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

    public static final List<String> NOT_AUTH = Arrays.asList("/home", "/login", "/logout", "/registration", "/error");
    public static final List<String> TEACHER = Arrays.asList("/take-quiz", "/make-quiz", "/manage-quiz");
    public static final List<String> STUDENT = Arrays.asList("/take-quiz");
}
