/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.common;

import java.util.Date;

/**
 * @author REID
 *  
 */
public class ParamValidator {

    public static boolean isSearchable(Long target) {
        boolean ret = false;

        ret = target != null && target.longValue() > 0;

        return ret;
    }

    public static boolean isSearchable(Integer target) {
        boolean ret = false;

        ret = target != null && target.longValue() > 0;

        return ret;
    }
    
    public static boolean isSearchable(String target) {
        boolean ret = false;

        ret = target != null && target.trim().length() > 0;

        return ret;
    }

    public static boolean isSearchable(Boolean target) {
        boolean ret = false;

        ret = target != null;

        return ret;
    }

    public static boolean isSearchable(Date target) {
        boolean ret = false;

        ret = target != null;

        return ret;
    }

}