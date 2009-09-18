/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;

/**
 * @author REID
 */
public interface EbayItemImageServiceDelegate extends SystemOwnerAware, OwnerKeyValidatorAndDecoratorAware {

    public static final String SPRING_BEAN_NAME = "ebayItemImageServiceDelegate";
    
    public static final EbayItemImageServiceDelegateFactory factory = new EbayItemImageServiceDelegateFactory();
    
    public Collection getAll(EbayItem ebayItem) throws EbayItemImageServiceDelegateException;
    
    public EbayItemImage save(EbayItemImage ebayItemImage) throws EbayItemImageServiceDelegateException;
    
    public EbayItemImage get(Long id) throws EbayItemImageServiceDelegateException;
    
    public void delete(Long id) throws EbayItemImageServiceDelegateException;
    
    public void delete(EbayItemImage target) throws EbayItemImageServiceDelegateException;
    
    public void delete(Collection target) throws EbayItemImageServiceDelegateException;
    
    public void resequence(Long ebayItemId) throws EbayItemImageServiceDelegateException;
    
    public void sequenceSooner(Long id) throws EbayItemImageServiceDelegateException;
    
    public void sequenceLater(Long id) throws EbayItemImageServiceDelegateException;
    
    public Collection find(EbayItemImageFilter filter) throws EbayItemImageServiceDelegateException;
    
}
