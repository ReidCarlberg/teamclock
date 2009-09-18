/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageDeleteCommand;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;

/**
 * @author REID
 */
public class EbayImageDeleteAction extends SessionContextAwareSupport implements EbayItemModifyContextAware {

    private Long imgtarget;

    private EbayItemModifyContext ebayItemModifyContext;
    
    public String execute() throws Exception {

        if (this.getImgtarget() == null || this.getImgtarget().longValue() == 0)
            return INPUT;

        this.getEbayItemModifyContext().setImages(null);
        
        this.getSessionContext().setSuccessOverride(null);
        
        EbayItemImage image = EbayItemImageServiceDelegate.factory.build(
                this.getSessionContext().getSystemOwner())
                .get(this.getImgtarget());

        EbayItemImageDeleteCommand dc = new EbayItemImageDeleteCommand(image);
        this.getSessionContext().setDeleteContext(
                DeleteContext.factory.build(dc, this.getSessionContext()
                        .getSuccessOverride()));
        return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
    }

    public Long getImgtarget() {
        return imgtarget;
    }

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