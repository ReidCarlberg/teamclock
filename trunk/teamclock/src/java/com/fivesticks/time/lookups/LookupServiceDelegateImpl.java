/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

/**
 * @author Reid
 */
public class LookupServiceDelegateImpl extends AbstractServiceDelegate
        implements LookupServiceDelegate {

    //    private SystemOwner systemOwner;
    //    
    //    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;
    //
    //    private GenericDAO dao;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#getAll(com.fivesticks.time.lookups.LookupType)
     */
    public Collection getAll(LookupType type)
            throws LookupServiceDelegateException {

        LookupCriteriaParameters params = new LookupCriteriaParameters();
        params.setType(type.getName());

        return this.search(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#save(com.fivesticks.time.lookups.Lookup)
     */
    public void save(Lookup target) throws LookupServiceDelegateException {
        try {
            handleDecorate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new LookupServiceDelegateException(e);
        }
        this.getDao().save(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#delete(com.fivesticks.time.lookups.Lookup)
     */
    public void delete(Lookup target) throws LookupServiceDelegateException {
        try {
            handleValidate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new LookupServiceDelegateException(e);
        }
        this.getDao().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#get(java.lang.Long)
     */
    public Lookup get(Long id) throws LookupServiceDelegateException {

        if (id == null || id.longValue() == 0)
            return null;

        Lookup ret = (Lookup) this.getDao().get(id);

        if (ret != null) {
            try {
                handleValidate(ret);
            } catch (AbstractServiceDelegateException e) {
                throw new LookupServiceDelegateException(e);
            }
        }

        return ret;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#replace(java.lang.Long,
     *      java.lang.Long)
     */
    public void replace(Long original, Long replaceWith)
            throws LookupServiceDelegateException {
        Lookup orig = this.get(original);
        Lookup repl = this.get(replaceWith);

        this.replace(orig, repl);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#replace(com.fivesticks.time.lookups.Lookup,
     *      com.fivesticks.time.lookups.Lookup)
     */
    public void replace(Lookup original, Lookup replaceWith)
            throws LookupServiceDelegateException {

        if (original == null || replaceWith == null) {
            throw new LookupServiceDelegateException(
                    "can't have null on either the original or the replaceWith");
        }

        LookupType type = LookupType.getByShortName(original.getType());

        LookupReplacementDelegate del;
        try {
            del = LookupReplacementDelegateFactory.factory.build(type, this
                    .getSystemOwner());
        } catch (LookupReplacementDelegateException e) {
            throw new LookupServiceDelegateException(e);
        }
        try {
            del.replace(original, replaceWith);
        } catch (LookupReplacementDelegateException e1) {
            throw new LookupServiceDelegateException(e1);
        }

    }

    //    /* (non-Javadoc)
    //     * @see
    // com.fivesticks.time.systemowner.SystemOwnerAware#setSystemOwner(com.fivesticks.time.systemowner.SystemOwner)
    //     */
    //    public void setSystemOwner(SystemOwner systemOwner) {
    //        this.systemOwner = systemOwner;
    //    }
    //
    //    /* (non-Javadoc)
    //     * @see
    // com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware#setOwnerKeyValidatorAndDecorator(com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator)
    //     */
    //    public void
    // setOwnerKeyValidatorAndDecorator(OwnerKeyValidatorAndDecorator
    // ownerKeyValidatorAndDecorator) {
    //        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
    //    }
    //    
    //    
    //
    //    /**
    //     * @return Returns the dao.
    //     */
    //    public GenericDAO getDao() {
    //        return dao;
    //    }
    //    /**
    //     * @param dao The dao to set.
    //     */
    //    public void setDao(GenericDAO dao) {
    //        this.dao = dao;
    //    }
    //    /**
    //     * @return Returns the ownerKeyValidatorAndDecorator.
    //     */
    //    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
    //        return ownerKeyValidatorAndDecorator;
    //    }
    //    /**
    //     * @return Returns the systemOwner.
    //     */
    //    public SystemOwner getSystemOwner() {
    //        return systemOwner;
    //    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.lookups.LookupServiceDelegate#search(com.fivesticks.time.lookups.LookupCriteriaParameters)
     */
    public Collection search(LookupCriteriaParameters parameters)
            throws LookupServiceDelegateException {

        try {
            handleDecorate(parameters);
        } catch (AbstractServiceDelegateException e) {
            throw new LookupServiceDelegateException(e);
        }

        return this.getDao().find(parameters);

    }

    //    private void handleDecorate(SystemOwnerKeyAware systemOwnerKeyAware)
    // throws LookupServiceDelegateException {
    //        try {
    //            this.getOwnerKeyValidatorAndDecorator().decorate(systemOwnerKeyAware,
    // this.getSystemOwner());
    //        } catch (OwnerKeyValidatorAndDecoratorException e) {
    //            throw new LookupServiceDelegateException(e);
    //        }
    //    }
    //    
    //    private void handleValidate(SystemOwnerKeyAware systemOwnerKeyAware)
    // throws LookupServiceDelegateException {
    //        try {
    //            this.getOwnerKeyValidatorAndDecorator().validate(systemOwnerKeyAware,
    // this.getSystemOwner());
    //        } catch (OwnerKeyValidatorAndDecoratorException e) {
    //            throw new LookupServiceDelegateException(e);
    //        }
    //    }
}
