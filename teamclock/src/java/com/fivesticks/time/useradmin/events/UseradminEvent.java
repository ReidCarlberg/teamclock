/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.useradmin.events;

import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface UseradminEvent extends GeneralEvent {

    public UseradminEventType getUseradminEventType();

    public UserTypeEnum getUserTypeEnum();

    public User getAffectedUser();

}