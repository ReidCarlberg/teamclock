/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * Builds for display purposes.
 * 
 * @author REID
 */
public class UserCollectionBuilder {

    public Collection build(SystemOwner systemOwner)
            throws UserServiceDelegateException {
        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();
        Collection ret = new ArrayList();
        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            UserListKeyValue current = new UserListKeyValue();
            current.setKey(element.getUser().getUsername());
            if (element.getUser().isBooleanInactive()) {
                current.setValue(element.getUser().getUsername()
                        + " (Inactive)");
            } else {
                current.setValue(element.getUser().getUsername());
            }
            ret.add(current);

        }
        return ret;

    }

    public Collection buildInternal(SystemOwner systemOwner)
            throws UserServiceDelegateException {
        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();
        Collection ret = new ArrayList();
        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            if (element.getUserTypeEnum() != UserTypeEnum.EXTERNAL) {
                UserListKeyValue current = new UserListKeyValue();
                current.setKey(element.getUser().getUsername());
                if (element.getUser().isBooleanInactive()) {
                    current.setValue(element.getUser().getUsername()
                            + " (Inactive)");
                } else {
                    current.setValue(element.getUser().getUsername());
                }
                ret.add(current);
            }
        }
        return ret;

    }

    public Collection buildAsUserTypeVO(SystemOwner systemOwner)
            throws UserServiceDelegateException {
        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();
        return raw;

    }

    public Collection buildActiveOnly(SystemOwner systemOwner)
            throws UserServiceDelegateException {
        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();
        Collection ret = new ArrayList();
        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            if (!element.getUser().isBooleanInactive()) {
                UserListKeyValue current = new UserListKeyValue();
                current.setKey(element.getUser().getUsername());
                current.setValue(element.getUser().getUsername());
                ret.add(current);
            }

        }
        return ret;

    }

    public Collection buildInternalActiveOnlyAsUserAndTypeVO(
            SystemOwner systemOwner) throws UserServiceDelegateException {
        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();
        Collection ret = new ArrayList();
        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            if (!element.getUser().isBooleanInactive()
                    && element.getUserTypeEnum() != UserTypeEnum.EXTERNAL) {
                ret.add(element);
            }

        }
        return ret;

    }

    public Collection buildExternal(SystemOwner systemOwner)
            throws UserServiceDelegateException {

        Collection raw = UserServiceDelegateFactory.factory.build(systemOwner)
                .listUserAndType();

        Collection ret = new ArrayList();

        for (Iterator iter = raw.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            if (element.getUserTypeEnum() == UserTypeEnum.EXTERNAL) {

                if (!element.getUser().isBooleanInactive()) {
                    UserListKeyValue current = new UserListKeyValue();
                    current.setKey(element.getUser().getUsername());
                    current.setValue(element.getUser().getUsername());
                    if (element.getUser().isBooleanInactive()) {
                        current.setValue(current.getValue() + " (Inactive)");
                    }
                    ret.add(current);
                }
            }

        }
        return ret;

    }
}