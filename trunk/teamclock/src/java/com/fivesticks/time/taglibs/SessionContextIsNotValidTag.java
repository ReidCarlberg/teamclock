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
package com.fivesticks.time.taglibs;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * 
 * 
 * @author Reid
 *  
 */
public class SessionContextIsNotValidTag extends TagSupport {

    /**
     * this needs to take the right code, the authenticated user, and check
     * authorization. if not authorized, we skip the body. If authorized, the
     */
    public int doStartTag() {

        //        if (ActionContext.getContext() == null)
        //            return TagSupport.SKIP_BODY;
        //
        //        if (ActionContext.getContext().getSession() == null)
        //            return TagSupport.SKIP_BODY;

        SessionContext sessionContext = null;

        //        ComponentManager container = (ComponentManager) ActionContext
        //                .getContext().getSession().get("DefaultComponentManager");

        HttpSession currentSession = pageContext.getSession();

        ComponentManager container = (ComponentManager) currentSession
                .getAttribute("DefaultComponentManager");

        sessionContext = (SessionContext) container
                .getComponent(SessionContextAware.class);

        if (sessionContext == null || !sessionContext.isValid()) {
            return TagSupport.EVAL_BODY_INCLUDE;
        }

        return TagSupport.SKIP_BODY;
    }

}