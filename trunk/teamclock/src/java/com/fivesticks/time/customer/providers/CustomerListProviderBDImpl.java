/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.customer.providers;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.common.xwork.AbstractSystemOwnerAwareProvider;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;

/**
 * @author Reid
 * 
 */
public class CustomerListProviderBDImpl extends
        AbstractSystemOwnerAwareProvider implements CustomerListProvider,
        Serializable {

    private Collection customers;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.providers.CustomerListProvider#getCustomers()
     */
    public Collection getCustomers() throws CustomerListProviderException {

        if (customers == null) {
            try {
                customers = CustomerServiceDelegateFactory.factory.build(
                        this.getSystemOwner()).getAll();
            } catch (CustomerServiceDelegateException e) {
                throw new CustomerListProviderException();
            }
        }
        return customers;
    }

    public Collection getCustomers(Long customerStatusLookupId)
            throws CustomerListProviderException {

        CustomerFilterVO params = new CustomerFilterVO();
        params.setStatus(customerStatusLookupId);
        
        try {
            customers = CustomerServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).searchByFilter(params);
        } catch (CustomerServiceDelegateException e) {
            throw new CustomerListProviderException();
        }

        return customers;
    }

}