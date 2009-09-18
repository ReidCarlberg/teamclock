/*
 * Created on Oct 5, 2005
 *
 * 
 */
package com.fivesticks.time.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;
import java.util.TreeSet;


public class TimeZoneListBuilder {

    public static TimeZoneListBuilder singleton = new TimeZoneListBuilder();

//    private static Log log = LogFactory.getLog(TimeZoneListBuilder.class);
    
    private TimeZoneListBuilder() {
        super();

    }

    public Collection build() {
        TreeSet sorted = new TreeSet(new FstxSorter().getAscending(
                "sortKey", "id"));

        String s[] = TimeZone.getAvailableIDs();
        for (int i = 0; i < s.length; i++) {
            String string = s[i];

            //log.info("string: " + string);
            
            TimeZone tz = TimeZone.getTimeZone(string);

            TimeZoneWrapper tzw = new TimeZoneWrapper(tz);
            sorted.add(tzw);
        }
        
        Collection r = new ArrayList();
        
        r.add(new TimeZoneWrapper(TimeZone.getTimeZone("America/New_York")));
        r.add(new TimeZoneWrapper(TimeZone.getTimeZone("America/Chicago")));
        r.add(new TimeZoneWrapper(TimeZone.getTimeZone("America/Phoenix")));
        r.add(new TimeZoneWrapper(TimeZone.getTimeZone("America/Los_Angeles")));
        
        r.add(new TimeZoneWrapper(null));
        r.addAll(sorted);

    
        
        
        return r;
    }

}
