/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.config;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyGenerator;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Reid
 */
public class DatabaseContent_IntializeSystemOwnerCommand {

    private static int ownerCount = 0;

    private SystemOwner systemOwner;

    private int myId = ++ownerCount;

    /**
     *  
     */
    public void execute() throws Exception {
        SystemOwner newOwner = handleSystemOwner();

        this.setSystemOwner(newOwner);

        //        handleDefaultSystemOwnerUser();

    }

//    /**
//     *  
//     */
//    private void handleDefaultSystemOwnerUser() throws Exception {
//
//        User user = UserBD.factory.build().addUser("newadmin", "newadmin",
//                "newadmin@notareal.domain");
//
//        SystemUserServiceDelegate.factory.build().associate(
//                this.getSystemOwner(), user, UserTypeEnum.OWNERADMIN);
//        //        UserGroupBD bd = UserGroupBD.factory.build();
//        //
//        //        UserGroup ownerAdminGroup = bd
//        //                .getByName(UserTypeEnum.USERTYPE_OWNERADMIN.getName());
//        //
//        //        bd.join(user, ownerAdminGroup);
//    }

    /**
     * @return
     */
    private SystemOwner handleSystemOwner() throws Exception {

        SystemOwner systemOwner = new SystemOwner();

        systemOwner.setActivated(true);
        systemOwner.setName("Default System Owner" + myId);
        systemOwner.setAddress1("Default Address" + myId);
        systemOwner.setCity("City" + myId);
        systemOwner.setState("State" + myId);
        systemOwner.setPostalCode("PostaslCode" + myId);
        systemOwner.setCountry("Country" + myId);

        systemOwner.setContactName("Contact Name" + myId);
        systemOwner.setContactPhone("Contact Phone" + myId);
        systemOwner.setContactEmail("Here is the email." + myId);

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