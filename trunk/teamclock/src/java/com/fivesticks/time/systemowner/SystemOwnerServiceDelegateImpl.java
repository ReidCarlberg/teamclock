/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.systemowner.delete.FstxCommand;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class SystemOwnerServiceDelegateImpl implements
        SystemOwnerServiceDelegate {

    private SystemOwnerDAO systemOwnerDAO;

    /**
     * @return Returns the systemOwnerDAO.
     */
    public SystemOwnerDAO getSystemOwnerDAO() {
        return systemOwnerDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#findAll()
     */
    public Collection findAll() throws SystemOwnerServiceDelegateException {

        SystemOwnerCriteriaParameters filter = new SystemOwnerCriteriaParameters();

        filter.setSortedByName(true);

        return this.search(filter);

    }

    public Collection findAllById() throws SystemOwnerServiceDelegateException {

        return this.search(new SystemOwnerCriteriaParameters());

    }

    public Collection search(SystemOwnerCriteriaParameters filter) {
        return this.getSystemOwnerDAO().search(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#save(com.fivesticks.time.systemowner.SystemOwner)
     */
    public SystemOwner save(SystemOwner target)
            throws SystemOwnerServiceDelegateException {

        /*
         * do a quick validation
         */
        if (new ValidationHelper().isStringEmpty(target.getKey()))
            throw new SystemOwnerServiceDelegateException("can't save without a key.");
        
        return this.getSystemOwnerDAO().save(target);
    }
    
    public SystemOwner decorateWithKeyAndSave(SystemOwner target) throws SystemOwnerServiceDelegateException {
        
        /*
         * do a quick validation
         */
        if (!new ValidationHelper().isStringEmpty(target.getKey()))
            throw new SystemOwnerServiceDelegateException("can't replace a key");
        
        
            target.setKey(SystemOwnerKeyGenerator.singleton.getGeneratedKey());
        
        return this.save(target);
    }    

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#get(java.lang.Long)
     */
    public SystemOwner get(Long target)
            throws SystemOwnerServiceDelegateException {

        return this.getSystemOwnerDAO().get(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#get(java.lang.String)
     */
    public SystemOwner get(String key)
            throws SystemOwnerServiceDelegateException {

        SystemOwnerCriteriaParameters params = new SystemOwnerCriteriaParameters();
        params.setKey(key);

        return this.handleSearchSingle(params);

    }

    public SystemOwner getByEmail(String email)
            throws SystemOwnerServiceDelegateException {

        SystemOwnerCriteriaParameters params = new SystemOwnerCriteriaParameters();
        params.setContactEmail(email);

        return this.handleSearchSingle(params);

    }

    public SystemOwner handleSearchSingle(SystemOwnerCriteriaParameters params) {
        Collection results = this.getSystemOwnerDAO().search(params);

        SystemOwner ret = null;

        if (results != null && results.size() == 1) {
            ret = (SystemOwner) results.toArray()[0];
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#delete(com.fivesticks.time.systemowner.SystemOwner)
     */
    public void delete(SystemOwner target, SessionContext sessionContext)
            throws SystemOwnerServiceDelegateException {

        // this.getSystemOwnerDAO().delete(target);

        FstxCommand txnCommand = (FstxCommand) SpringBeanBroker
                .getBeanFactory().getBean(SPRING_BEAN_NAME_DELETE);
        // txnCommand.setSessionContext(sessionContext);
        txnCommand.setTarget(target);
        try {
            txnCommand.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemOwnerServiceDelegateException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAOAware#setSystemOwnerDAO(com.fivesticks.time.systemowner.SystemOwnerDAO)
     */
    public void setSystemOwnerDAO(SystemOwnerDAO systemOwnerDAO) {
        this.systemOwnerDAO = systemOwnerDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerServiceDelegate#get(com.fstx.stdlib.authen.users.User)
     */
    public SystemOwner get(User user)
            throws SystemOwnerServiceDelegateException {
        SystemOwner ret = null;

        SystemUser systemUser;

        try {
            systemUser = SystemUserServiceDelegateFactory.factory.build().get(user);
        } catch (SystemUserServiceDelegateException e) {
            throw new SystemOwnerServiceDelegateException(e);
        }

        ret = this.get(systemUser.getOwnerKey());

        return ret;

    }

    public boolean isCurrentOwnerKey(String target) {
        boolean ret = false;

        SystemOwnerCriteriaParameters filter = new SystemOwnerCriteriaParameters();
        filter.setKey(target);

        Collection m = this.search(filter);

        ret = m.size() > 0;

        return ret;
    }



}