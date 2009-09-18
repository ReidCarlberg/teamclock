/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 *  
 */
public interface TimeclockDAO {

    public static final String SEARCH_SELECT_ALL = "from timeclock in class com.fivesticks.time.timeclock.FstxTimeclock where timeclock.id > 0 order by timeclock.id";

    public abstract Timeclock save(Timeclock u) throws DAOException;

    public abstract Timeclock get(Long id) throws DAOException;

    public abstract void remove(Timeclock u) throws DAOException;

    public abstract void remove(Long id) throws DAOException;

    /**
     * Returns a list of <code>FstxCalendar</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public abstract List search(String query, String value) throws DAOException;

    /**
     * @param filter
     * @return
     */
    public abstract Collection find(TimeclockFilterParameters filter)
            throws DAOException;
}