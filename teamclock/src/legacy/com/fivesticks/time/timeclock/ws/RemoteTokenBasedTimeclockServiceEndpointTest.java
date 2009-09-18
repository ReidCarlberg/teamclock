/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.timeclock.ws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;

public class RemoteTokenBasedTimeclockServiceEndpointTest extends AbstractTimeTestCase {

    private Log log = LogFactory.getLog(RemoteTokenBasedTimeclockServiceEndpointTest.class);
    
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBasic() throws Exception {

        /*
         * Used for status checks.
         */
        
        
        
        SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, true);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();

        String r = endpoint.punchIn(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        Thread.sleep(1000);

        log.info("current status (in): " + endpoint.getStatus(t, user.getUsername()));
        /*
         * 
         */
        r = endpoint.breakStart(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        Thread.sleep(1000);

        log.info("current status (start): " + endpoint.getStatus(t, user.getUsername()));
        /*
         * 
         */
        
        r = endpoint.breakStop(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        Thread.sleep(1000);

        log.info("current status (stop): " + endpoint.getStatus(t, user.getUsername()));
        /*
         * 
         */
        r = endpoint.punchOut(t, user.getUsername());

        log.info("current status (out): " + endpoint.getStatus(t, user.getUsername()));
        
        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);
    }

    public void testFailsEvent() throws Exception {

        SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, true);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();
        String r = endpoint.punchIn(t, user.getUsername());
        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);
        Thread.sleep(1000);
        r = endpoint.punchIn(t, user.getUsername());
        assertTrue(r.startsWith(RemoteTokenBasedTimeclockServiceEndpoint.FAILED_EVENT));

        Thread.sleep(1000);
        r = endpoint.breakStart(t, user.getUsername());
        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        Thread.sleep(1000);
        r = endpoint.breakStart(t, user.getUsername());
        assertTrue(r.startsWith(RemoteTokenBasedTimeclockServiceEndpoint.FAILED_EVENT));

        Thread.sleep(1000);

        r = endpoint.breakStop(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        Thread.sleep(1000);

        r = endpoint.punchOut(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

    }

    public void testShiftStatus() throws Exception {

        SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, true);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();

        String r = endpoint.punchIn(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.SUCCESS, r);

        String status = endpoint.getStatus(t, user.getUsername());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN
                .getFriendlyName(), status);

    }

    public void testFailsDueToSettings() throws Exception {

        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW,
                        false);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();

        String r = endpoint.punchIn(t, user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.FAILED_SETTINGS, r);

    }

    public void testFailsDueBadToken() throws Exception {

        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW,
                        false);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();

        String r = endpoint.punchIn(t + "x", user.getUsername());

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.FAILED_USER_OR_TOKEN, r);

    }

    public void testFailsDueBadUser() throws Exception {

        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW,
                        false);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        String t = sd.generateSystemToken(this.systemOwner);

        RemoteTokenBasedTimeclockServiceEndpoint endpoint = new RemoteTokenBasedTimeclockServiceEndpoint();

        String r = endpoint.punchIn(t, user.getUsername() + "x");

        assertEquals(RemoteTokenBasedTimeclockServiceEndpoint.FAILED_USER_OR_TOKEN, r);

    }

}
