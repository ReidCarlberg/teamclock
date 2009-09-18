/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.util.legacy;

import com.fivesticks.time.common.IdReadable;

/**
 * @author REID
 */
public class MockSequence implements SequenceAware, IdReadable {

    private static int testCount;
    
    
    private Long id;
    
    private Integer sequence;
    
    public static MockSequence getTest() {
        testCount++;
        
        MockSequence ret = new MockSequence();
        
        ret.setId(new Long(testCount));
        ret.setSequence(new Integer(testCount));
        
        return ret;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
