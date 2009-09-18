/*
 * Created on May 12, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

/**
 * @author Reid
 */
public class EbayItemViewDetailToggleStandardViewAction extends
        SessionContextAwareSupport implements EbayItemModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;

    public String execute() throws Exception {

        this.getEbayItemModifyContext().setStandardView(
                !this.getEbayItemModifyContext().isStandardView());
        
        return SUCCESS;
    }

    /**
     * @return Returns the ebayItemModifyContext.
     */
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }

    /**
     * @param ebayItemModifyContext
     *            The ebayItemModifyContext to set.
     */
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }
}