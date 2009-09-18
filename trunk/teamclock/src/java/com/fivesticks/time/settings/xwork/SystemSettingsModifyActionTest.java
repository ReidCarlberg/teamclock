/*
 * Created on Dec 13, 2005
 *
 */
package com.fivesticks.time.settings.xwork;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class SystemSettingsModifyActionTest extends AbstractTimeTestCase {

public void testBasic() throws Exception {
        
        SystemSettingsModifyAction action = new SystemSettingsModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setSettings(sessionContext.getSettings());
        action.getSettings().setSmtpHost("updated");
        action.setSubmitSettings("submit");
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,s);
        
        assertEquals(SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner).getSettingAsString(
                        SettingTypeEnum.SETTING_SMTP_HOST), "updated");
    }}
