/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author REID
 */
public class ContactLastNameComparator implements Comparator, Serializable {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        
        int ret = 0;
        
        CustomerAndContactVO one = (CustomerAndContactVO) arg0;
        
        CustomerAndContactVO two = (CustomerAndContactVO) arg1;
        
        if (one.getContact() == null ){
            ret = -1;
            
        } else if (two.getContact() == null) {
            ret = 1;
        } else {
	        ret = one.getContact().getNameLast().compareToIgnoreCase(two.getContact().getNameLast());
	        
	        if (ret == 0) {
	            ret = one.getContact().getNameFirst().compareToIgnoreCase(two.getContact().getNameFirst());            
	        }
	        
	        if (ret == 0) {
	            ret = one.getContact().getId().compareTo(two.getContact().getId());
	        }
        }
        return ret;
    }

}
