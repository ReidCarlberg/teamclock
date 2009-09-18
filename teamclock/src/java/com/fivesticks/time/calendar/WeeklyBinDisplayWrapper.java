/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar;

import com.fstx.stdlib.common.simpledate.SimpleDate;
/*
 * This is used for the weekly view of the dashboard.  
 * It depends on the ViewDashboardCalendarAJAX
 * createing the wrapper with builder = ScheduleBuilder.factory.buildWeekly(
                    this.getSystemOwner(), filter, 24*60);
                    
     this keeps the bins in 24 hour increments.  Since we look at the 
     labels directly, if that changes this will have to change.
                    
 */
public class WeeklyBinDisplayWrapper extends DailyBinDisplayWrapper {

    public WeeklyBinDisplayWrapper(final DailyBin bin) {
        super(bin);

    }

    public String getBinLower() {
        String[] days = { "", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday" };

        SimpleDate date = SimpleDate.factory.build(this.getBin()
                .getLowerBound());

        return days[date.getDayOfWeek()] + " " + monthDayFormat.format(date.getDate());// + "<br/>" + getTimeDescription();
    }


    public boolean isRegularDay() {

        
       return true;
    }

}
