/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 *
 */
public class EmployeeTestFactory {

    private static int counter;
    
    public static Employee getUnpersisted(SystemOwner systemOwner, User user) {
    
        counter++;
        
        Employee ret = new Employee();
        
        ret.setCode("code" + counter);
        ret.setNameFirst("first" + counter);
        ret.setNameLast("last" + counter);
        ret.setOwnerKey(systemOwner.getKey());
        ret.setUsername(user.getUsername());
        ret.setHourlyRate(new Double(5.0 + counter));
        
        return ret;
        
    }
    
    public static Employee getPersisted(SystemOwner systemOwner, User user) throws Exception {
        
        Employee ret = getUnpersisted(systemOwner, user);
        
        EmployeeServiceDelegateFactory.factory.build(systemOwner).save(ret);
        
        return ret;
    }

}
