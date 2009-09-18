/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.util.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.IdReadable;

/**
 * @author REID
 */
public class SequenceSorter {

    public void resequence(Collection original) {
        int i = 0;
        for (Iterator iter = original.iterator(); iter.hasNext();) {
            i++;
            SequenceAware element = (SequenceAware) iter.next();
            element.setSequence(new Integer(i));
        }        
        
    }
    public Collection reverse(Collection original, IdReadable target) {
        
        //resequence
//        this.resequence(original);
        
        Collection ret = new ArrayList();
        
        SequenceAware last = null;
        SequenceAware current = null;
        SequenceAware swap = null;
        
        boolean found = false;
        boolean currentAdded = false;
        
        for (Iterator iter = original.iterator(); iter.hasNext();) {
            last = current;
            current = (SequenceAware) iter.next();
            currentAdded = false;
            if (((IdReadable) current).getId().longValue() == target.getId().longValue()
                    && last == null) {
                /*
                 * nothing to do.
                 */
                ret = original;
                
                break;
            } else if (!found && ((IdReadable) current).getId().longValue() == target.getId()
                    .longValue()) {
                /*
                 * OK, let's swap the values.
                 */
                Integer lastNumb = last.getSequence();
                
                last.setSequence(current.getSequence());
                
                current.setSequence(lastNumb);
                
                ret.add(current);
                currentAdded = true;
                ret.add(last);
                found = true;
                
            }
            
            if (!found && last != null) {
                ret.add(last);
            } else if (found && !currentAdded) {
                ret.add(current);
            }
            
           
        }        
        
//        ret.add(current);
        
        return ret;
    }
}
