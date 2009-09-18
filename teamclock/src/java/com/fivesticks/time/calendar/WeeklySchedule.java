/*
 * Created on Apr 22, 2004
 *
 */
package com.fivesticks.time.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import com.fivesticks.time.settings.broker.SettingsBroker;
import com.fivesticks.time.settings.broker.SettingsBrokerAware;

/**
 * @author Nick
 *  
 */
public class WeeklySchedule implements SettingsBrokerAware, Serializable {

    private SettingsBroker settingsBroker;

    /**
     * @param timeslots
     */
    public WeeklySchedule(Collection timeslots) {
        this.timeslots = new TreeSet();
        this.timeslots.addAll(timeslots);

    }

    private Collection headers;

    private TreeSet timeslots;

    /**
     * @return
     */
    public Collection getTimeslots() {

        return timeslots;
    }

    /**
     * @return
     */
    public Collection getHeaders() {
        if (headers == null) {
            buildHeaderList();
        }
        return headers;
    }

    /**
     *  
     */
    private void buildHeaderList() {
        headers = new ArrayList();

        if (timeslots != null && timeslots.size() > 0) {
            WeeklyScheduleTimeSlot timeslotTemp = (WeeklyScheduleTimeSlot) timeslots
                    .toArray()[0];

            Iterator i = timeslotTemp.getBins().iterator();

            while (i.hasNext()) {
                DailyBin binTemp = (DailyBin) i.next();

                /*
                 * There should be one bin for each day.
                 */
                WeeklyScheduleHeader headerTemp = new WeeklyScheduleHeader(
                        binTemp.getLowerBound());
                headers.add(headerTemp);
            }

        }

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