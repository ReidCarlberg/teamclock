/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserDAO;
import com.fivesticks.time.systemowner.SystemUserDAOFactory;
import com.fivesticks.time.systemowner.SystemUserSearchParameters;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * 
 * This will delete the SystemOwner object. All the dependents will be deleted
 * with other commands, controled by the executing service delegate.
 * 
 * @author Nick
 */
public class SystemOwnerUsersDeleteCommand implements FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private Object target;

    private SessionContext sessionContext;

    /**
     *  
     */
    public void execute() throws Exception {

        if (target == null) {
            throw new Exception("SystemOwnerDeleteCommand: I need a target.");
        }

        if (!(target instanceof SystemOwner)) {
            throw new Exception(
                    "SystemOwnerDeleteCommand:  I don't know how to handle this target - "
                            + target);
        }

        SystemOwner so = (SystemOwner) this.getTarget();

        /*
         * We need to find all the activities for this systemowner.
         */
        SystemUserSearchParameters filter = new SystemUserSearchParameters();

        filter.setOwnerKey(so.getKey());

        SystemUserDAO dao = SystemUserDAOFactory.factory.build();
        UserBD bd = UserBDFactory.factory.build();

        List elements = dao.search(filter);

        for (Iterator iter = elements.iterator(); iter.hasNext();) {
            SystemUser element = (SystemUser) iter.next();
            User currentUser = bd.getByUsername(element.getUsername());

            /*
             * 2005-01-18 RSC if the user isn't associated with other system
             * owners then we can delete them.
             */

            dao.delete(element);

            Collection otherSystemOwnerAssociations = SystemUserServiceDelegateFactory.factory
                    .build().list(currentUser.getUsername());

            if (otherSystemOwnerAssociations.size() == 0) {
                bd.delete(currentUser);
            }

            //            UserFilterParameters userFilterParameters = new
            // UserFilterParameters();
            //            userFilterParameters.setUsername(element.getUsername());
            //            Collection users = userDAO.find(userFilterParameters);
            //
            //            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            //                User user = (User) iterator.next();
            //// GroupMembershipDAO groupMembershipDAO =
            // GroupMembershipDAO.factory
            //// .build();
            ////
            //// List memberships = groupMembershipDAO.find(user);
            //// for (Iterator iterator2 = memberships.iterator(); iterator2
            //// .hasNext();) {
            //// GroupMembership groupMembership = (GroupMembership) iterator2
            //// .next();
            //// groupMembershipDAO.delete(groupMembership);
            ////
            //// }
            //
            //                userDAO.delete(user);
            //
            //            }

        }

    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}