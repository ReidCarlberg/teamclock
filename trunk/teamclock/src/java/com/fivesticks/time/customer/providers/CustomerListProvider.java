/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.customer.providers;

import java.util.Collection;


/**
 * @author Reid
 *
 */
public interface CustomerListProvider {

    public Collection getCustomers() throws CustomerListProviderException;
    
    public Collection getCustomers(Long customerStatusLookupId) throws CustomerListProviderException;
    
}
