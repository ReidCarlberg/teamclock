/*
 * Created on Jun 6, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegateException;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ListingFormDataMerger implements FormDataMerger {

    private SystemOwner systemOwner;

    private EbayForm ebayForm;

    private EbayItem ebayItem;

    private FstxCustomer fstxCustomer;

    private String ebayImageSrc;

    private Collection images;

    /**
     * @param systemOwner
     * @param ebayForm
     * @param ebayItem
     */
    public ListingFormDataMerger(SystemOwner systemOwner, EbayForm ebayForm,
            EbayItem ebayItem, String ebayImageSrc, FstxCustomer fstxCustomer) {

        this.systemOwner = systemOwner;
        this.ebayForm = ebayForm;
        this.ebayItem = ebayItem;
        this.ebayImageSrc = ebayImageSrc;
        this.fstxCustomer = fstxCustomer;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.util.FormDataMerger#getMergedData()
     */
    public String getMergedData() throws FormDataMergerException {

        try {
            this.images = EbayItemImageServiceDelegate.factory.build(
                    systemOwner).getAll(ebayItem);
        } catch (EbayItemImageServiceDelegateException e) {
            throw new FormDataMergerException(e);
        }

        String ret = ebayForm.getForm();

        String detail = ebayItem.getListingDetail();

        detail = detail
                .replaceAll("\r\n\r\n", "\r\n" + ebayForm.getLinebreak());

        /*
         * merge specific items
         */

        detail = detail.replaceAll("\\$", "_USDOLLAR_");

        ret = ret.replaceAll("<listing:headline />", ebayItem
                .getListingHeadline());

        ret = ret.replaceAll("<listing:detail />", detail);

        ret = ret.replaceAll("<customer:id />", this.fstxCustomer.getId().toString());

        ret = ret.replaceAll("<listing:thumbnails />", this.getSimpleThumbs());

        ret = ret.replaceAll("<listing:thumbnails />", this.getSimpleThumbs());

        ret = ret
                .replaceAll("<listing:fullsize />", this.getSimpleImages(null));

        ret = ret.replaceAll("<listing:fullsizeVertical />", this
                .getSimpleImages("<br>"));

        ret = ret.replaceAll("_USDOLLAR_", "\\$");

        return ret;
    }

    public String getSimpleThumbs() {

        StringBuffer ret = new StringBuffer();

        for (Iterator iter = this.images.iterator(); iter.hasNext();) {
            EbayItemImage element = (EbayItemImage) iter.next();

            ret.append("<img src=\"" + this.ebayImageSrc + "/"
                    + element.getImageSmall() + "\" />");

        }

        return ret.toString();

    }

    public String getSimpleImages(String separator) {

        StringBuffer ret = new StringBuffer();

        if (separator == null)
            separator = "";

        for (Iterator iter = this.images.iterator(); iter.hasNext();) {
            EbayItemImage element = (EbayItemImage) iter.next();

            ret.append("<img src=\"" + this.ebayImageSrc + "/"
                    + element.getImageFull() + "\" />" + separator);

            if (element.getCaption() != null) {
                ret.append(separator);
                ret.append(element.getCaption());
                ret.append(separator);
            }

        }

        return ret.toString();

    }

}
