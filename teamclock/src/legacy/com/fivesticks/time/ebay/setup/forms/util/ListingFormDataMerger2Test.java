/*
 * Created on Jun 25, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 */
public class ListingFormDataMerger2Test extends TestCase {

    Log log = LogFactory.getLog(ListingFormDataMerger2Test.class);
    
    public void testReplaceAll() throws Exception {
        
        String start = "1 2 3 4 5 6";
    
        
        
        String replace = "here is a $dollar 12345";
        
        replace = replace.replaceAll("\\$","_dollar_");
        
        log.info(replace);
        
        String updates = start.replaceAll("3", replace);
        
        log.info(updates);
        
        updates = updates.replaceAll("_dollar_", "\\$");
        
        log.info(updates);
        
        
    }
}
