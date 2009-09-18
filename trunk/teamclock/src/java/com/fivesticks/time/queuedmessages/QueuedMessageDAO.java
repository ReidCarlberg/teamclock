/*
 * Created on Apr 30, 2004
 *
 */
package com.fivesticks.time.queuedmessages;

import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface QueuedMessageDAO {

    public abstract QueuedMessage save(QueuedMessage u) throws DAOException;

    public abstract QueuedMessage get(Long id) throws DAOException;

    public abstract void delete(QueuedMessage u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(QueuedMessageFilter filter) throws DAOException;
}