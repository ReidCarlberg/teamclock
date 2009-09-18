/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.calendar;

import java.util.List;

/**
 * @author REID
 *  
 */
public interface CalendarDAO {

    public abstract TcCalendar save(TcCalendar u);

    public abstract TcCalendar get(Long id);

    public abstract void delete(TcCalendar u);

    public abstract void removeFstxCalendar(Long id);

    /**
     * Returns a list of <code>FstxCalendar</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List @
     */
    public abstract List searchFstxCalendars(String query, String value);

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(final CalendarFilterParameters params);
}