/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import java.util.Collection;

import com.fivesticks.time.ebay.setup.forms.util.FormType;

/**
 * @author Reid
 *
 */
public interface EbayFormServiceDelegate {

    public static final String SPRING_BEAN_NAME = "ebayFormServiceDelegate";
    
    public static final EbayFormServiceDelegateFactory factory = new EbayFormServiceDelegateFactory();
    
    public void save(EbayForm target) throws EbayFormServiceDelegateException;
    
    public void delete(EbayForm target) throws EbayFormServiceDelegateException;
    
    public EbayForm load(Long id) throws EbayFormServiceDelegateException;
    
    public Collection find(EbayFormCriteriaParameters params) throws EbayFormServiceDelegateException;
    
    public Collection find(FormType type) throws EbayFormServiceDelegateException;
    
    public Collection findAll() throws EbayFormServiceDelegateException;
    
}
