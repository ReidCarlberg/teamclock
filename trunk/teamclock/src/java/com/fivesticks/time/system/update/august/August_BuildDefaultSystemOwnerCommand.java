/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.system.update.august;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyGenerator;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class August_BuildDefaultSystemOwnerCommand {

    private SystemOwner systemOwner;

    /**
     *  
     */
    public void execute() throws Exception {

        SystemOwner newOwner = handleSystemOwner();

        this.setSystemOwner(newOwner);

        handleDefaultSystemOwnerUser();

    }

    /**
     *  
     */
    private void handleDefaultSystemOwnerUser() throws Exception {

        User user = UserBDFactory.factory.build().addUser("newadmin", "newadmin",
                "newadmin@notareal.domain");

        //        UserGroupBD bd = UserGroupBD.factory.build();
        //
        //        UserGroup ownerAdminGroup = bd
        //                .getByName(UserTypeEnum.USERTYPE_OWNERADMIN.getName());
        //
        //        bd.join(user, ownerAdminGroup);

        SystemUserServiceDelegateFactory.factory.build().associate(
                this.getSystemOwner(), user, UserTypeEnum.OWNERADMIN);
    }

    /**
     * @return
     */
    private SystemOwner handleSystemOwner() throws Exception {

        SystemOwner systemOwner = new SystemOwner();

        systemOwner.setActivated(true);
        systemOwner.setName("Default System Owner");
        systemOwner.setAddress1("Default Address");
        systemOwner.setCity("City");
        systemOwner.setState("State");
        systemOwner.setPostalCode("PostaslCode");
        systemOwner.setCountry("Country");

        systemOwner.setContactName("Contact Name");
        systemOwner.setContactPhone("Contact Phone");
        systemOwner.setContactEmail("Here is the email.");

        systemOwner.setKey(SystemOwnerKeyGenerator.singleton.getGeneratedKey());

        return SystemOwnerServiceDelegateFactory.factory.build().save(systemOwner);
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}