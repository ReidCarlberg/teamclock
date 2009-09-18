/*
 * Created on Aug 10, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.opensymphony.xwork.Action;

/**
 * Designed to be more JSON
 * 
 * Part one really just returns SUCCESS
 * 
 * @author Reid
 */
public class TimeclockOnlyPageActionV2 extends AbstractTimeclockSupportAction {

    public String target;

    public String execute() throws Exception {

        if (this.getTarget() != null) {
            SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                    this.getTarget());
         
            if (systemOwner != null) {
                this.getSessionContext().setSystemOwner(systemOwner);
            }
        }
        
        return Action.SUCCESS;

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}