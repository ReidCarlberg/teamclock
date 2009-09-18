/*
 * Created on Apr 22, 2004
 *
 */
package com.fivesticks.time.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.broker.SettingsBroker;
import com.fivesticks.time.settings.broker.SettingsBrokerAware;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * This class groups bins by timeslot over a week. In other works it contains
 * all of the bins for 7:00 sorted by day.
 * 
 * *Assumptions it only contains bin of the same timeslot. Several propertied
 * are determined by looking at the first bin in the collection.
 * 
 * @author Nick
 *  
 */
public class WeeklyScheduleTimeSlot implements SettingsBrokerAware, Comparable {
    private TreeSet bins = new TreeSet();

    private Collection displayableBins = null;

    private SettingsBroker settingsBroker;

    /**
     *  
     */
    public WeeklyScheduleTimeSlot() {
        super();
    }

    /**
     * @param currentBin
     */
    public void addBin(DailyBin currentBin) {
        bins.add(currentBin);

    }

    /**
     * @return
     */
    public TreeSet getBins() {
        return bins;
    }

    public Collection getDisplayableBins() {
        if (displayableBins == null) {
            displayableBins = new ArrayList();
            Iterator i = bins.iterator();

            while (i.hasNext()) {
                DailyBin current = (DailyBin) i.next();
                DailyBinDisplayWrapper currentDisplay = new DailyBinDisplayWrapper(
                        current);
                //2005-11-17 RSC
//                currentDisplay.setSettingsBroker(this.getSettingsBroker());
                displayableBins.add(currentDisplay);
            }
        }

        return displayableBins;
    }

    /*
     * This will be the label for the side of the table.
     */
    public String getName() {
        Collection displayableBins = getDisplayableBins();
        if (displayableBins != null && displayableBins.size() > 0) {
            DailyBinDisplayWrapper wrapper = (DailyBinDisplayWrapper) displayableBins
                    .toArray()[0];
            return wrapper.getBinLower();
        }
        return "NA";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object arg0) {
        if (!(arg0 instanceof WeeklyScheduleTimeSlot)) {
            throw new RuntimeException(
                    "Invalid class in WeeklyScheduleTimeSlot compareTo: "
                            + arg0.getClass());
        }
        WeeklyScheduleTimeSlot test = (WeeklyScheduleTimeSlot) arg0;

        int ret = this.getCompareToHourTime().compareTo(
                test.getCompareToHourTime());
        if (ret == 0) {
            ret = -1;
        }
        return ret;
    }

    /**
     * This gets the 24 hour time as a double. This is used by the compare to
     * method
     */
    private Double getCompareToHourTime() {
        if (bins != null && bins.size() > 0) {
            DailyBin bin = (DailyBin) bins.toArray()[0];
            SimpleDate simple = SimpleDate.factory.build(bin.getLowerBound());
            int hourOfDay = simple.getCalendar().get(Calendar.HOUR_OF_DAY);
            int minute = simple.getCalendar().get(Calendar.MINUTE);

            double time = ((double) (hourOfDay +  minute)) / 60.0;
            return new Double(time);
        }
        return new Double(0.0);
    }

    public boolean isRegularDay(FstxTimeSettings settings) {
        Collection displayableBins = getDisplayableBins();
        if (displayableBins != null && displayableBins.size() > 0) {
            DailyBinDisplayWrapper wrapper = (DailyBinDisplayWrapper) displayableBins
                    .toArray()[0];

            //RSC-2005-11-17
//            wrapper.setSettingsBroker(this.getSettingsBroker());
            return wrapper.isRegularDay(settings);
        }
        return false;
    }

    public boolean isToday() {
        boolean ret = false;

        //		wrapper

        return ret;
    }

    /**
     * @return Returns the settingsBroker.
     */
    public SettingsBroker getSettingsBroker() {
        return settingsBroker;
    }

    /**
     * @param settingsBroker
     *            The settingsBroker to set.
     */
    public void setSettingsBroker(SettingsBroker settingsBroker) {
        this.settingsBroker = settingsBroker;
    }
}