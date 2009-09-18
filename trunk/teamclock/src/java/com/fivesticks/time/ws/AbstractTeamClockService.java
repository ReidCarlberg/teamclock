/*
 * Created on Nov 9, 2005
 *
 */
package com.fivesticks.time.ws;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.util.TimezoneDateTimeResolver;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

public abstract class AbstractTeamClockService extends ServletEndpointSupport
        implements GeneralTimeService {

    protected SystemOwner systemOwner;

    protected User user;

    protected SessionContext sessionContext;

    private TimezoneDateTimeResolver resolver;

    protected TimezoneDateTimeResolver getResolver() {
        if (resolver == null) {
            if (sessionContext == null || sessionContext.getSettings() == null
                    || sessionContext.getSettings().getSystemTimeZone() == null) {
                throw new RuntimeException(
                        "sessionContext, settings or timezone is unexpectedly null");
            }
            resolver = new TimezoneDateTimeResolver();
            resolver.setTimezone(sessionContext.getSettings()
                    .getSystemTimeZone());
        }

        return resolver;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

}
