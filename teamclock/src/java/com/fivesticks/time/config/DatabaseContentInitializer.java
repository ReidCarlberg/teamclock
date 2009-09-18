/*
 * Created on Aug 27, 2004 by Reid
 */
package com.fivesticks.time.config;

import com.fivesticks.time.systemowner.DefaultSystemOwnerBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class DatabaseContentInitializer {

    public void initialize(int quantityOwners) throws Exception {

        for (int i = 0; i < quantityOwners; i++) {
            handleInitialize(i);
        }

    }

    /**
     *  
     */
    private void handleInitialize(int ownerNumber) throws Exception {

        SystemOwner currentOwner = null;

        try {
            DatabaseContent_IntializeSystemOwnerCommand command = new DatabaseContent_IntializeSystemOwnerCommand();
            command.execute();
            currentOwner = command.getSystemOwner();
        } catch (Exception e) {
            throw e;
        }

        /*
         * 8/31/2004 -- reid -- this is for the default system owner -- the
         * system owner that has rights to use the default application.
         */
        {
            DatabaseContent_ValidateVersionSettingsCommand command = new DatabaseContent_ValidateVersionSettingsCommand();
            command.setIterationNumber(ownerNumber);
            command.execute(currentOwner);

        }

        /*
         * 8/31/2004 -- reid -- this is the default system owner that represents
         * system wide preferences
         */
        new DatabaseContent_ValidateVersionSettingsCommand()
                .execute(DefaultSystemOwnerBroker.getDefaultSystemOwner());

        try {
            DatabaseContent_InstallDefaultUsersAndGroupsCommand command = new DatabaseContent_InstallDefaultUsersAndGroupsCommand();
            command.setSystemOwner(currentOwner);
            command.setIterationNumber(ownerNumber);
            command.execute(true);
        } catch (Exception e) {
            throw e;
        }

        try {
            DatabaseContent_InstallDefaultCustomerProjectTaskCommand command = new DatabaseContent_InstallDefaultCustomerProjectTaskCommand();
            command.setDefaultOwner(currentOwner);
            command.setIterationNumber(ownerNumber);
            command.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}