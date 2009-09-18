/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.common.json;

import org.json.JSONObject;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ObjectToJSONConverterTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        JSONObject o = new ObjectToJSONConverter().convert(this.customer);
        
        assertNotNull(o);
        
        log.info(o.toString(3));
        
    }
}
