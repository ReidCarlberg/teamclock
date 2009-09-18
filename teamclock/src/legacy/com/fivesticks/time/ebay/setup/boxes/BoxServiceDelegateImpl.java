/*
 * Created on May 10, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

/**
 * @author Reid
 */
public class BoxServiceDelegateImpl extends AbstractServiceDelegate implements BoxServiceDelegate {

//    private SystemOwner systemOwner;
//
//    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;
//
//    private BoxDAO boxDAO;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#findLarger(java.lang.Integer,
     *      java.lang.Integer, java.lang.Integer)
     */
    public Collection findLarger(Integer length, Integer width, Integer height)
            throws BoxServiceDelegateException {

        BoxCriteriaParameters filter = BoxFilterBuilder.buildActiveLargerThan(length,
                width, height);

        return this.find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#findLargerInstock(java.lang.Integer,
     *      java.lang.Integer, java.lang.Integer)
     */
    public Collection findLargerInstock(Integer length, Integer width,
            Integer height) throws BoxServiceDelegateException {

        BoxCriteriaParameters filter = BoxFilterBuilder.buildActiveInstockLargerThan(
                length, width, height);

        return this.find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#find(com.fivesticks.time.ebay.setup.BoxFilter)
     */
    public Collection find(BoxCriteriaParameters filter) throws BoxServiceDelegateException {

        try {
            this.handleDecorate(filter);
        } catch (AbstractServiceDelegateException e) {
            throw new BoxServiceDelegateException(e);
        }
        
        return this.getDao().find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#getBox(java.lang.Long)
     */
    public Box getBox(Long id) throws BoxServiceDelegateException {
        Box ret = (Box) this.getDao().get(id);
        if (ret != null) {
            try {
                this.handleValidate(ret);
            } catch (AbstractServiceDelegateException e) {
                throw new BoxServiceDelegateException(e);
            }
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#delete(java.lang.Long)
     */
    public void delete(Long id) throws BoxServiceDelegateException {
        Box box = this.getBox(id);
        this.delete(box);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#delete(com.fivesticks.time.ebay.setup.Box)
     */
    public void delete(Box box) throws BoxServiceDelegateException {
        try {
            this.handleValidate(box);
        } catch (AbstractServiceDelegateException e) {
            throw new BoxServiceDelegateException(e);
        }
        this.getDao().delete(box);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.BoxServiceDelegate#save(com.fivesticks.time.ebay.setup.Box)
     */
    public void save(Box box) throws BoxServiceDelegateException {
        try {
            handleDecorate(box);
        } catch (AbstractServiceDelegateException e) {
            throw new BoxServiceDelegateException(e);
        }
        this.getDao().save(box);
    }

//    private void handleDecorate(SystemOwnerKeyAware target) throws BoxServiceDelegateException {
//        try {
//            this.getOwnerKeyValidatorAndDecorator().decorate(target, this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new BoxServiceDelegateException(e);
//        }
//    }
//    
//    private void handleValidate(SystemOwnerKeyAware target) throws BoxServiceDelegateException {
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(target, this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new BoxServiceDelegateException(e);
//        }
//    }
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.fivesticks.time.ebay.setup.BoxDAOAware#setBoxDAO(com.fivesticks.time.ebay.setup.BoxDAO)
//     */
//    public void setBoxDAO(BoxDAO boxDAO) {
//        this.boxDAO = boxDAO;
//    }
//
//    /**
//     * @return Returns the ownerKeyValidatorAndDecorator.
//     */
//    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
//        return ownerKeyValidatorAndDecorator;
//    }
//
//    /**
//     * @param ownerKeyValidatorAndDecorator
//     *            The ownerKeyValidatorAndDecorator to set.
//     */
//    public void setOwnerKeyValidatorAndDecorator(
//            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
//        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
//    }
//
//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//
//    /**
//     * @param systemOwner
//     *            The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }
//
//    /**
//     * @return Returns the boxDAO.
//     */
//    public BoxDAO getBoxDAO() {
//        return boxDAO;
//    }
}