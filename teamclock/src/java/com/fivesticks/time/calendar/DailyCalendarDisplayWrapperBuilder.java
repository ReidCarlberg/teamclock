/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar;

public class DailyCalendarDisplayWrapperBuilder implements
        CalendarDisplayWrapperBuilder {

    public DailyCalendarDisplayWrapperBuilder() {
        super();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.calendar.CalendarDisplayWrapperBuilder#build(com.fivesticks.time.calendar.DailyBin)
     */
    public DailyBinDisplayWrapper build(DailyBin current) {
        DailyBinDisplayWrapper currentDisplay = new DailyBinDisplayWrapper(
                current);

        return currentDisplay;
    }

}
