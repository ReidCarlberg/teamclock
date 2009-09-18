/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import com.opensymphony.xwork.ActionSupport;

public abstract class AbstractRegistrationContextAwareAction extends
        ActionSupport implements RegistrationContextAware {

    private RegistrationContext registrationContext;

    /**
     * @return Returns the registrationContext.
     */
    public RegistrationContext getRegistrationContext() {
        return registrationContext;
    }

    /**
     * @param registrationContext
     *            The registrationContext to set.
     */
    public void setRegistrationContext(RegistrationContext registrationContext) {
        this.registrationContext = registrationContext;
    }

}
