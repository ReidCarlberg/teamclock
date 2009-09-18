/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface UserBuilder {

    public User createTimeclockOnly(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException;

    public User createStandardUser(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException;

    public User createPriviledgedUser(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException;

    public User createOwnerAdministratorUser(SystemOwner systemOwner,
            String username, String password, String email)
            throws UserBuilderException;

}