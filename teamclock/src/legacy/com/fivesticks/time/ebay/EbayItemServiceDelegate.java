/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;

/**
 * @author REID
 */
public interface EbayItemServiceDelegate extends SystemOwnerAware, OwnerKeyValidatorAndDecoratorAware {

    public static final String SPRING_BEAN_NAME = "ebayItemServiceDelegate";
    
    public static final EbayItemServiceDelegateFactory factory = new EbayItemServiceDelegateFactory();

    public Collection find(ListType listType) throws EbayItemServiceDelegateException;

    public Collection find(FstxCustomer fstxCustomer, ListType listType) throws EbayItemServiceDelegateException;

    public Collection findAll(FstxCustomer fstxCustomer) throws EbayItemServiceDelegateException;
    
    public EbayItem save(EbayItem ebayItem) throws EbayItemServiceDelegateException;
    
    public EbayItem load(Long id) throws EbayItemServiceDelegateException;
    
    public void delete(EbayItem ebayItem) throws EbayItemServiceDelegateException;
    
    public Collection find(EbayItemFilter filter) throws EbayItemServiceDelegateException;
    
}
