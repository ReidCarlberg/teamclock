/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.system.fivesticks;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextFactory;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupCriteriaParameters;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/*
 * this will be faster than doing it manually.
 */
public class CleanUpHiddenCustomersCommand {

    private Log log = LogFactory.getLog(CleanUpHiddenCustomersCommand.class);
    
    public void execute(SystemOwner fivesticks) throws Exception {
        
//        SystemOwner fivesticks = SystemOwnerServiceDelegateFactory.factory.build().get("CXZASTPKGU");
//        if (fivesticks == null) {
//            throw new RuntimeException("couldn't find FiveSticks");
//        }
        
        User reid = UserBDFactory.factory.build().getByUsername("reid");
        SessionContext s = SessionContextFactory.factory.build(reid, fivesticks);
        
        GenericDAO dao = GenericDAOFactory.factory.build();

        LookupCriteriaParameters p = new LookupCriteriaParameters();
        p.setType(LookupType.CUSTOMER_STATUS.getName());
        p.setShortName("ebay");
       
        Collection lookups = LookupServiceDelegateFactory.factory.build(fivesticks).search(p);
        if (lookups.size() != 1) {
            log.info("lookups size was " + lookups.size());
            throw new RuntimeException("didn't find the ebay lookup");
        }
        Lookup l = (Lookup) lookups.toArray()[0];

        
        String hql = " from " + Customer.class.getName() + " where ownerKey = :key and "
            + " hidden = true and" +
                    " (status = " + l.getId() + " or status is null)";
    
        log.info("hql is " + hql);
        
        CustomerServiceDelegate csd = CustomerServiceDelegateFactory.factory.build(s);
        
        Collection c = dao.find(hql, fivesticks);
        
        log.info("found " + c.size() + " hidden customers");
        

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();
            
            log.info("Attempting to delete customer... " + element.getName());
            csd.delete(element);
            
            
        }
    }
}
