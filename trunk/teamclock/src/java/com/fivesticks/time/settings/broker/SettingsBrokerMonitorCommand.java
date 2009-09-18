/*
 * Created on Jan 29, 2005 by REID
 */
package com.fivesticks.time.settings.broker;

/**
 * Runs every so often to evict stale session issues.
 * 
 * @author REID
 */
public class SettingsBrokerMonitorCommand {

    public void execute() {
        MasterSettingsBroker.singleton.resetBroker();
    }
}