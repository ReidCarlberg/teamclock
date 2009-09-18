/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Date;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class EbayItemTestFactory {

    private int counter = 0;
    
    public static final EbayItemTestFactory singleton = new EbayItemTestFactory();
    
    public EbayItem getPersisted(SystemOwner systemOwner, FstxCustomer fstxCustomer) throws Exception {
        
//        counter++;
//        
//        EbayItem ret = new EbayItem();
//        
//        ret.setOwnerKey(systemOwner.getKey());
//        ret.setCustId(fstxCustomer.getId());
//        ret.setDescriptionShort("short " + counter);
//        ret.setDescription("long description " + counter);
//        ret.setDateAdded(new Date());
//        ret.setItemStatus(ItemStatusType.ADDED.getName());
//        
//        ret.setListingHeadline("headline " + counter);
//        
//        ret.setListingDetail("detail " + counter);
//        
//        EbayItemDAOImpl dao = (EbayItemDAOImpl) SpringBeanBroker.getBeanFactory().getBean(EbayItemDAOImpl.SPRING_BEAN_NAME);
//        dao.save(ret);
        
        return getPersisted(systemOwner, fstxCustomer, ItemStatusType.ADDED);
    }
    
    public EbayItem getPersisted(SystemOwner systemOwner, FstxCustomer fstxCustomer, ItemStatusType status) throws Exception {
        
        counter++;
        
        EbayItem ret = new EbayItem();
        
        ret.setOwnerKey(systemOwner.getKey());
        ret.setCustId(fstxCustomer.getId());
        ret.setDescriptionShort("short " + counter);
        ret.setDescription("long description " + counter);
        ret.setDateAdded(new Date());
        ret.setItemStatus(status.getName());
        
        ret.setListingHeadline("headline " + counter);
        
        ret.setListingDetail("detail " + counter);
        
        GenericDAO dao = (GenericDAO) SpringBeanBroker.getBeanFactory().getBean(EbayItemDAOImpl.SPRING_BEAN_NAME);
        dao.save(ret);
        
        return ret;
    }    
}
