/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.config.DatabaseContentInitializer;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 *  
 */
public class DatabaseFoundationDataValidator {

    private static Log log = LogFactory
            .getLog(DatabaseFoundationDataValidator.class);

    /**
     *  
     */
    public void validate() throws Exception {

        //Collection users = UserDAO.factory.build().find(UserDAO.SELECT_ALL);
        
        Collection users = UserBDFactory.factory.build().getAll();

        
        
        
        /**
         * the only way we'd have a user collection size 0 is if we have no
         * data.
         * 
         * so we're going to add some data real quick like.
         */
        if (users.size() == 0) {
            log.info("No users found, so we'll add a couple...");
            //new SetupBasicData();
            new DatabaseContentInitializer().initialize(1);
        } else {
            log.info("Some user found, so we should be good to go...");
        }

    }

}