/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class WebFormServiceDelegateImplTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        WebForm test1 = WebFormTestFactory.getPersisted(this.sessionContext);
        
        assertNotNull(test1);
        
        assertNotNull(test1.getId());

        assertNotNull(test1.getKey());
        
        assertNotNull(test1.getCreateDate());
        
        assertNotNull(test1.getModifyDate());
        
        log.info("Sample key is " + test1.getKey());
        
    }
    
    public void testGetByKeyAndId() throws Exception {
        
        WebForm test1 = WebFormTestFactory.getPersisted(this.sessionContext);
        WebForm test2 = WebFormTestFactory.getPersisted(this.sessionContext);
        WebForm test3 = WebFormTestFactory.getPersisted(this.sessionContext);
        
        WebForm test1a = WebFormServiceDelegateFactory.factory.build(this.sessionContext).get(test1.getId());
        
        assertNotNull(test1a);
        
        assertEquals(test1.getId(),test1a.getId());
        
        
        WebForm test2a = WebFormServiceDelegateFactory.factory.build(this.sessionContext).get(test2.getKey());
        
        assertNotNull(test2a);
        
        assertEquals(test2.getId(),test2a.getId());
        
    }
    
    public void testGetByKeyShouldFail() throws Exception {
        
        WebForm test2 = WebFormTestFactory.getPersisted(this.sessionContext);
        String badKey = test2.getKey() + "a";
     
        try {
            WebForm test2a = WebFormServiceDelegateFactory.factory.build(this.sessionContext).get(badKey);
            assertTrue(false);
        } catch (Exception e) {}
    }
}
