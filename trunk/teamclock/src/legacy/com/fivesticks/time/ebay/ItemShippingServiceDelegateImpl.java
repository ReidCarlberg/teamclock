/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

/**
 * @author Reid
 */
public class ItemShippingServiceDelegateImpl implements
        ItemShippingServiceDelegate, SystemOwnerAware,
        OwnerKeyValidatorAndDecoratorAware {

    private SystemOwner systemOwner;

    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    private ItemShippingDAO itemShippingDAO;

    private void handleDecorate(SystemOwnerKeyAware target)
            throws ItemShippingServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ItemShippingServiceDelegateException(e);
        }
    }

    private void handleValidate(SystemOwnerKeyAware target)
            throws ItemShippingServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ItemShippingServiceDelegateException(e);
        }
    }

    /**
     * @return Returns the itemShippingDAO.
     */
    public ItemShippingDAO getItemShippingDAO() {
        return itemShippingDAO;
    }

    /**
     * @param itemShippingDAO
     *            The itemShippingDAO to set.
     */
    public void setItemShippingDAO(ItemShippingDAO itemShippingDAO) {
        this.itemShippingDAO = itemShippingDAO;
    }

    /**
     * @return Returns the ownerKeyValidatorAndDecorator.
     */
    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
        return ownerKeyValidatorAndDecorator;
    }

    /**
     * @param ownerKeyValidatorAndDecorator
     *            The ownerKeyValidatorAndDecorator to set.
     */
    public void setOwnerKeyValidatorAndDecorator(
            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#save(com.fivesticks.time.ebay.ItemShipping)
     */
    public void save(ItemShipping target)
            throws ItemShippingServiceDelegateException {
        this.handleDecorate(target);
        this.getItemShippingDAO().save(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#get(java.lang.Long)
     */
    public ItemShipping get(Long id)
            throws ItemShippingServiceDelegateException {
        ItemShipping ret = this.getItemShippingDAO().get(id);
        this.handleValidate(ret);
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#delete(java.lang.Long)
     */
    public void delete(Long id) throws ItemShippingServiceDelegateException {
        ItemShipping target = this.get(id);
        delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#delete(com.fivesticks.time.ebay.ItemShipping)
     */
    public void delete(ItemShipping target)
            throws ItemShippingServiceDelegateException {
        this.handleValidate(target);
        this.getItemShippingDAO().delete(target);
    }

    public void delete(Collection target)  throws ItemShippingServiceDelegateException{
        for (Iterator iter = target.iterator(); iter.hasNext();) {
            ItemShipping element = (ItemShipping) iter.next();
            this.delete(element);
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#find(com.fivesticks.time.ebay.ItemShippingFilter)
     */
    public Collection find(ItemShippingFilter filter)
            throws ItemShippingServiceDelegateException {
        
        this.handleDecorate(filter);
        
        return this.getItemShippingDAO().find(filter);
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.ItemShippingServiceDelegate#findByEbayItem(com.fivesticks.time.ebay.EbayItem)
     */
    public Collection findByEbayItem(EbayItem item)
            throws ItemShippingServiceDelegateException {

        ItemShippingFilter filter = new ItemShippingFilter();
        filter.setEbayId(item.getId());
        
        return this.find(filter);
    }
}