/*
 * Created on Sep 10, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class LoginResetAction extends ActionSupport {

    public String execute() throws Exception {

        ActionContext context = ActionContext.getContext();

        HttpServletRequest request = (HttpServletRequest) context
                .get(WebWorkStatics.HTTP_REQUEST);

        request.getSession().invalidate();

        return SUCCESS;
    }
}