/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 *  
 */
public class SystemSettingsDaoTest extends TestCase {

    /**
     * Constructor for SystemSettingsDaoTest.
     * 
     * @param arg0
     */
    public SystemSettingsDaoTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testFindSettingByKey() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        SystemSettings one = new SystemSettings();
        one.setSettingKey(SettingTypeEnum.SETTING_LOGO_URL.getName());
        one.setSetting("URL");
        one.setOwnerKey(owner.getKey());
        
        SystemSettingsDao dao = SystemSettingsDaoFactory.factory.build();

        dao.save(one);

        assertTrue(one.getId() != null);

    }

}