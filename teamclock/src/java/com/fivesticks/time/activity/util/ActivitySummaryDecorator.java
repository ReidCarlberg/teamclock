/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.activity.util;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.activity.AbstractActivitySummary;
import com.fivesticks.time.activity.ActivitySummaryByLabel;

public class ActivitySummaryDecorator {

    public void decorate(Collection summaries, double totalShiftHours) {
        
        
        
        double totalElapsedTime = 0.0;
        double totalElapsedChargeableTime = 0.0;
        double totalPercent = 0.0;
        int totalCount = 0;
        
        for (Iterator iter = summaries.iterator(); iter.hasNext();) {
            AbstractActivitySummary element = (AbstractActivitySummary) iter.next();
        
            double currentPercent = element.getElapsed().doubleValue() / totalShiftHours; 
                
            element.setElapsedPercentOfShiftTime(currentPercent);
            
            totalPercent += currentPercent;
            
            double currentPercentChargeable = element.getElapsedChargeable().doubleValue() / totalShiftHours;
            
            element.setElapsedChargeablePercentOfShiftTime(currentPercentChargeable);
            
            
            totalElapsedTime += element.getElapsed().doubleValue();
            totalElapsedChargeableTime += element.getElapsedChargeable().doubleValue();
            totalCount += element.getCount().intValue();
        }
        
        ActivitySummaryByLabel totals = new ActivitySummaryByLabel();
        totals.setLabel("Total");
        totals.setCount(new Long(totalCount));
        totals.setElapsed(new Double(totalElapsedTime));
        totals.setElapsedChargeable(new Double(totalElapsedChargeableTime));
        totals.setElapsedPercentOfShiftTime(totalPercent);
        
        summaries.add(totals);
        
        
    }
}
