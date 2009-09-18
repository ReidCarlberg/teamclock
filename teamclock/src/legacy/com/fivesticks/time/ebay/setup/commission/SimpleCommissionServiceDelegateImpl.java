/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

/**
 * @author Reid
 *
 */
public class SimpleCommissionServiceDelegateImpl extends AbstractServiceDelegate implements SimpleCommissionServiceDelegate {

    public void save(SimpleCommission simpleCommission)  throws SimpleCommissionServiceDelegateException {
        try {
            this.handleDecorate(simpleCommission);
        } catch (AbstractServiceDelegateException e) {
            throw new SimpleCommissionServiceDelegateException(e);
        }
        this.getDao().save(simpleCommission);
    }
    
    public SimpleCommission get(Long id) throws SimpleCommissionServiceDelegateException {
        
        SimpleCommission ret =  (SimpleCommission) this.getDao().get(id);
        
        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new SimpleCommissionServiceDelegateException(e);
        }
        
        return ret;
        
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate#find(com.fivesticks.time.ebay.setup.commission.SimpleCommissionCriteriaParameters)
     */
    public Collection find(SimpleCommissionCriteriaParameters params) throws SimpleCommissionServiceDelegateException {
        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new SimpleCommissionServiceDelegateException(e);
        }
        return this.getDao().find(params);
    }
    
    public void delete(SimpleCommission simpleCommission) throws SimpleCommissionServiceDelegateException {
        this.getDao().delete(simpleCommission);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate#getAll()
     */
    public Collection getAll() throws SimpleCommissionServiceDelegateException {
        SimpleCommissionCriteriaParameters p = new SimpleCommissionCriteriaParameters();
        return this.find(p);
    }
    
    
}
