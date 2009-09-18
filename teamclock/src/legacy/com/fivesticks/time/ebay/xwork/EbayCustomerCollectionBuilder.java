/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.ebay.util.ContactLastNameComparator;
import com.fivesticks.time.ebay.util.CustomerAndContactVOBuilder;

/**
 * @author REID
 */
public class EbayCustomerCollectionBuilder {

//    private Log log = LogFactory.getLog(EbayCustomerCollectionBuilder.class);

    public Collection build(SessionContext sessionContext) throws Exception {
        Collection cust = CustomerServiceDelegate.factory.build(sessionContext)
                .getCustomerBySetting(CustomerSettingType.IS_AUCTION_CUSTOMER,
                        "true");

        Collection vo = new CustomerAndContactVOBuilder().build(sessionContext
                .getSystemOwner(), cust);

        return vo;

    }

    public Collection buildSortedByContact(SessionContext sessionContext) throws Exception {
        
        FstxCustomerFilterVO filter = new FstxCustomerFilterVO();
        
        return this.buildSortedByContact(sessionContext, filter);

    }
    
    public Collection buildSortedByContact(SessionContext sessionContext,
            FstxCustomerFilterVO filter) throws Exception {
        
        Collection cust = CustomerServiceDelegate.factory.build(sessionContext)
                .getCustomerBySetting(filter, CustomerSettingType.IS_AUCTION_CUSTOMER,
                        "true");

        Collection vo = new CustomerAndContactVOBuilder().build(sessionContext
                .getSystemOwner(), cust);

        Set ret = new TreeSet(new ContactLastNameComparator());

        ret.addAll(vo);
        return ret;

    }
}
