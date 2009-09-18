/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen;

import java.util.Collection;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 *  
 */
public interface LoginHistoryDAO {

    /**
     * @param params
     * @return
     */
    public abstract Collection find(LoginHistoryFilterParameters params)
            throws DAOException;

    /**
     * @param lh
     */
    public abstract LoginHistory save(LoginHistory target) throws DAOException;

    public abstract void delete(LoginHistory element);
}