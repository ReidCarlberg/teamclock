/*
 * Created on Jun 1, 2006
 *
 */
package com.fivesticks.util.html.legacy;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpecialCharacterUtilTest extends TestCase {

    private Log log = LogFactory.getLog(SpecialCharacterUtil.class);
    
    SpecialCharacterUtil util;
    
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        
        super.setUp();
        util = new SpecialCharacterUtil();
    }

    public void testBasic() throws Exception {
        
        
        
        String r = util.encode("one&two");
        
        assertEquals("one&amp;two",r);
        
    }
    
    public void testEncoding() throws Exception {
        
        String r = util.encode("reid's car");
        assertEquals("reid&apos;s car",r);
        
        r = util.encode("\"love\"");
        assertEquals("&quot;love&quot;",r);        
        
        r = util.encode("one < two");
        assertEquals("one &lt; two",r);
        
        r = util.encode("three > two");
        assertEquals("three &gt; two",r);
        
        r = util.encode("one & one");
        assertEquals("one &amp; one",r);
        
        r = util.encode("one & one<three");
        assertEquals("one &amp; one&lt;three",r);      
        
    }
    
    public void testSkipsEncodedChars() throws Exception {
        String r = util.encode("reid&apos;s car");
        assertEquals("reid&apos;s car",r);        
        
    }
    
    
}
