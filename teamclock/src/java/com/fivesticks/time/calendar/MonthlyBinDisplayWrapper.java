/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar;

/*
 * This is used for the weekly view of the dashboard.  
 * It depends on the ViewDashboardCalendarAJAX
 * createing the wrapper with builder = ScheduleBuilder.factory.buildWeekly(
                    this.getSystemOwner(), filter, 6*60);
                    
     this keeps the bins in 6 hour increments.  Since we look at the 
     labels directly, if that changes this will have to change.
                    
 */
public class MonthlyBinDisplayWrapper extends DailyBinDisplayWrapper {

    public MonthlyBinDisplayWrapper(final DailyBin bin) {
        super(bin);

    }

  

}
