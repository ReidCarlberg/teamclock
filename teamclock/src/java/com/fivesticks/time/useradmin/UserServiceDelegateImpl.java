/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.common.TransactionManagerAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.events.UseradminEventPublisher;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDAware;
import com.fstx.stdlib.authen.users.UserBDException;

/**
 * @author Reid
 */
public class UserServiceDelegateImpl implements UserServiceDelegate,
        TransactionManagerAware, UserBDAware {

    private SystemOwner systemOwner;

    private User originatingUser;

    private PlatformTransactionManager transactionManager;

    private UserBD userBD;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserServiceDelegate#list()
     */
    public Collection listUserAndType() throws UserServiceDelegateException {

        SystemUserServiceDelegate systemUserSd = SystemUserServiceDelegateFactory.factory
                .build();

        Collection raw;
        try {
            raw = systemUserSd.list(this.getSystemOwner());
        } catch (SystemUserServiceDelegateException e) {

            throw new UserServiceDelegateException(e);
        }

        Collection processed = new ArrayList();

        UserAndTypeVOBuilder voBuilder = new UserAndTypeVOBuilder();

        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            SystemUser element = (SystemUser) iter.next();

            User current;
            try {
                current = this.getUserBD().getByUsername(element.getUsername());
            } catch (UserBDException e1) {

                throw new UserServiceDelegateException(e1);
            }
            UserAndTypeVO built = voBuilder.build(this.getSystemOwner(),
                    current);

            processed.add(built);
        }

        return processed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerAware#setSystemOwner(com.fivesticks.time.systemowner.SystemOwner)
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public SystemOwner getSystemOwner() {
        return this.systemOwner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserServiceDelegate#updateUserType(com.fstx.stdlib.authen.users.User,
     *      com.fivesticks.time.useradmin.UserTypeEnum)
     */
    public void updateUserType(final User user,
            final UserTypeEnum oldUserTypeEnum,
            final UserTypeEnum newUserTypeEnum)
            throws UserServiceDelegateException {

        TransactionTemplate transactionTemplate = new TransactionTemplate(this
                .getTransactionManager());

        transactionTemplate
                .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    try {
                        handleUpdateUserType(user, oldUserTypeEnum,
                                newUserTypeEnum);
                    } catch (SystemUserServiceDelegateException e) {

                        throw new RuntimeException(e);
                    }

                }
            });
        } catch (RuntimeException e) {
            throw new UserServiceDelegateException(e);
        }
        /*
         * just to make sure they don't have any stale rights. 2004-11-25 Change
         * this to a listener.
         */
        //        AuthorizationManager.singleton.removeAuthorizationBean(user
        //                .getUsername());
        UseradminEventPublisher.singleton.publishUserTypeChangedEvent(this
                .getSystemOwner(), this.getOriginatingUser(), user,
                newUserTypeEnum);

    }

    private void handleUpdateUserType(User user, UserTypeEnum oldUserTypeEnum,
            UserTypeEnum newUserTypeEnum)
            throws SystemUserServiceDelegateException {

        SystemUserServiceDelegate sd = SystemUserServiceDelegateFactory.factory
                .build();
        SystemUser c = sd.getBySystemAndUser(this.getSystemOwner(), user);
        c.setUserType(newUserTypeEnum.getName());
        sd.save(c);

    }

    /**
     * @return
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * @param manager
     */
    public void setTransactionManager(PlatformTransactionManager manager) {
        transactionManager = manager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserServiceDelegate#createNewUser(java.lang.String,
     *      java.lang.String, java.lang.String,
     *      com.fivesticks.time.useradmin.UserTypeEnum)
     */
    public User createNewUser(String username, String password, String email,
            UserTypeEnum userTypeEnum) throws UserServiceDelegateException {

        User user = null;

        try {
            user = this.getUserBD().addUser(username, password, email);
        } catch (UserBDException e) {
            throw new UserServiceDelegateException(e);
        }

        try {
            SystemUserServiceDelegateFactory.factory.build().associate(
                    this.getSystemOwner(), user, userTypeEnum);
        } catch (SystemUserServiceDelegateException e3) {

            throw new UserServiceDelegateException(e3);
        }

        UseradminEventPublisher.singleton.publishUserCreatedEvent(this
                .getSystemOwner(), this.getOriginatingUser(), user,
                userTypeEnum);

        return user;
    }

    /**
     * @return Returns the userBD.
     */
    public UserBD getUserBD() {
        return userBD;
    }

    /**
     * @param userBD
     *            The userBD to set.
     */
    public void setUserBD(UserBD userBD) {
        this.userBD = userBD;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserServiceDelegate#makeInactive(java.lang.String)
     */
    public User makeInactive(String username)
            throws UserServiceDelegateException {

        User user;
        try {
            user = this.getUserBD().getByUsername(username);
        } catch (UserBDException e) {

            throw new UserServiceDelegateException(e);
        }
        UserTypeEnum type;
        try {
            type = new UserTypeDeterminator().getUserType(
                    this.getSystemOwner(), user);
        } catch (UserTypeDeterminatorException e2) {
            throw new UserServiceDelegateException(e2);
        }
        if (type == UserTypeEnum.OWNERADMIN) {
            throw new UserServiceDelegateException(
                    "Can't make an owner admin inactive.  Switch to another type first.");
        }

        try {
            user = this.getUserBD().makeInactive(user);
        } catch (UserBDException e1) {
            throw new UserServiceDelegateException(e1);
        }
        UseradminEventPublisher.singleton.publishUserDeactivatedEvent(this
                .getSystemOwner(), this.getOriginatingUser(), user);
        return user;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserServiceDelegate#makeActive(java.lang.String)
     */
    public User makeActive(String username) throws UserServiceDelegateException {
        User user;
        try {
            user = this.getUserBD().getByUsername(username);
        } catch (UserBDException e) {

            throw new UserServiceDelegateException(e);
        }
        try {
            user = this.getUserBD().makeActive(user);
        } catch (UserBDException e1) {
            throw new UserServiceDelegateException(e1);
        }
        UseradminEventPublisher.singleton.publishUserActivatedEvent(this
                .getSystemOwner(), this.getOriginatingUser(), user);
        return user;
    }

    /**
     * @return Returns the originatingUser.
     */
    public User getOriginatingUser() {
        return originatingUser;
    }

    /**
     * @param originatingUser
     *            The originatingUser to set.
     */
    public void setOriginatingUser(User originatingUser) {
        this.originatingUser = originatingUser;
    }
}