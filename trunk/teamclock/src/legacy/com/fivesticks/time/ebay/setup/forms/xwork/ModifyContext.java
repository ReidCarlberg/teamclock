/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms.xwork;

import java.io.Serializable;

import com.fivesticks.time.ebay.setup.forms.EbayForm;

/**
 * @author Reid
 *
 */
public class ModifyContext implements Serializable {

    private EbayForm target;
    
    
    /**
     * @return Returns the target.
     */
    public EbayForm getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(EbayForm target) {
        this.target = target;
    }
}
