/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.system.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.systemowner.RequiresUpdateAccountReasonType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * finds soon to be expiring demo accounts and flags them as needing
 * an account update.
 * 
 * @author Reid
 */
public class FlagDemosForAccountUpdateCommand {

    private Log log = LogFactory.getLog(FlagDemosForAccountUpdateCommand.class);
    
    public void execute() throws Exception {
   
        //setup a simple date
        SimpleDate sd = SimpleDate.factory.buildEndOfDay();
        sd.advanceDay(5);
        
        //setup a simple date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        
        
        StringBuffer hql = new StringBuffer();
        
        hql.append( "update " + SystemOwner.class.getName() + " set " );
        hql.append( " requiresAccountUpdate = true, ");
        hql.append(" requiresAccountUpdateReason = '" + RequiresUpdateAccountReasonType.DEMO_OVER.getName() + "'");
        hql.append(" where account = 'Demo' and ");
        hql.append("expirationDate <= '" + sdf.format(sd.getDate()) +"'");
        
        
        log.info(hql.toString());
        
        int affected = GenericDAOFactory.factory.build().execute(hql.toString());
        
        log.info("Affected " + affected); 
        
        log.info("\n\n\n\n\n**************Flag Frmos For Account Update Command" + new Date() + "\n\n\n\n\n\n");
        
    }

}
