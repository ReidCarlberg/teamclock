/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.Collection;
import java.util.HashMap;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.events.UseradminEventImpl;

/**
 * @author REID
 */
public class UserListBroker  {

    public static final UserListBroker singleton = new UserListBroker();

    private HashMap userLists = new HashMap();

    private HashMap activeUserLists = new HashMap();

    private HashMap externalUserLists = new HashMap();

    protected UserListBroker() {

    }

    public synchronized Collection getUserList(SystemOwner systemOwner) {

        Collection ret = (Collection) userLists.get(systemOwner);

        if (ret == null) {
            Collection users;
            try {
                users = new UserCollectionBuilder().build(systemOwner);
            } catch (UserServiceDelegateException e) {
                throw new RuntimeException("Couldn't build user list.");
            }
            userLists.put(systemOwner, users);

            ret = users;
        }

        return ret;
    }

    public synchronized Collection getInternalUserList(SystemOwner systemOwner) {

        Collection ret = (Collection) userLists.get(systemOwner);

        if (ret == null) {
            Collection users;
            try {
                users = new UserCollectionBuilder().buildInternal(systemOwner);
            } catch (UserServiceDelegateException e) {
                throw new RuntimeException("Couldn't build user list.");
            }
            userLists.put(systemOwner, users);

            ret = users;
        }

        return ret;
    }

    public synchronized Collection getActiveUserList(SystemOwner systemOwner) {

        Collection ret = (Collection) activeUserLists.get(systemOwner);

        if (ret == null) {
            Collection users;
            try {
                users = new UserCollectionBuilder()
                        .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);
            } catch (UserServiceDelegateException e) {
                throw new RuntimeException("Couldn't build user list." + e);
            }
            activeUserLists.put(systemOwner, users);

            ret = users;
        }

        return ret;
    }

    public synchronized Collection getExternalUserList(SystemOwner systemOwner) {

        Collection ret = (Collection) externalUserLists.get(systemOwner);

        if (ret == null) {
            Collection users;
            try {
                users = new UserCollectionBuilder().buildExternal(systemOwner);
            } catch (UserServiceDelegateException e) {
                throw new RuntimeException("Couldn't build user list.");
            }
            externalUserLists.put(systemOwner, users);

            ret = users;
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UseradminEventListener#notify(com.fivesticks.time.useradmin.UseradminEvent)
     */
    public synchronized void notify(UseradminEventImpl useradminEvent) {

        SystemOwner targetOwner = useradminEvent.getSystemOwner();

        if (targetOwner == null) {
            try {
                targetOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                        useradminEvent.getUser());
            } catch (SystemOwnerServiceDelegateException e) {
                throw new RuntimeException(e);
            }
        }
        notifyOfChange(targetOwner);
    }

    public synchronized void notifyOfChange(SystemOwner targetOwner) {
        userLists.remove(targetOwner);
        activeUserLists.remove(targetOwner);
        externalUserLists.remove(targetOwner);
    }

}