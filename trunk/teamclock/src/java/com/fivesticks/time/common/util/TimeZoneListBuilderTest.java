/*
 * Created on Oct 14, 2005
 *
 */
package com.fivesticks.time.common.util;

import java.util.Collection;

import junit.framework.TestCase;

public class TimeZoneListBuilderTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBasic() throws Exception {
        Collection c = TimeZoneListBuilder.singleton.build();
    }
}
