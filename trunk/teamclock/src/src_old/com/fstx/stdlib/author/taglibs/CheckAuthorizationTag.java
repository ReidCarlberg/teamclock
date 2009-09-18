/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Aug 21, 2003
 *
 */
package com.fstx.stdlib.author.taglibs;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.authen.webwork.SessionContextAware;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.useradmin.FstxTimeSystemRights;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.author.old.Authorizer;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * Needs to take a property and, if it checks out from an authorization point of
 * view, then process the body.
 * 
 * @author Reid
 *  
 */
public class CheckAuthorizationTag extends BodyTagSupport {

    private String rightCode;

    private FstxTimeSystemRights rightCode1;

    private FstxTimeSystemRights rightCode2;

    private FstxTimeSystemRights rightCode3;

    private FstxTimeSystemRights rightCode4;

    private String checkValue;

    /**
     * this needs to take the right code, the authenticated user, and check
     * authorization. if not authorized, we skip the body. If authorized, the
     */
    public int doStartTag() {
        boolean ok = false;

        //log.info("doStartTag begins...");

        if (userIsAuthorized())
            return BodyTagSupport.EVAL_BODY_INCLUDE;

        return BodyTagSupport.SKIP_BODY;
    }

    private boolean userIsAuthorized() {

        SessionContext sessionContext = null;

        ComponentManager container = (ComponentManager) ActionContext
                .getContext().getSession().get("DefaultComponentManager");

        sessionContext = (SessionContext) container
                .getComponent(SessionContextAware.class);

        /*
         * check authentication
         */
        if (sessionContext == null)
            return false;

        /*
         * check valid authentication
         */
        if (sessionContext.getUser() == null)
            return false;

        AuthenticatedUser au = sessionContext.getUser();

        //SystemRights right = SystemRights.getRight(this.getRightCode());

        //log.info("attempting to authorize user " + au.getUsername() + " for
        // right " + this.getRightCode());;
        /**
         * useful since we need to, sometimes, use more than a single right code
         * with the tag
         */
        FstxTimeSystemRights[] rights = { rightCode1, rightCode2, rightCode3,
                rightCode4 };

        boolean authorized = Authorizer.singleton
                .isAuthorizedForAny(au, rights);

        boolean ret = authorized;
        /*
         * we assume we're checking if someone is, but its also possible to
         * check if someone isn't.
         */
        if (this.getCheckValue() != null && this.getCheckValue().length() > 0) {
            boolean target = Boolean.getBoolean(this.getCheckValue());

            if (target && authorized) {
                ret = true;
            } else if (!target && !authorized) {
                ret = true;
            } else if (!target && authorized) {
                ret = false;
            } else if (target && !authorized) {
                ret = false;
            }

        }
        /*
         * Make sure the systemowner and the auth user jive
         */
        SystemOwner asystemowner = null;
        try {
            asystemowner = SystemOwnerServiceDelegate.factory.build().get(
                    au.getUser());
        } catch (Exception e) {
            ret = false;
        }
        if (!sessionContext.getSystemOwner().getKey().equals(
                asystemowner.getKey())) {
            ret = false;
        }

        return ret;
    }

    /**
     * all this needs to do is quit.
     */
    public int doEndTag() {

        //log.info("ending the tag...");

        return BodyTagSupport.SKIP_BODY;
    }

    /**
     * @return
     */
    public String getRightCode() {
        return rightCode;
    }

    /**
     * @param string
     */
    public void setRightCode(String string) {
        rightCode = string;
    }

    public void setRightCode(FstxTimeSystemRights right) {
        rightCode1 = right;
        this.setRightCode(right.getName());
    }

    static Log log = LogFactory.getLog(CheckAuthorizationTag.class.getName());

    /**
     * @return
     */
    public FstxTimeSystemRights getRightCode2() {
        return rightCode2;
    }

    /**
     * @return
     */
    public FstxTimeSystemRights getRightCode3() {
        return rightCode3;
    }

    /**
     * @return
     */
    public FstxTimeSystemRights getRightCode4() {
        return rightCode4;
    }

    /**
     * @param rights
     */
    public void setRightCode2(FstxTimeSystemRights rights) {
        rightCode2 = rights;
    }

    /**
     * @param rights
     */
    public void setRightCode3(FstxTimeSystemRights rights) {
        rightCode3 = rights;
    }

    /**
     * @param rights
     */
    public void setRightCode4(FstxTimeSystemRights rights) {
        rightCode4 = rights;
    }

    /**
     * @return
     */
    public String getCheckValue() {
        return checkValue;
    }

    /**
     * @param string
     */
    public void setCheckValue(String string) {
        checkValue = string;
    }

}