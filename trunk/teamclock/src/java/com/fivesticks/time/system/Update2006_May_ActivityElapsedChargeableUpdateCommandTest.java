/*
 * Created on May 10, 2006
 *
 */
package com.fivesticks.time.system;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class Update2006_May_ActivityElapsedChargeableUpdateCommandTest extends
        AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        new Update2006_May_ActivityElapsedChargeableUpdateCommand().execute();
        
    }
}
