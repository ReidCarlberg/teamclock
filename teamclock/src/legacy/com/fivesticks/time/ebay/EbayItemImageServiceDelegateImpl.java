/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fivesticks.util.SequenceSorter;

/**
 * @author REID
 */
public class EbayItemImageServiceDelegateImpl implements
        EbayItemImageServiceDelegate {

    private SystemOwner systemOwner;

    private EbayItemImageDAOImpl ebayItemImageDAOImpl;

    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#getAll(com.fivesticks.time.ebay.EbayItem)
     */
    public Collection getAll(EbayItem ebayItem)
            throws EbayItemImageServiceDelegateException {

        this.handleValidation(ebayItem);

//        EbayItemImageFilter filter = new EbayItemImageFilter();
//        filter.setEbayId(ebayItem.getId());

        return this.getAll(ebayItem.getId());
    }

    public Collection getAll(Long ebayItemId)
            throws EbayItemImageServiceDelegateException {

//        this.handleValidation(ebayI);

        EbayItemImageFilter filter = new EbayItemImageFilter();
        filter.setEbayId(ebayItemId);
        

        return this.find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#save(com.fivesticks.time.ebay.EbayItemImage)
     */
    public EbayItemImage save(EbayItemImage ebayItemImage)
            throws EbayItemImageServiceDelegateException {
        this.handleDecoration(ebayItemImage);
        return this.getEbayItemImageDAOImpl().save(ebayItemImage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#get(java.lang.Long)
     */
    public EbayItemImage get(Long id)
            throws EbayItemImageServiceDelegateException {
        EbayItemImage ret = this.getEbayItemImageDAOImpl().get(id);
        this.handleValidation(ret);
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#delete(java.lang.Long)
     */
    public void delete(Long id) throws EbayItemImageServiceDelegateException {
        EbayItemImage curr = this.getEbayItemImageDAOImpl().get(id);
        delete(curr);
    }

    public void delete(EbayItemImage curr)
            throws EbayItemImageServiceDelegateException {

        Long eid = curr.getEbayId();
        
        this.handleValidation(curr);
        this.getEbayItemImageDAOImpl().delete(curr);
        
        this.resequence(eid);
    }
    
    public void delete(Collection target)
    	throws EbayItemImageServiceDelegateException {
        
        for (Iterator iter = target.iterator(); iter.hasNext();) {
            EbayItemImage element = (EbayItemImage) iter.next();
            this.delete(element);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#find(com.fivesticks.time.ebay.EbayItemImageFilter)
     */
    public Collection find(EbayItemImageFilter filter)
            throws EbayItemImageServiceDelegateException {
        this.handleDecoration(filter);
        return this.getEbayItemImageDAOImpl().find(filter);
    }

    public EbayItemImageDAOImpl getEbayItemImageDAOImpl() {
        return ebayItemImageDAOImpl;
    }

    public void setEbayItemImageDAOImpl(
            EbayItemImageDAOImpl ebayItemImageDAOImpl) {
        this.ebayItemImageDAOImpl = ebayItemImageDAOImpl;
    }

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

    private void handleValidation(SystemOwnerKeyAware systemOwnerKeyAware)
            throws EbayItemImageServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new EbayItemImageServiceDelegateException(e);
        }
    }

    private void handleDecoration(SystemOwnerKeyAware systemOwnerKeyAware)
            throws EbayItemImageServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new EbayItemImageServiceDelegateException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#sequenceSooner(java.lang.Long)
     */
    public void sequenceSooner(Long id)
            throws EbayItemImageServiceDelegateException {
        EbayItemImageFilter params = new EbayItemImageFilter();
        

        handleResequence(this.get(id), params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#sequenceLater(java.lang.Long)
     */
    public void sequenceLater(Long id)
            throws EbayItemImageServiceDelegateException {
        EbayItemImageFilter params = new EbayItemImageFilter();

        params.setSortSequenceDesc(new Boolean(true));

        handleResequence(this.get(id), params);
    }

    private void handleResequence(EbayItemImage target,
            EbayItemImageFilter params)
            throws EbayItemImageServiceDelegateException {

        params.setEbayId(target.getEbayId());
        this.handleDecoration(params);

        Collection ret = this.getEbayItemImageDAOImpl().find(params);

        SequenceSorter sorter = new SequenceSorter();

        Collection sorted = sorter.reverse(ret, target);

        this.getEbayItemImageDAOImpl().save(sorted);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.EbayItemImageServiceDelegate#resequence(java.lang.Long)
     */
    public void resequence(Long ebayItemId)
            throws EbayItemImageServiceDelegateException {

        Collection ret = this.getAll(ebayItemId);

        SequenceSorter sorter = new SequenceSorter();

        sorter.resequence(ret);

        this.getEbayItemImageDAOImpl().save(ret);

    }
}