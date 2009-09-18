/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.activity;

import java.util.Date;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ActivityBDImplTestPort extends AbstractTimeTestCase {

    public void testBasicPost() throws Exception {

        ActivityBD bd = ActivityBDFactory.factory.build(sessionContext);

        Activity activity = bd.post(this.user, this.project, new Date(),
                new Date(), this.task, "comments");

        assertNotNull(activity);

        assertNotNull(activity.getId());

        assertTrue(activity.getElapsed().doubleValue() == 0.0);
    }

    public void testPostWithElapsedMinutes() throws Exception {

        SimpleDate start = SimpleDate.factory.build();
        start.setHours(11);
        start.setMinutes(0);

        SimpleDate stop = SimpleDate.factory.build();
        stop.setHours(12);
        stop.setMinutes(0);

        ActivityBD bd = ActivityBDFactory.factory.build(sessionContext);

        Activity activity = bd.post(this.user, this.project, start
                .getDate(), stop.getDate(), this.task, "comments");

        assertNotNull(activity);

        assertNotNull(activity.getId());

        log.info("Elapsed is " + activity.getElapsed());

        /*
         * elapsed is in hours.
         */
        assertTrue(activity.getElapsed().doubleValue() == 1.0);

    }

    public void testInvalidParameters() throws Exception {
        ActivityBD bd = ActivityBDFactory.factory.build(sessionContext);

        try {
            Activity activity = bd.post(null, this.project, new Date(),
                    new Date(), this.task, "comments");
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }
        
        try {
            Activity activity = bd.post(this.user, null, new Date(),
                    new Date(), this.task, "comments");
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }

        
        try {
            Activity activity = bd.post(this.user, this.project, null,
                    new Date(), this.task, "comments");
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }
        
        try {
            Activity activity = bd.post(this.user, this.project, new Date(),
                    null, this.task, "comments");
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }
        
        try {
            Activity activity = bd.post(this.user, this.project, new Date(),
                    new Date(), null, "comments");
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }
        
        try {
            Activity activity = bd.post(this.user, this.project, new Date(),
                    new Date(), this.task, null);
            assertTrue(false);
        } catch (Exception e) {
            // great!
        }
        
    }
}
