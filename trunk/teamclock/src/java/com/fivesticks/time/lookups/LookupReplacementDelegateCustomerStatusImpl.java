/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.common.TransactionManagerAware;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class LookupReplacementDelegateCustomerStatusImpl implements
        LookupReplacementDelegate, TransactionManagerAware {
    
    public static final String SPRING_BEAN_NAME = "lookupReplaceCustomerStatus";
    
    private SystemOwner systemOwner;
    
    private PlatformTransactionManager transactionManager;
    
    private CustomerServiceDelegate customerBD;

    /* (non-Javadoc)
     * @see com.fivesticks.time.lookups.LookupReplacementDelegate#replace(com.fivesticks.time.lookups.Lookup, com.fivesticks.time.lookups.Lookup)
     */
    public void replace(Lookup original, Lookup replaceWith) throws LookupReplacementDelegateException {
        this.setCustomerBD(CustomerServiceDelegateFactory.factory.build(this.getSystemOwner()));
        
        CustomerFilterVO filter = new CustomerFilterVO();
        
        filter.setStatus(original.getId());
        
        Collection affected;
        try {
            affected = this.getCustomerBD().searchByFilter(filter);
        } catch (CustomerServiceDelegateException e) {
            throw new LookupReplacementDelegateException(e);
        }
        handleReplace(affected, replaceWith);
    }
    
    private void handleReplace(final Collection target, final Lookup newCustomerType) throws LookupReplacementDelegateException{

        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            final CustomerServiceDelegate bd = this.getCustomerBD();
            
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    try {
                        for (Iterator iter = target.iterator(); iter.hasNext();) {
                            Customer element = (Customer) iter.next();
                            element.setStatus(newCustomerType.getId());
                            bd.save(element);
                        }

                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Transaction failed."
                                + e.getMessage(), e);
                    }
                }

            });

        } catch (RuntimeException e) {
            throw new LookupReplacementDelegateException(e);
        }
        
        
    }
    
    

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }
    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
    /**
     * @return Returns the customerBD.
     */
    public CustomerServiceDelegate getCustomerBD() {
        return customerBD;
    }
    /**
     * @param customerBD The customerBD to set.
     */
    public void setCustomerBD(CustomerServiceDelegate customerBD) {
        this.customerBD = customerBD;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.TransactionManagerAware#setTransactionManager(org.springframework.transaction.PlatformTransactionManager)
     */
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    /**
     * @return Returns the transactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
}
