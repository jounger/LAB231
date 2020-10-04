/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    public static boolean isNull(String s) {
        if (s == null || s.length() == 0 || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNull(String... arr) {
        if (arr == null) {
            return true;
        }
        for (String s : arr) {
            if (s == null || s.length() == 0 || s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static int toInteger(String s, int defaultValue) {
        if (isNull(s)) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }

    public static LocalDateTime convertToLocalDatetimeViaInstant(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDatetimeViaTimestamp(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
