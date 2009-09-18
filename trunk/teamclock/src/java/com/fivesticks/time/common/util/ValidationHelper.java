/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.common.util;

import java.util.StringTokenizer;

import com.opensymphony.xwork.ValidationAware;

/**
 * @author Reid
 */
public class ValidationHelper {

    public boolean isStringEmpty(String target) {
        return target == null || target.trim().length() == 0;
    }

    public void validateNonEmpty(String target, ValidationAware validatable,
            String field, String message) {

        if (this.isStringEmpty(target))
            validatable.addFieldError(field,message);
        
    }
    
    
    
    public boolean isNonZero(Long target) {
        return target != null && target.longValue() > 0;
    }
    
    public boolean isNonZero(Integer target) {
        return target != null && target.intValue() > 0;
    }    

    public boolean isNonZero(Double target) {
        return target != null && target.doubleValue() > 0.0;
    }    

    public boolean isEmailAddress(String target) {
        boolean ret = true;

        if (target == null || target.length() < 7 || target.length() > 100) {
            //has to have x@xx.xx characters
            ret = false;
        } else if (target.indexOf("@") < 0 || target.indexOf(".") < 0) {
            //has to have an @ and a .
            ret = false;
        } else if (target.length() - 1 - target.lastIndexOf(".") < 2) {
            //the last period has to be at least 2 characters in from the right
            ret = false;
        } else if (target.indexOf("@") < 2) {
            //the @ has to be at least 2 characters in from the left.
            ret = false;
        }

        if (ret) {
            if (new StringTokenizer(target, "@").countTokens() > 2) {
                ret = false;
            }

        }

        return ret;
    }

    public boolean isValidUsername(String target) {
        boolean ret = true;

        if (target == null || target.length() < 5 || target.length() > 20) {
            ret = false;
        } else if (target.indexOf(" ") > -1) {
            ret = false;
        }
        
        //2006-08-01 "all" is a reserved username;
        if (ret && target.trim().equalsIgnoreCase("all")) {
            ret = false;
        }

        return ret;
    }

    public boolean isValidPassword(String target) {
        boolean ret = true;

        if (target == null || target.length() < 5 || target.length() > 20) {
            ret = false;
        } else if (target.indexOf(" ") > -1) {
            ret = false;
        }

        return ret;
    }

}