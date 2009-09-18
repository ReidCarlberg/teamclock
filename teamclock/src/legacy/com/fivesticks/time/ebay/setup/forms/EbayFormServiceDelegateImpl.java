/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.ebay.setup.forms.util.FormType;

/**
 * @author Reid
 *  
 */
public class EbayFormServiceDelegateImpl extends AbstractServiceDelegate
        implements EbayFormServiceDelegate {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate#save(com.fivesticks.time.ebay.setup.forms.EbayForm)
     */
    public void save(EbayForm target) throws EbayFormServiceDelegateException {

        try {
            this.handleDecorate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new EbayFormServiceDelegateException(e);
        }

        this.getDao().save(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate#delete(com.fivesticks.time.ebay.setup.forms.EbayForm)
     */
    public void delete(EbayForm target) throws EbayFormServiceDelegateException {
        try {
            this.handleValidate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new EbayFormServiceDelegateException(e);
        }

        this.getDao().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate#load(java.lang.Long)
     */
    public EbayForm load(Long id) throws EbayFormServiceDelegateException {

        EbayForm ret = (EbayForm) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new EbayFormServiceDelegateException(e);
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate#find(com.fivesticks.time.ebay.setup.forms.EbayFormCriteriaParameters)
     */
    public Collection find(EbayFormCriteriaParameters params)
            throws EbayFormServiceDelegateException {

        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new EbayFormServiceDelegateException(e);
        }
        
        return this.getDao().find(params);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate#find(com.fivesticks.time.ebay.setup.forms.FormType)
     */
    public Collection find(FormType type) throws EbayFormServiceDelegateException {
        
        EbayFormCriteriaParameters params = new EbayFormCriteriaParameters();
        
        params.setType(type.getName());
        
        return find(params);
    }
    
    public Collection findAll() throws EbayFormServiceDelegateException {
        
        EbayFormCriteriaParameters params = new EbayFormCriteriaParameters();        
        return find(params);

        
    }

}
