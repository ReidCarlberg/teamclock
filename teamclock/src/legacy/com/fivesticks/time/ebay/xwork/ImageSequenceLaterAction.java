/*
 * Created on Jan 12, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;

/**
 * @author Reid
 */
public class ImageSequenceLaterAction extends SessionContextAwareSupport
        implements EbayItemModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;

    private Long imgtarget;

    public String execute() throws Exception {

        EbayItemImageServiceDelegate.factory.build(
                this.getSessionContext().getSystemOwner()).sequenceLater(
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