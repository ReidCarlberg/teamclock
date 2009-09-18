/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ContactV2ModifySettingsActionTest extends AbstractTimeTestCase {

    Lookup lu1;

    Lookup lu2;

    Lookup lu3;


    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.testutil.AbstractTimeTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

        lu1 = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);
        lu2 = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);
        lu3 = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);
    }
}
