/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.events;

import com.fivesticks.time.authen.events.AuthenticationEventListener;
import com.fivesticks.time.externaluser.events.ExternalUserEventListener;
import com.fivesticks.time.settings.event.SettingsEventListener;
import com.fivesticks.time.todo.events.ToDoEventListener;
import com.fivesticks.time.useradmin.events.UseradminEventListener;

/**
 * @author Reid
 */
public class EventChannelInitializer {

    public void initialize() {
        
        EventChannelBroker.singleton.clearChannels();
        
        EventChannelBroker.singleton.getChannel(
                GeneralEventType.AUTHENTICATION_EVENT).subscribe(
                new AuthenticationEventListener());

        EventChannelBroker.singleton.getChannel(
                GeneralEventType.EXTERNAL_USER_EVENT).subscribe(
                new ExternalUserEventListener());

        EventChannelBroker.singleton
                .getChannel(GeneralEventType.SETTINGS_EVENT).subscribe(
                        new SettingsEventListener());

        EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT)
                .subscribe(new ToDoEventListener());

        EventChannelBroker.singleton.getChannel(GeneralEventType.USER_EVENT)
                .subscribe(new UseradminEventListener());
    }
}