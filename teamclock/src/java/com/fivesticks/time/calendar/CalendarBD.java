package com.fivesticks.time.calendar;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author REID
 *  
 */
public interface CalendarBD extends CalendarDAOAware, SystemOwnerAware {

    public void delete(TcCalendar target) throws CalendarBDException;

    //adding save() by shuji iida Sep.29
    public TcCalendar save(TcCalendar target)
            throws CalendarBDException;

    public TcCalendar get(Long id) throws CalendarBDException;
    
    public Collection find(CalendarFilterParameters filter) throws CalendarBDException;

}