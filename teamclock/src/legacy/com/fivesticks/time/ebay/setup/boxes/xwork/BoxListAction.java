/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.boxes.BoxCriteriaParameters;
import com.fivesticks.time.ebay.setup.boxes.BoxFilterBuilder;
import com.fivesticks.time.ebay.setup.boxes.BoxServiceDelegate;

/**
 * @author Reid
 */
public class BoxListAction extends SessionContextAwareSupport {

    private Collection boxes;
    
    public String execute() throws Exception {
        
        BoxCriteriaParameters filter = BoxFilterBuilder.buildActiveByLength();
        
        BoxServiceDelegate sd = BoxServiceDelegate.factory.build(this.getSystemOwner());
        
        this.setBoxes(sd.find(filter));
        
        return SUCCESS;
    }
    
    /**
     * @return Returns the boxes.
     */
    public Collection getBoxes() {
        return boxes;
    }
    /**
     * @param boxes The boxes to set.
     */
    public void setBoxes(Collection boxes) {
        this.boxes = boxes;
    }
}
