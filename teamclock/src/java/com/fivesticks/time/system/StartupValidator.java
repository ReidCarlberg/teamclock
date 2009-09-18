/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 *  
 */
public class StartupValidator {

    private static Log log = LogFactory.getLog(StartupValidator.class);

    private String validationFailure;

    public boolean validate() {
        boolean ret = true;

        log.info("starting...");
        try {
            new DatabaseConnectionValidator().validate();
        } catch (DatabaseConnectionValidatorException e) {
            log.warn("failed in Database Connection Validator");
            SystemHealthMonitor.singleton
                    .getCurrentIssues()
                    .add(
                            "Couldn't connect to the database.  Make sure you have the right URL, username and password.");
            return false;
        }

        log.info("updating the database schema...");
        try {
            new DatabaseSchemaUpdater().update();
        } catch (DatabaseSchemaUpdaterException e) {
            log.warn("failed in Database Updater");
            SystemHealthMonitor.singleton.getCurrentIssues().add(
                    "Couldn't update the database.");
            return false;
        }

        log.info("making sure we have some foundation data...");
        try {
            new DatabaseFoundationDataValidator().validate();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("failed in foundation data validator");
            SystemHealthMonitor.singleton.getCurrentIssues().add(
                    "Didn't have and couldn't add any foundation data.");
        }

        log
                .info("now checking to see if we have any post update startup tasks to take care of...");
        try {
            new DatabaseContentUpdateChecker().update();
        } catch (Exception e) {
            log.warn("failed in the content udpate checker...");
            SystemHealthMonitor.singleton
                    .getCurrentIssues()
                    .add(
                            "Couldn't update some of the data we really needed to.  Call Five Sticks. " + e.getMessage());
            e.printStackTrace();
        }

        log.info("completing without incident...");

        return ret;
    }

    /**
     * @return
     */
    public String getValidationFailure() {
        return validationFailure;
    }

    /**
     * @param string
     */
    public void setValidationFailure(String string) {
        validationFailure = string;
    }

}