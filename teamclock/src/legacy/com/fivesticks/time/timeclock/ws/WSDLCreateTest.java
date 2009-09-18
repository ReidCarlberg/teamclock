/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.timeclock.ws;

import junit.framework.TestCase;

import org.apache.axis.wsdl.Java2WSDL;

public class WSDLCreateTest extends TestCase {

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        
        super.setUp();
    }
    
    public void testBasic() throws Exception {


            String args[] = { "-ltest.wsdl",
                    "com.fivesticks.time.timeclock.ws.RemoteTimeclockService" };

            String argsBlank[] = {};
//            WSDL2Java.main(args);

            Java2WSDL.main(args);
        
    }


}
