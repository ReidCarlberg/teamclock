/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar;

public class MonthlyCalendarDisplayWrapperBuilder implements
        CalendarDisplayWrapperBuilder {

    public MonthlyCalendarDisplayWrapperBuilder() {
        super();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.calendar.CalendarDisplayWrapperBuilder#build(com.fivesticks.time.calendar.DailyBin)
     */
    public DailyBinDisplayWrapper build(DailyBin current) {
        MonthlyBinDisplayWrapper currentDisplay = new MonthlyBinDisplayWrapper(
                current);

        return currentDisplay;
    }

}
