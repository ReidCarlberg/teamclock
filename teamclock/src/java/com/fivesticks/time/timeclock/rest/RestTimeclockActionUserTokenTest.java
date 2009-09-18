/*
 * Created on Dec 1, 2005
 *
 */
package com.fivesticks.time.timeclock.rest;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractAPIBasedAccessTestCase;
import com.fivesticks.time.timeclock.TimeclockEventEnum;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;
import com.fivesticks.time.ws.xwork.AuthenticationBasedServiceSupport;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

public class RestTimeclockActionUserTokenTest extends AbstractAPIBasedAccessTestCase {



    public void testBasic() throws Exception {

        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_VALIDATION,
                action.getResult());
    }

    public void testSuccess() throws Exception {
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), action.getCurrentStatus());

    }

    public void testSuccessThenSuccess() throws Exception {
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());
        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), action.getCurrentStatus());

        /*
         * then clock out
         */
        Thread.sleep(1000);

        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());
        action.setAction(TimeclockEventEnum.CLOCK_OUT.getName());

        s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_OUT
                .getFriendlyName(), action.getCurrentStatus());

        
        /*
         * then clock in
         */
        Thread.sleep(1000);

        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), action.getCurrentStatus());

        
        /*
         * then clock out
         */
        Thread.sleep(1000);

        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_OUT.getName());

        s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_OUT
                .getFriendlyName(), action.getCurrentStatus());
        
    }

    public void testSuccessThenFailure() throws Exception {
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());
        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), action.getCurrentStatus());

        Thread.sleep(1000);
        /*
         * then try it again and it will fail
         */
        action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_EVENT, action
                .getResult());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), action.getCurrentStatus());

    }
    
    public void testShouldFailNoTokenAccess() throws Exception {
        SystemSettingsServiceDelegateFactory.factory
        .build(this.systemOwner)
        .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, false);
        
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);
        
        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_SETTINGS, action
                .getResult());
    }
    
    public void testShouldFailUserInactive() throws Exception {

        
        UserBDFactory.factory.build().makeInactive(this.user);
        
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());

        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);
        
        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_USER_OR_TOKEN, action
                .getResult());
        
    }
    
    public void testShouldFailTokenReplaced() throws Exception {

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();
        sd.revokeToken(this.getUserToken());
        
        UserBDFactory.factory.build().makeInactive(this.user);
        
        RestTimeclockAction action = new RestTimeclockAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());

        action.setToken(this.getUserToken());
        action.setAction(TimeclockEventEnum.CLOCK_IN.getName());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);
        
        assertEquals(AbstractAuthBasedAuthenticationService.FAILED_USER_OR_TOKEN, action
                .getResult());
        
    }    
}
