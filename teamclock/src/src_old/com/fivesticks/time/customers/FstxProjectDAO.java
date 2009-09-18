/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customers;

import java.util.List;

import com.fivesticks.time.customer.FstxProject;
import com.fivesticks.time.customer.FstxProjectFilterVO;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface FstxProjectDAO {

    public static final String SPRING_BEAN_NAME = "fstxProjectDAO";

    public static final FstxProjectDAOFactory factory = new FstxProjectDAOFactory();

    public abstract FstxProject save(FstxProject u) throws DAOException;

    public abstract FstxProject get(Long id) throws DAOException;

    public abstract void delete(FstxProject u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    public List find(FstxProjectFilterVO filter) throws DAOException;

    /**
     * Returns a list of <code>FstxProject</code> s using the query specified
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
}