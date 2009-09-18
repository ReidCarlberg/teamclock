/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import com.fivesticks.time.ebay.setup.forms.util.FormType;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *
 */
public class EbayFormTestFactory {

    private static int counter;
    
    public static EbayForm build() throws Exception {
        counter++;
        
        EbayForm ret = new EbayForm();
        
        ret.setName("name"+counter);
        ret.setType(FormType.LISTING.getName());
        
        return ret;
        
    }
    
    public static EbayForm build(SystemOwner systemOwner) throws Exception {
        
        EbayForm ret = build();
        
        EbayFormServiceDelegate.factory.build(systemOwner).save(ret);
        
        return ret;
    }

    public static EbayForm build(FormType type, SystemOwner systemOwner) throws Exception {
        
        EbayForm ret = build();
        
        ret.setType(type.getName());
        
        StringBuffer sb = new StringBuffer();
        
        sb.append("hello <listing:headline /> and <listing:detail /> and");
        
        sb.append("thumbs <listing:thumbnails /> and ");
        
        sb.append("full images <listing:fullsize />");

        sb.append("full images vertical <listing:fullsizeVertical />");

        ret.setForm(sb.toString());
        
        EbayFormServiceDelegate.factory.build(systemOwner).save(ret);
        
        return ret;
    }
}
