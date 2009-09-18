/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.testutil;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;

public class AbstractAPIBasedAccessTestCase extends AbstractTimeTestCase {

    protected String token;
    
    protected String userToken;

    protected void setUp() throws Exception {
        super.setUp();

        SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, true);

        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();

        token = sd.generateSystemToken(this.systemOwner);
        
        userToken = sd.generateUserToken(this.user);
        
    }

    /**
     * @return Returns the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return Returns the userToken.
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * @param userToken The userToken to set.
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
