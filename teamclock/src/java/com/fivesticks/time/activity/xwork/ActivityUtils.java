/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import java.text.DecimalFormat;
import java.util.Date;

import com.fivesticks.time.activity.Activity;

public class ActivityUtils {

    public static Double getElapsed(Date start, Date stop) {

        Double newd = null;

        if (stop != null) {
            long t1 = stop.getTime();
            long t2 = start.getTime();
            double res = (double) t1 - t2;
            double cal = res / 3600000;
            DecimalFormat df = new DecimalFormat("#,##0.000");
            Double d = new Double(cal);
            String f = df.format(d);
            newd = new Double(f);
        }
        return newd;
    }
    
    public static void decorateWithElapsed(Activity activity) {
        activity.setElapsed(getElapsed(activity.getStart(), activity.getStop()));
    }
    
    public static void decorateWithElapsedChargeable(Activity activity) {
        activity.setChargeableElapsed(getElapsed(activity.getStart(), activity.getStop()));
    }

}
