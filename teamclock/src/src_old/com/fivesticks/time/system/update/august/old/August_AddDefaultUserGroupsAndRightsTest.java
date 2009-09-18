/*
 * Created on Aug 14, 2004 by Reid
 */
package com.fivesticks.time.system.update.august.old;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.TestSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.UserGroup;
import com.fstx.stdlib.authen.users.UserGroupBD;

/**
 * @author Reid
 */
public class August_AddDefaultUserGroupsAndRightsTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        TestSettings.initializeTestSystemWithMySQLDump(
                "time-drop-2004-08-13.txt", "time-data-2004-08-13.txt");
    }

    public void testCommand() throws Exception {

        UserGroup userGroup = UserGroupBD.factory.build().getByName(
                UserTypeEnum.USERTYPE_TIMECLOCK.getName());

        assertTrue(userGroup == null);

        August_AddDefaultUserGroupsAndRights command = new August_AddDefaultUserGroupsAndRights();

        command.execute();

        userGroup = UserGroupBD.factory.build().getByName(
                UserTypeEnum.USERTYPE_TIMECLOCK.getName());

        assertTrue(userGroup != null);

    }

}