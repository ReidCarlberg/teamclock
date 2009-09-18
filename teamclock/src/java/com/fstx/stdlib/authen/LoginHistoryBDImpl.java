package com.fstx.stdlib.authen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.xwork.KeyValueVO;

/**
 * @author Nick
 *  
 */
public class LoginHistoryBDImpl extends AbstractServiceDelegate implements LoginHistoryBD {

//    private LoginHistoryDAO dao;

    public void recordLogin(String username, Date timestamp, String location,
            String ownerKey) throws LoginHistoryException {
        //        LoginHistory lh = new LoginHistory(username, timestamp,
        // LoginHistoryBD.LOGIN);
        //        lh.setLocation(location);
        //        lh.setOwnerKey(ownerKey);
        //        try {
        //            this.getLoginHistoryDAO().save(lh);
        //        } catch (DAOException e) {
        //            throw new LoginHistoryException("Unable to record the Login.", e);
        //        }
        recordEvent(username, timestamp, location, ownerKey,
                LoginHistoryBD.LOGIN);
    }

    public void recordLogout(String username, Date timestamp, String location,
            String ownerKey) throws LoginHistoryException {
        //        LoginHistory lh = new LoginHistory(username, timestamp,
        // LoginHistoryBD.LOGOUT);
        //        lh.setLocation(location);
        //        lh.setOwnerKey(ownerKey);
        //        try {
        //            this.getLoginHistoryDAO().save(lh);
        //        } catch (DAOException e) {
        //            throw new LoginHistoryException("Unable to record the Logout.", e);
        //        }
        recordEvent(username, timestamp, location, ownerKey,
                LoginHistoryBD.LOGOUT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.LoginHistoryBD#recordEvent(java.lang.String,
     *      java.util.Date, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public void recordEvent(String username, Date timestamp, String location,
            String ownerKey, String eventDescription)
            throws LoginHistoryException {
        LoginHistory lh = new LoginHistory(username, timestamp,
                eventDescription);
        lh.setLocation(location);
        lh.setOwnerKey(ownerKey);
            this.getDao().save(lh);

    }

    public Collection list() throws LoginHistoryException {
        Collection ret = null;

        LoginHistoryFilterParameters params = new LoginHistoryFilterParameters();

        try {
            ret = this.getDao().find(params);
        } catch (Exception e) {
            throw new LoginHistoryException(
                    "Unable to get list from persistance.", e);
        }

        return ret;
    }

//    /**
//     * @return
//     */
//    public LoginHistoryDAO getDao() {
//        return dao;
//    }
//
//    /**
//     * @param historyDAO
//     */
//    public void setLoginHistoryDAO(LoginHistoryDAO historyDAO) {
//        dao = historyDAO;
//    }

    public Collection search(LoginHistoryFilterParameters params)
            throws LoginHistoryException {
            return this.getDao().find(params);
    }
    
    public Collection searchStatsByUser(LoginHistoryFilterParameters params){
        
        CriteriaDecorator old = this.getDao().getCriteriaBuilder();
        
        this.getDao().setCriteriaBuilder(new LoginHistoryAggregateCriteriaBuilder());
        
        params.setGroupByUser(new Boolean(true));
        params.setGroupByOwnerKey(null);
        
        Collection l = this.getDao().find(params);
        
        this.getDao().setCriteriaBuilder(old);
        
        Collection ret = new ArrayList();
        
        for (Iterator iter = l.iterator(); iter.hasNext();) {
            Object[] element = (Object[]) iter.next();
            KeyValueVO curr = new KeyValueVO(element[0].toString(), element[1].toString());
            ret.add(curr);
        }
        
        
        return ret;
    }
    
    public Collection searchStatsByOwner(LoginHistoryFilterParameters params){
        
        CriteriaDecorator old = this.getDao().getCriteriaBuilder();
        
        this.getDao().setCriteriaBuilder(new LoginHistoryAggregateCriteriaBuilder());
        
        params.setGroupByUser(null);
        params.setGroupByOwnerKey(new Boolean(true));
        
        Collection l = this.getDao().find(params);
        
        this.getDao().setCriteriaBuilder(old);
        Collection ret = new ArrayList();
        
        for (Iterator iter = l.iterator(); iter.hasNext();) {
            Object[] element = (Object[]) iter.next();
            KeyValueVO curr = new KeyValueVO(element[0].toString(), element[1].toString());
            ret.add(curr);
        }
        
        
        return ret;
        }    

}