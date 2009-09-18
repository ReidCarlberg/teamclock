/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class LookupTestFactory {

    private static int count;
    
    public static Lookup build(SystemOwner systemOwner, LookupType lookupType) {
        
        count++;
        
        Lookup ret = new Lookup();
        
        ret.setType(lookupType.getName());
        ret.setShortName("short name " + count);
        ret.setFullName("full name " + count);
        ret.setOwnerKey(systemOwner.getKey());
        
        GenericDAO dao = GenericDAOFactory.factory.build();
        
        dao.save(ret);
        
        return ret;
        
    }
}
