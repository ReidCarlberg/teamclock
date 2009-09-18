/*
 * Created on Apr 30, 2004
 *
 */
package com.fivesticks.time.activity;

import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface ActivityDAO {

    public abstract Activity save(Activity u) throws DAOException;

    public abstract Activity get(Long id) throws DAOException;

    public abstract void delete(Activity u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * Returns a list of <code>FstxActivity</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public abstract List find(String query, String value) throws DAOException;

    public abstract List find(String query) throws DAOException;

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(ActivityFilterVO filter) throws DAOException;
}