/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.useradmin.provider;

import java.util.Collection;

/**
 * @author Reid
 *
 */
public interface UserListProvider {

    public Collection getInternalUsersAll();
    
    public Collection getInternalUsersActiveOnly();
    
    public Collection getExternalUsers();
    
}
