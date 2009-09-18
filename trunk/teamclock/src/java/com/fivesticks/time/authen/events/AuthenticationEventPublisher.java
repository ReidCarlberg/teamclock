/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.authen.events;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.events.GeneralEventQueue;
import com.fivesticks.time.events.GeneralEventType;
import com.fstx.stdlib.authen.LoginHistoryBD;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;

/**
 * @author REID
 */
public class AuthenticationEventPublisher {

    public static final AuthenticationEventPublisher singleton = new AuthenticationEventPublisher();

    public final static String LOGIN = LoginHistoryBD.LOGIN;

    public final static String LOGOUT = LoginHistoryBD.LOGOUT;

    public synchronized void publishLogin(final ActionContext actionContext,
            final SessionContext sessionContext, final User user) {
        handlePublish(actionContext, sessionContext, user, LOGIN);
    }

    public synchronized void publishLogout(final ActionContext actionContext,
            final SessionContext sessionContext, final User user) {
        handlePublish(actionContext, sessionContext, user, LOGOUT);
    }

    private void handlePublish(final ActionContext actionContext,
            final SessionContext sessionContext, final User user,
            final String eventDescription) {
        HttpServletRequest request = (HttpServletRequest) actionContext
                .get(WebWorkStatics.HTTP_REQUEST);
        String remoteAddr = null;
        if (request != null)
            remoteAddr = request.getRemoteAddr();

        AuthenticationEvent event = new AuthenticationEvent();
        event.setSystemOwner(sessionContext.getSystemOwner());
        /*
         * record in Zulu time.
         */
        event.setDate(new Date());
        event.setDescription(eventDescription);
        event.setGeneralEventType(GeneralEventType.AUTHENTICATION_EVENT);
        event.setLocation(remoteAddr);
        event.setUser(user);

        GeneralEventQueue.singleton.add(event);
    }

}