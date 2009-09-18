/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.events;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author REID
 */
public class EventHandlerCommand {

//    private AuthenticationEventHandler authenticationEventHandler;
//
//    private ToDoEventHandler toDoEventHandler;
//    
//    private ExternalUserEventHandler externalUserEventHandler;
//    
//    private UseradminEventHandler useradminEventHandler;
//    
//    private SettingsEventHandler settingsEventHandler;

//    public void execute() {
//
//        /*
//         * reid 2005-03-19 factored this out.
//         */
////        if (!GeneralEventQueue.singleton.hasEvents())
////            return;
////
////        Collection eventsToHandle = GeneralEventQueue.singleton.getQueue();
////
////        for (Iterator iter = eventsToHandle.iterator(); iter.hasNext();) {
////            GeneralEvent element = (GeneralEvent) iter.next();
////
////            try {
////                sendEvents(element);
////            } catch (EventHandlerException e) {
////                e.printStackTrace();
////            }
////        }
//        
//        try {
//            processEvents();
//        } catch (EventHandlerException e) {
//            e.printStackTrace();
//        }
//
//    }
//
////    /**
////     * @param element
////     */
////    private void sendEvents(GeneralEvent element) throws EventHandlerException {
////        
////        /*
////         * 2005-03-06 Update
////         */
////        
////        EventChannel channel = EventChannelBroker.singleton.getChannel(element.getGeneralEventType());
////        try {
////            channel.send(element);
////        } catch (EventChannelException e) {
////            throw new EventHandlerException(e);
////        }
////    }
    
    /*
     * modified to simply process the different channels.
     */
    public  void execute() throws EventHandlerException {
        Collection eventChannels = EventChannelBroker.singleton.getChannelKeys();
        
        for (Iterator iterator = eventChannels.iterator(); iterator.hasNext();) {
            Object element =  iterator.next();
            EventChannel channel = EventChannelBroker.singleton.getChannel(element);
            try {
                channel.process();
            } catch (EventChannelException e) {
                throw new EventHandlerException(e);
            }
        }        
    }


}