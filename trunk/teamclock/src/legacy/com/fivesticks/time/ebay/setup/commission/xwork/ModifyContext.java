/*
 * Created on Jun 15, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission.xwork;

import java.io.Serializable;

import com.fivesticks.time.ebay.setup.commission.SimpleCommission;

/**
 * @author Reid
 *
 */
public class ModifyContext implements Serializable {

    private SimpleCommission target;
    
    
    /**
     * @return Returns the target.
     */
    public SimpleCommission getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(SimpleCommission target) {
        this.target = target;
    }
}
