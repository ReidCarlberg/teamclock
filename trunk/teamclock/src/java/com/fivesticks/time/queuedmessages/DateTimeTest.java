/*
 * Created on May 1, 2006
 *
 */
package com.fivesticks.time.queuedmessages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class DateTimeTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        
        
        
        Date javaDate = new Date();
        log.info("Java Date is " + javaDate);
        
        DateTime jodaDate = new DateTime(DateTimeZone.UTC);
        log.info("Joda date is " + jodaDate.getMillis());
        
        log.info("Javaified Joda date is " + jodaDate.toDate());
        
        Calendar c = GregorianCalendar.getInstance(TimeZone.getTimeZone("Zulu"));
        log.info(SimpleDateFormat.getDateTimeInstance().format(c.getTime()));
    }

    protected void setUp() throws Exception {
        
        super.setUp();
        TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));
    }
}
