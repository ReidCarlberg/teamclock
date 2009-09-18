/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

/**
 * @author Reid
 */
public interface ItemShippingDAO {

    public static final String SPRING_BEAN_NAME = "ebayItemShippingDAO";
    
    public void save(ItemShipping target);
    
    public ItemShipping get(Long id);
    
    public void delete(ItemShipping target);
    
    public Collection find(final ItemShippingFilter filter);
    
}
