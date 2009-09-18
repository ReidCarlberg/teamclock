package com.fivesticks.time.calendar;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID modified by shuji Sep. 23
 *  
 */
public class CalendarBDImpl implements CalendarBD {

    private CalendarDAO fstxCalendarDAO;

    private SystemOwner systemOwner;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.calendar.FstxCalendarBD#save(com.fivesticks.time.calendar.FstxCalendar)
     */
    public TcCalendar save(TcCalendar target)
            throws CalendarBDException {
        if (target.getId() == null)
            decorate(target);
        else
            validate(target);

        try {
            return getFstxCalendarDAO().save(target);
        } catch (Exception e) {
            throw new CalendarBDException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.calendar.FstxCalendarBD#delete(com.fstx.time.calendar.FstxCalendar)
     */
    public void delete(TcCalendar target) throws CalendarBDException {
        validate(target);
        this.getFstxCalendarDAO().delete(target);

    }

    public TcCalendar get(Long id) throws CalendarBDException {
        TcCalendar fc = this.getFstxCalendarDAO().get(id);
        validate(fc);
        return fc;

    }

    /**
     * @return
     */
    public CalendarDAO getFstxCalendarDAO() {
        return fstxCalendarDAO;
    }

    /**
     * @param calendarDAO
     */
    public void setFstxCalendarDAO(CalendarDAO calendarDAO) {
        fstxCalendarDAO = calendarDAO;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    private void validate(TcCalendar fstxCalendar)
            throws CalendarBDException {
        if (!fstxCalendar.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new CalendarBDException(
                    "Couldn't validate that the calendar in question belongs to the current SystemOwner");
        }
    }

    private void decorate(TcCalendar fstxCalendar) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No system owner to decorate with.");
        }
        fstxCalendar.setOwnerKey(this.getSystemOwner().getKey());
    }

    public Collection find(CalendarFilterParameters filter) throws CalendarBDException {
        
        this.decorate(filter);
        
        return this.getFstxCalendarDAO().find(filter);
    }
}