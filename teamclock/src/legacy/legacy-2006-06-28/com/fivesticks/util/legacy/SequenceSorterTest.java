/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.util.legacy;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author REID
 */
public class SequenceSorterTest extends TestCase {
    
    Log log = LogFactory.getLog(SequenceSorterTest.class);
    
    public void testSort() throws Exception {
        
        MockSequence s1 = MockSequence.getTest();
        MockSequence s2 = MockSequence.getTest();
        MockSequence s3 = MockSequence.getTest();
        
        Collection before = new ArrayList();
        before.add(s1);before.add(s2);before.add(s3);
        
        SequenceSorter sorter = new SequenceSorter();
        Collection after = sorter.reverse(before, s3);
        
        assertTrue(after != null);
        log.info("size " + after.size());
        assertTrue(after.size() == 3);
        assertTrue(after.toArray()[2] == s2);
        assertTrue(after.toArray()[1] == s3);
        
    }
    
    public void testSortSecond() throws Exception {
        
        MockSequence s1 = MockSequence.getTest();
        MockSequence s2 = MockSequence.getTest();
        MockSequence s3 = MockSequence.getTest();
        
        Collection before = new ArrayList();
        before.add(s1);before.add(s2);before.add(s3);
        
        SequenceSorter sorter = new SequenceSorter();
        Collection after = sorter.reverse(before, s2);
        
        assertTrue(after != null);
        log.info("size " + after.size());
        assertTrue(after.size() == 3);
        assertTrue(after.toArray()[2] == s3);
        assertTrue(after.toArray()[0] == s2);
        assertTrue(after.toArray()[1] == s1);
        
    }    
}
