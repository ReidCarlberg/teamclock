/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

/**
 * @author Reid
 */
public interface ItemShippingServiceDelegate {

    public static final String SPRING_BEAN_NAME = "ebayItemShippingServiceDelegate";
    
    public static final ItemShippingServiceDelegateFactory factory = new ItemShippingServiceDelegateFactory();
    
    public void save(ItemShipping target) throws ItemShippingServiceDelegateException;
    
    public ItemShipping get(Long id) throws ItemShippingServiceDelegateException;
    
    public void delete(Long id) throws ItemShippingServiceDelegateException;
    
    public void delete(ItemShipping target) throws ItemShippingServiceDelegateException;
    
    public void delete(Collection target) throws ItemShippingServiceDelegateException;
    
    public Collection find(ItemShippingFilter filter) throws ItemShippingServiceDelegateException;
    
    public Collection findByEbayItem(EbayItem item) throws ItemShippingServiceDelegateException;
    
}
