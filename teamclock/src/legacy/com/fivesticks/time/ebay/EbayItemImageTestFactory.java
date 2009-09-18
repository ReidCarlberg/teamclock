/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class EbayItemImageTestFactory {

    public static final EbayItemImageTestFactory factory = new EbayItemImageTestFactory();
    
    private int counter;
    
    public EbayItemImage getPersisted(SystemOwner systemOwner, EbayItem ebayItem) throws Exception {
        counter++;
        EbayItemImage ret = new EbayItemImage();
        
        ret.setEbayId(ebayItem.getId());
        ret.setOwnerKey(systemOwner.getKey());
        ret.setImageFull("full image " + counter);
        ret.setImageSmall("small " + counter);
        
        EbayItemImageDAOImpl dao = (EbayItemImageDAOImpl) SpringBeanBroker.getBeanFactory().getBean(EbayItemImageDAOImpl.SPRING_BEAN_NAME);

        dao.save(ret);
        
        return ret;
    }
}
