/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.events;

import com.fivesticks.time.events.GeneralEventQueue;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class UseradminEventPublisher {

    public static final UseradminEventPublisher singleton = new UseradminEventPublisher();

//    private Collection listeners = new ArrayList();

    public void publishUserCreatedEvent(SystemOwner systemOwner,
            User originatingUser, User newUser, UserTypeEnum userTypeEnum) {

        UseradminEventType useradminEventType = UseradminEventType.CREATED;

        if (originatingUser == null) {
            useradminEventType = UseradminEventType.CREATE_WITHOUT_ORGINATING_USER;
        }
        handlePublish(systemOwner, originatingUser, newUser, userTypeEnum,
                useradminEventType);
    }

    public void publishUserTypeChangedEvent(SystemOwner systemOwner,
            User originatingUser, User affectedUser, UserTypeEnum userTypeEnum) {

        handlePublish(systemOwner, originatingUser, affectedUser, userTypeEnum,
                UseradminEventType.TYPECHANGED);
    }

    public void publishUserActivatedEvent(SystemOwner systemOwner,
            User originatingUser, User affectedUser) {

        handlePublish(systemOwner, originatingUser, affectedUser, null,
                UseradminEventType.ACTIVATED);
    }

    public void publishUserDeactivatedEvent(SystemOwner systemOwner,
            User originatingUser, User affectedUser) {

        handlePublish(systemOwner, originatingUser, affectedUser, null,
                UseradminEventType.DEACTIVATED);
    }

    public void publishUserDeletedEvent(SystemOwner systemOwner,
            User originatingUser, User affectedUser) {

        handlePublish(systemOwner, originatingUser, affectedUser, null,
                UseradminEventType.DEACTIVATED);
    }

    public void publishUserPasswordChangedEvent(SystemOwner systemOwner,
            User originatingUser, User affectedUser) {
        
        handlePublish(systemOwner, originatingUser, affectedUser, null, UseradminEventType.PASSWORDCHANGED);
        
    }
    private void handlePublish(SystemOwner systemOwner, User originatingUser,
            User affectedUser, UserTypeEnum userTypeEnum,
            UseradminEventType useradminEventType) {

        UseradminEventImpl event = new UseradminEventImpl();

        event.setNewUser(affectedUser);
        event.setSystemOwner(systemOwner);
        event.setUser(originatingUser);
        event.setUserTypeEnum(userTypeEnum);
        event.setUseradminEventType(useradminEventType);

        GeneralEventQueue.singleton.add(event);
    }

}