/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.List;

/**
 *
 * @author nguyenvanan
 */
public class Tool {

    public static boolean includes(List<String> list, String s) {
        for (String el : list) {
            if (el.contains(s) || s.contains(el)) {
                return true;
            }
        }
        return false;
    }
}
