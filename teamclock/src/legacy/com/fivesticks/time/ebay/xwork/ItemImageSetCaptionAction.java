/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;

/**
 * @author REID
 */
public class ItemImageSetCaptionAction extends SessionContextAwareSupport implements EbayItemModifyContextAware {

    private Long imagetarget;
    
    private String caption;

    private EbayItemModifyContext ebayItemModifyContext;
    
    public String execute() throws Exception {

        if (this.getImagetarget() == null || this.getImagetarget().longValue() == 0)
            return SUCCESS;

        this.getEbayItemModifyContext().setImages(null);
        
        EbayItemImageServiceDelegate sd = EbayItemImageServiceDelegate.factory.build(this.getSystemOwner());
        
        
        EbayItemImage i = sd.get(this.getImagetarget());
        
        i.setCaption(this.getCaption());
        
        sd.save(i);

        return SUCCESS;
    }

    public Long getImagetarget() {
        return imagetarget;
    }

    public void setImagetarget(Long target) {
        this.imagetarget = target;
    }
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }
    /**
     * @return Returns the caption.
     */
    public String getCaption() {
        return caption;
    }
    /**
     * @param caption The caption to set.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }
}