/*
 * Created on Dec 2, 2004
 *
 * 
 */
package com.fivesticks.time.admin.xwork;

import com.fstx.stdlib.authen.LoginHistoryFilterParameters;

/**
 * @author Nick
 * 
 *  
 */
public class LoginHistoryListContext {

    private LoginHistoryFilterParameters params = new LoginHistoryFilterParameters();

    public LoginHistoryFilterParameters getParams() {
        return params;
    }

    public void setParams(LoginHistoryFilterParameters params) {
        this.params = params;
    }
}