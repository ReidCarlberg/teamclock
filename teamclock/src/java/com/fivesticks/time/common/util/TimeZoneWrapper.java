/*
 * Created on Oct 5, 2005
 *
 * 
 */
package com.fivesticks.time.common.util;

import java.util.TimeZone;

public class TimeZoneWrapper {

    private TimeZone timeZone;

    public TimeZoneWrapper(TimeZone tz) {
        super();
        this.timeZone = tz;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getId() {
        if (this.timeZone == null)
            return "";
        
        return this.timeZone.getID();

    }

    public String getDisplayNameFull() {
        if (this.timeZone == null)
            return "----";
        
        return this.timeZone.getDisplayName(true, TimeZone.LONG) + " "
                + this.getOffset() + " (" + this.getId() + ")";

    }

    public String getDisplayName() {
        if (this.timeZone == null)
            return "----";
        
        return this.timeZone.getDisplayName(true, TimeZone.LONG);

    }

    public String getOffset() {
        
        if (this.timeZone == null)
            return "";
        
        StringBuffer buffer = new StringBuffer();

        buffer.append("GMT");

        double d = (double) this.timeZone.getRawOffset() / 1000.0 / 60.0 / 60.0;
        if (d >= 0) {
            buffer.append("+");

        }
        double floor = Math.floor(d);
        buffer.append((int) floor);

        if ((d - floor) == .5) {
            buffer.append(":30");

        } else {
            buffer.append(":00");

        }

        return buffer.toString();
    }

    public String getSortKey() {
        
        /*
         * Prioritize These timezones.
         */
        if (this.getDisplayName().indexOf("Pacific Daylight Time") > -1) {
            return 1 + this.getDisplayName();

        }
        if (this.getDisplayName().indexOf("Central Daylight Time") > -1) {
            return 1 + this.getDisplayName();

        }
        if (this.getDisplayName().indexOf("Eastern Daylight Time") > -1) {
            return 1 + this.getDisplayName();

        }
        if (this.getDisplayName().indexOf("Eastern Standard Time") > -1) {
            return 1 + this.getDisplayName();

        }
        if (this.getDisplayName().indexOf("Mountain Daylight Time") > -1) {
            return 1 + this.getDisplayName();

        }
        if (this.getDisplayName().indexOf("Mountain Standard Time") > -1) {
            return 1 + this.getDisplayName();

        }

        return this.getDisplayName();

    }
}
