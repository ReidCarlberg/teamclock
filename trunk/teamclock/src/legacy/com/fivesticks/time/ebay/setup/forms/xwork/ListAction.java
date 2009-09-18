/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;

/**
 * @author Reid
 *
 */
public class ListAction extends SessionContextAwareSupport {

    private Collection forms;
    
    public String execute() throws Exception {
        
        this.setForms(EbayFormServiceDelegate.factory.build(this.getSystemOwner()).findAll());
        
        return this.getSuccess();
        
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
