/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.useradmin.provider;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.common.xwork.AbstractSystemOwnerAwareProvider;
import com.fivesticks.time.useradmin.xwork.UserListBroker;

/**
 * @author Reid
 *  
 */
public class UserListProviderBrokerImpl extends
        AbstractSystemOwnerAwareProvider implements UserListProvider, Serializable {

    private static final long serialVersionUID = 5164007797349767265L;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.provider.UserListProvider#getUsers()
     */
    public Collection getInternalUsersAll() {

        return UserListBroker.singleton.getInternalUserList(this
                .getSystemOwner());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.provider.UserListProvider#getInternalUsersActiveOnly()
     */
    public Collection getInternalUsersActiveOnly() {
        return UserListBroker.singleton.getActiveUserList(this
                .getSystemOwner());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.provider.UserListProvider#getExternalUsers()
     */
    public Collection getExternalUsers() {
        return UserListBroker.singleton.getExternalUserList(this
                .getSystemOwner());
    }

}
