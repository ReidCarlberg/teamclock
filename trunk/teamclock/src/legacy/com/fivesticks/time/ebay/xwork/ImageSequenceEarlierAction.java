/*
 * Created on Jan 12, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;

/**
 * @author Reid
 */
public class ImageSequenceEarlierAction extends SessionContextAwareSupport implements EbayItemModifyContextAware {

    private Long imgtarget;

    private EbayItemModifyContext ebayItemModifyContext;
    

    public String execute() throws Exception {

        EbayItemImageServiceDelegate.factory.build(
                this.getSessionContext().getSystemOwner()).sequenceSooner(
                this.getImgtarget());

        this.getEbayItemModifyContext().setImages(null);
        return SUCCESS;
    }


    /**
     * @return Returns the target.
     */
    public Long getImgtarget() {
        return imgtarget;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setImgtarget(Long target) {
        this.imgtarget = target;
    }
    
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }    
}