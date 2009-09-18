/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar;

public class WeeklyCalendarDisplayWrapperBuilder implements
        CalendarDisplayWrapperBuilder {

    public WeeklyCalendarDisplayWrapperBuilder() {
        super();

    }

    public DailyBinDisplayWrapper build(DailyBin current) {
        WeeklyBinDisplayWrapper currentDisplay = new WeeklyBinDisplayWrapper(
                current);

        return currentDisplay;
    }

}
