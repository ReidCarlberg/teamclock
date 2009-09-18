/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

public class ConfirmOrderAction extends AbstractRegistrationContextAwareAction {

    public String submitConfirm;
    
    public String execute() throws Exception {
        
        if (this.getSubmitConfirm() == null) {
            return INPUT;
        }
        
        /*
         * handle registration.
         */
        
        
        return SUCCESS;
        
    }

    /**
     * @return Returns the submitConfirm.
     */
    public String getSubmitConfirm() {
        return submitConfirm;
    }

    /**
     * @param submitConfirm The submitConfirm to set.
     */
    public void setSubmitConfirm(String submitConfirm) {
        this.submitConfirm = submitConfirm;
    }
}
