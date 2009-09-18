/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.system;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;


/**
 * @author REID
 */
public class Update2006_May_ActivityElapsedChargeableUpdateCommand {

    public void execute() throws Exception {

        String HQL = "update " + Activity.class.getName() +
            " set chargeableElapsed = elapsed";
               
        GenericDAO dao = new GenericDAOFactory().build();
        
        dao.execute(HQL);
        

    }
}