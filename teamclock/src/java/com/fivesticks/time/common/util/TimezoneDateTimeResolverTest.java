/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.common.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class TimezoneDateTimeResolverTest extends TestCase {

//    private Log log = LogFactory.getLog(TimezoneDateTimeResolver.class);

    // public void testZones() throws Exception {
    //
    // String central = "US Central";
    // String pacific = "US Pacific";
    // String mountain = "US Mountain";
    // String eastern = "US Eastern";
    //
    // SimpleDate current = SimpleDate.factory.build();
    // current.setHours(16);
    //        
    // SimpleDate toCentral = SimpleDate.factory.build();
    // toCentral.setHours(16);
    //        
    // SimpleDate toPacific = SimpleDate.factory.build();
    // toPacific.setHours(16);
    //        
    // SimpleDate toMountain = SimpleDate.factory.build();
    // toMountain.setHours(16);
    //        
    // SimpleDate toEastern = SimpleDate.factory.build();
    // toEastern.setHours(16);
    //
    // SimpleDate centralResolved = TimezoneDateTimeResolver.resolve(
    // toCentral, central);
    // SimpleDate pacificResolved = TimezoneDateTimeResolver.resolve(
    // toPacific, pacific);
    // SimpleDate mountainResolved = TimezoneDateTimeResolver.resolve(
    // toMountain, mountain);
    // SimpleDate easternResolved = TimezoneDateTimeResolver.resolve(
    // toEastern, eastern);
    //
    // assertTrue(centralResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY));
    //
    // assertTrue(pacificResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY) - 2);
    //
    // assertTrue(mountainResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY) - 1);
    //
    // assertTrue(easternResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY) + 1);
    //
    // }
    //
    // public void testZonesFromZero() throws Exception {
    //
    // String central = "US Central";
    // String pacific = "US Pacific";
    // String mountain = "US Mountain";
    // String eastern = "US Eastern";
    //
    // SimpleDate current = SimpleDate.factory.build();
    // current.setHours(0);
    //        
    // SimpleDate toCentral = SimpleDate.factory.build();
    // toCentral.setHours(0);
    //        
    // SimpleDate toPacific = SimpleDate.factory.build();
    // toPacific.setHours(0);
    //        
    // SimpleDate toMountain = SimpleDate.factory.build();
    // toMountain.setHours(0);
    //        
    // SimpleDate toEastern = SimpleDate.factory.build();
    // toEastern.setHours(0);
    //
    // SimpleDate centralResolved = TimezoneDateTimeResolver.resolve(
    // toCentral, central);
    // SimpleDate pacificResolved = TimezoneDateTimeResolver.resolve(
    // toPacific, pacific);
    // // SimpleDate mountainResolved = TimezoneDateTimeResolver.resolve(
    // // toMountain, mountain);
    // // SimpleDate easternResolved = TimezoneDateTimeResolver.resolve(
    // // toEastern, eastern);
    //
    // assertTrue(centralResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY));
    //
    // current.advanceHour(-2);
    // log.info("current hour of day " + (current
    // .getCalendar().get(Calendar.HOUR_OF_DAY)));
    // log.info("pacific resolved " +
    // pacificResolved.getCalendar().get(Calendar.HOUR_OF_DAY));
    //        
    //        
    // assertTrue(pacificResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // .getCalendar().get(Calendar.HOUR_OF_DAY));
    //
    // // assertTrue(mountainResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // // .getCalendar().get(Calendar.HOUR_OF_DAY) - 1);
    // //
    // // assertTrue(easternResolved.getCalendar().get(Calendar.HOUR_OF_DAY) ==
    // current
    // // .getCalendar().get(Calendar.HOUR_OF_DAY) + 1);
    //
    // }
    //    

    public void testGMTNotDSTCentralDaylightTime() {
       
        
        SimpleDate current = SimpleDate.factory.build();
        current.setHours(0);
        current.setDay(1);
        current.setMonth(1);

        SimpleDate centralResolved = TimezoneDateTimeResolver.resolveStatic(current,
                "America/Chicago");

        assertEquals(6, centralResolved.getCalendar().get(Calendar.HOUR));

    }

    /*
     * Chicago is in Central Daylight Time, so they do adjust for DST
     */
    public void testGMTwithDSTCentralDaylightTime() {
        SimpleDate currentDST = SimpleDate.factory.build();
        currentDST.setHours(0);
        currentDST.setDay(5);
        currentDST.setMonth(5);

        SimpleDate centralResolved = TimezoneDateTimeResolver.resolveStatic(
                currentDST, "America/Chicago");

        assertEquals(7, centralResolved.getCalendar().get(Calendar.HOUR));

    }

    public void testGMTNotDSTCentralStandardTime() {
        SimpleDate current = SimpleDate.factory.build();
        current.setHours(0);
        current.setDay(1);
        current.setMonth(1);

        SimpleDate centralResolved = TimezoneDateTimeResolver.resolveStatic(current,
                "America/Managua");

        assertEquals(6, centralResolved.getCalendar().get(Calendar.HOUR));

    }

    /*
     * Belize is in Central Standard Time so they don't adjust DST
     */
    public void testGMTwithDSTCentralStandardTime() {
        SimpleDate currentDST = SimpleDate.factory.build();
        currentDST.setHours(0);
        currentDST.setDay(5);
        currentDST.setMonth(5);

        SimpleDate centralResolved = TimezoneDateTimeResolver.resolveStatic(
                currentDST, "America/Managua");

        assertEquals(6, centralResolved.getCalendar().get(Calendar.HOUR));

    }

    
    public void resolveLocalServerTimeToGMT0()
    {
      System.out.println(new Date());
       TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));
  System.out.println(new Date());
        
        Locale l = Locale.getDefault();
        
       TimeZone tz = TimeZone.getDefault();
       
       
       System.out.println(tz.getID());
    }
    
    
public void testGMTTestShowTZ()
    {
        
  Collection sorted = TimeZoneListBuilder.singleton.build();

        
        
     System.out.println(sorted.size());
       
     
    
     for (Iterator iter = sorted.iterator(); iter.hasNext();) {
            TimeZoneWrapper tz = (TimeZoneWrapper) iter.next();
    
           
    System.out.println( tz.getDisplayNameFull());
           
        }
        
        
         
    }}