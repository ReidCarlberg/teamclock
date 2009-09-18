/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.events;

import com.fivesticks.time.events.AbstractGeneralEvent;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class UseradminEventImpl extends AbstractGeneralEvent implements UseradminEvent {

    private GeneralEventType generalEventType = GeneralEventType.USER_EVENT;

    private UseradminEventType useradminEventType;

    //the user that created the new user
    private User user;

//    private SystemOwner systemOwner;

    private User newUser;

    private UserTypeEnum userTypeEnum;

    /**
     *  
     */
    public UseradminEventImpl() {
        super();
    }

    public UseradminEventImpl(UseradminEventType useradminEventType, User user) {
        super();
        this.useradminEventType = useradminEventType;
        this.user = user;
    }

    public UseradminEventImpl(UseradminEventType useradminEventType, User user,
            SystemOwner systemOwner) {
        super();
        this.useradminEventType = useradminEventType;
        this.user = user;
        this.setSystemOwner( systemOwner);
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Returns the useradminEventType.
     */
    public UseradminEventType getUseradminEventType() {
        return useradminEventType;
    }

    /**
     * @param useradminEventType
     *            The useradminEventType to set.
     */
    public void setUseradminEventType(UseradminEventType useradminEventType) {
        this.useradminEventType = useradminEventType;
    }



    /**
     * @return Returns the newUser.
     */
    public User getAffectedUser() {
        return newUser;
    }

    /**
     * @param newUser
     *            The newUser to set.
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    /**
     * @return Returns the userTypeEnum.
     */
    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    /**
     * @param userTypeEnum
     *            The userTypeEnum to set.
     */
    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }

    /**
     * @return Returns the generalEventType.
     */
    public GeneralEventType getGeneralEventType() {
        return generalEventType;
    }

}