/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import java.util.Collection;

/**
 * @author Reid
 *
 */
public interface SimpleCommissionServiceDelegate {

    public static final String SPRING_BEAN_NAME = "simpleCommissionServiceDelegate";
    
    public static final SimpleCommissionServiceDelegateFactory factory = new SimpleCommissionServiceDelegateFactory();

    public void save(SimpleCommission simpleCommission) throws SimpleCommissionServiceDelegateException;
    
    public SimpleCommission get(Long id) throws SimpleCommissionServiceDelegateException;
    
    public void delete(SimpleCommission simpleCommission) throws SimpleCommissionServiceDelegateException;
    
    public Collection getAll() throws SimpleCommissionServiceDelegateException;
    
    public Collection find(SimpleCommissionCriteriaParameters params) throws SimpleCommissionServiceDelegateException;
    
    
}
