/*
 * Created on Aug 21, 2003
 *
 */
package com.fivesticks.time.taglibs;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * Needs to take a property and, if it checks out from an authorization point of
 * view, then process the body.
 * 
 * @author Reid
 *  
 */
public class SettingsSystemNameTag extends TagSupport {

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

        if (sessionContext != null) {
            try {

                this.pageContext.getOut().write(
                        sessionContext.getSettings().getSystemName());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return TagSupport.SKIP_BODY;
    }

}