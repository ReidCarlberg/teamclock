/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.contactv2.forms.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegateFactory;
import com.opensymphony.xwork.Action;

public class WebFormListAction extends SessionContextAwareSupport {

    private Collection forms;
    
    public String execute() throws Exception {
        
        Collection f = WebFormServiceDelegateFactory.factory.build(this.getSessionContext()).findAll();
     
        this.setForms(f);
        
        return Action.SUCCESS;
    }

    /**
     * @return Returns the forms.
     */
    public Collection getForms() {
        return forms;
    }

    /**
     * @param forms The forms to set.
     */
    public void setForms(Collection forms) {
        this.forms = forms;
    }
}
