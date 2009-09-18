/*
 * Created on Jun 6, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import java.text.DecimalFormat;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.ebay.CommissionDiscountType;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class ItemFormDataMerger implements FormDataMerger {

    private DecimalFormat df = new DecimalFormat("#,##0.00");

    private SystemOwner systemOwner;

    private EbayForm ebayForm;

    private EbayItem ebayItem;

    private FstxCustomer customer;

    /**
     * @param systemOwner
     * @param ebayForm
     * @param ebayItem
     */
    public ItemFormDataMerger(SystemOwner systemOwner, EbayForm ebayForm,
            EbayItem ebayItem, FstxCustomer customer) {

        this.systemOwner = systemOwner;
        this.ebayForm = ebayForm;
        this.ebayItem = ebayItem;
        this.customer = customer;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.util.FormDataMerger#getMergedData()
     */
    public String getMergedData() throws FormDataMergerException {

        String ret = ebayForm.getForm();

        String detail = ebayItem.getDescription();
        String descriptionShort = ebayItem.getDescriptionShort();
        
        detail = detail.replaceAll("\\$", "_USDOLLAR_");
        descriptionShort = descriptionShort.replaceAll("\\$", "_USDOLLAR_");
         
        ret = ret.replaceAll("<customer:name />", customer.getName());
        ret = ret.replaceAll("<customer:id />", customer.getId().toString());
        ret = ret.replaceAll("<item:descriptionShort />", descriptionShort);
        ret = ret.replaceAll("<item:description />", detail);
        ret = ret.replaceAll("<item:priceStart />", handleFormat(ebayItem.getPriceStart()));
        ret = ret
                .replaceAll("<item:length />", ebayItem.getLength().toString());
        
        ret = ret.replaceAll("<item:prepayAmount />", handleFormat(ebayItem.getPrepayAmount()));
        ret = ret.replaceAll("<item:discount />", this.getDiscount());
        ret = ret.replaceAll("<item:dateAdded />", SimpleDate.factory.build(ebayItem.getDateAdded()).getMmddyyyy());
        ret = ret.replaceAll("<item:id />", ebayItem.getId().toString());

        ret = ret.replaceAll("_USDOLLAR_", "\\$");
        
        return ret;
    }
    
    private String handleFormat(Double target) {
        if (target == null)
            target = new Double("0");
        
        String ret = df.format(target);
        
        
        return ret;
    }

    public String getDiscount() {
        String ret = "";

        if (ebayItem.getCommissionDiscount() != null
                && ebayItem.getCommissionDiscount().doubleValue() > 0.0) {
            String reason = "";

            Lookup l = null;
            try {
                l = LookupServiceDelegate.factory.build(systemOwner).get(
                        ebayItem.getCommissionDiscountTypeLuId());
            } catch (LookupServiceDelegateException e) {
                e.printStackTrace();
            }
            if (l == null) {
                reason = "(" + l.getFullName() + ")";
            }
            if (CommissionDiscountType.getByName(ebayItem
                    .getCommissionDiscountMethod()) == CommissionDiscountType.FLAT) {
                ret = "DISCOUNT: Save "
                        + df.format(ebayItem.getCommissionDiscountBase())
                        + reason;
            } else {
                ret = "DISCOUNT: Save " + ebayItem.getCommissionDiscountBase()
                        + "% " + reason;

            }

        }

        return ret;
    }

}
