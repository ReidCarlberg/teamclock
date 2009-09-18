/*
 * Created on Sep 10, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fivesticks.time.authen.events.AuthenticationEventPublisher;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.menu.MenuSection;
import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class LogoutAction extends ActionSupport implements SessionContextAware {

    private SessionContext sessionContext;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.LOGOUT);

        ActionContext context = ActionContext.getContext();

        if (this.getSessionContext().getUser() != null) {
            AuthenticationEventPublisher.singleton.publishLogout(context, this
                    .getSessionContext(), this.getSessionContext().getUser()
                    .getUser());
            //            LoginHistoryBD.factory.build().recordLogout(
            //                    this.getSessionContext().getUser().getUsername(),
            //                    TimezoneDateTimeResolver.resolve(new Date(), this
            //                            .getSessionContext().getSettings()
            //                            .getSystemTimeZone()), request.getRemoteAddr(),
            //                    this.getSessionContext().getSystemOwner().getKey());
        }

        HttpServletRequest request = (HttpServletRequest) context
                .get(WebWorkStatics.HTTP_REQUEST);

        /*
         * generally only happens in a test environment
         */
        if (request != null) {
            HttpSession session = request.getSession();
            session.invalidate();
        }

        return SUCCESS;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}