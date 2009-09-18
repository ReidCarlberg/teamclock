/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

/**
 * @author REID
 */
public class EbayItemServiceDelegateImpl implements EbayItemServiceDelegate {

    private SystemOwner systemOwner;

    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

//    private EbayItemDAOImpl ebayItemDAOImpl;
    
    private GenericDAO dao;

//    public EbayItemDAOImpl getEbayItemDAOImpl() {
//        return ebayItemDAOImpl;
//    }
//
//    public void setEbayItemDAOImpl(EbayItemDAOImpl ebayItemDAOImpl) {
//        this.ebayItemDAOImpl = ebayItemDAOImpl;
//    }

    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
        return ownerKeyValidatorAndDecorator;
    }

    public void setOwnerKeyValidatorAndDecorator(
            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public Collection find(FstxCustomer fstxCustomer, ListType listType)
            throws EbayItemServiceDelegateException {
        this.handleValidation(fstxCustomer);

        EbayItemFilter filter = new EbayItemFilter();
        filter.setCustId(fstxCustomer.getId());
        if (listType ==ListType.TOLIST) {
            filter.setReadyToList(new Boolean(true));
        } else if (listType == ListType.OPEN) {
            filter.setOpen(new Boolean(true));
        } else if (listType == ListType.TOPAY) {
            filter.setReadyToPay(new Boolean(true));
        } else if (listType == ListType.CLOSED) {
            filter.setCompleted(new Boolean(true));
        }

        return this.find(filter);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#findAll(com.fivesticks.time.customer.FstxCustomer)
     */
    public Collection findAll(FstxCustomer fstxCustomer)
            throws EbayItemServiceDelegateException {

        this.handleValidation(fstxCustomer);

        EbayItemFilter filter = new EbayItemFilter();
        filter.setCustId(fstxCustomer.getId());

        return this.find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#save(com.fivesticks.time.ebay.EbayItem)
     */
    public EbayItem save(EbayItem ebayItem)
            throws EbayItemServiceDelegateException {
        
        handleDecoration(ebayItem);
        this.getDao().save(ebayItem);
        return ebayItem;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#load(java.lang.Long)
     */
    public EbayItem load(Long id) throws EbayItemServiceDelegateException {

        EbayItem ret = (EbayItem) this.getDao().get(id);
        handleValidation(ret);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#delete(com.fivesticks.time.ebay.EbayItem)
     */
    public void delete(EbayItem ebayItem)
            throws EbayItemServiceDelegateException {
        this.handleValidation(ebayItem);
        
        /*
         * images
         */
        EbayItemImageServiceDelegate eisd = EbayItemImageServiceDelegate.factory.build(this.getSystemOwner());
        Collection images;
        try {
            images = eisd.getAll(ebayItem);
            eisd.delete(images);
        } catch (EbayItemImageServiceDelegateException e) {
            
            e.printStackTrace();
        }
        
        
        
        /*
         * shipping
         */
        ItemShippingServiceDelegate issd = ItemShippingServiceDelegate.factory.build(this.getSystemOwner());
        try {
            Collection ships = issd.findByEbayItem(ebayItem);
            issd.delete(ships);
        } catch (ItemShippingServiceDelegateException e1) {
            
            e1.printStackTrace();
        }
        
        /*
         * the item
         */
        this.getDao().delete(ebayItem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#find(com.fivesticks.time.ebay.EbayItemFilter)
     */
    public Collection find(EbayItemFilter filter)
            throws EbayItemServiceDelegateException {

        this.handleDecoration(filter);

        return this.getDao().find(filter);
    }

    private void handleValidation(SystemOwnerKeyAware systemOwnerKeyAware)
            throws EbayItemServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new EbayItemServiceDelegateException(e);
        }
    }

    private void handleDecoration(SystemOwnerKeyAware systemOwnerKeyAware)
            throws EbayItemServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new EbayItemServiceDelegateException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.ebay.EbayItemServiceDelegate#find(com.fivesticks.time.ebay.ListType)
     */
    public Collection find(ListType listType) throws EbayItemServiceDelegateException {

        EbayItemFilter filter = new EbayItemFilter();
        
        if (listType ==ListType.TOLIST) {
            filter.setReadyToList(new Boolean(true));
        } else if (listType == ListType.OPEN) {
            filter.setOpen(new Boolean(true));
        } else if (listType == ListType.TOPAY) {
            filter.setReadyToPay(new Boolean(true));
        } else if (listType == ListType.CLOSED) {
            filter.setCompleted(new Boolean(true));
        }

        return this.find(filter);
        
    }
    /**
     * @return Returns the dao.
     */
    public GenericDAO getDao() {
        return dao;
    }
    /**
     * @param dao The dao to set.
     */
    public void setDao(GenericDAO dao) {
        this.dao = dao;
    }
}