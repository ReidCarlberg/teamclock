/*
 * Created on May 19, 2006
 *
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractModifyContextAware extends
        SessionContextAwareSupport implements ModifyContextAware {

    private ModifyContext modifyContext;

    /**
     * @return Returns the modifyContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }

    /**
     * @param modifyContext
     *            The modifyContext to set.
     */
    public void setModifyContext(ModifyContext modifyContext) {
        this.modifyContext = modifyContext;
    }

}
