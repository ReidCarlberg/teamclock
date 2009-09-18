/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.activity.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.testutil.AbstractAPIBasedAccessTestCase;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;
import com.fivesticks.time.ws.xwork.AuthenticationBasedServiceSupport;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

public class RestActivityActionTest extends AbstractAPIBasedAccessTestCase {

    public void testBasicFails() throws Exception {

        RestActivityAction action = new RestActivityAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_VALIDATION,
                action.getResult());
    }

    public void testBasicSucceeds() throws Exception {

        DateFormat sdf = SimpleDateFormat.getDateTimeInstance();
        
        SimpleDate startTime = SimpleDate.factory.build();
        startTime.setHours(11);
        startTime.setMinutes(0);

        log.info("Start time SDF'd is " + sdf.format(startTime.getDate()));
        Date refDate = startTime.getDate();

        /*
         * before action
         */
        Collection before = ActivityBDFactory.factory.build(
                this.sessionContext).getDaysActivityForUser(user.getUsername(),
                refDate);

        RestActivityAction action = new RestActivityAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.token);
        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword);
        action.setAction(ActivityRestActionType.POST_COMPLETE.getName());

        action.setProjectKey(this.project.getShortName());
        action.setStartTime(sdf.format(startTime.getDate()));

        startTime.setHours(12);
        action.setStopTime(sdf.format(startTime.getDate()));

        action.setComments("here are some comments");

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);
        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());
        
        /*
         * before action
         */
        Collection after = ActivityBDFactory.factory.build(
                this.sessionContext).getDaysActivityForUser(user.getUsername(),
                refDate);
        
        assertTrue(before.size() + 1 == after.size());
    }

}
