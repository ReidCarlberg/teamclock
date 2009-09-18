/*
 * Created on Jan 23, 2005 by REID
 */
package com.fivesticks.time.settings.broker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.systemowner.DefaultSystemOwnerBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class MasterSettingsBroker {

    public static final MasterSettingsBroker singleton = new MasterSettingsBroker();

    private HashMap ownerSettings;

    private HashMap ownerRights;

    private FstxTimeSettings defaultSettings;

    protected MasterSettingsBroker() {
        this.resetBroker();
    };

    /**
     * @return Returns the ownerSettings.
     */
    private HashMap getOwnerSettings() {
        return ownerSettings;
    }

    /**
     * @param ownerSettings
     *            The ownerSettings to set.
     */
    private void setOwnerSettings(HashMap ownerSettings) {
        this.ownerSettings = ownerSettings;
    }

    public synchronized FstxTimeSettings getSettings(SystemOwner systemOwner, User user) {
        
        UserSystemOwner key = new UserSystemOwner(user, systemOwner);
        
        FstxTimeSettings ret = null;

        //ret = (FstxTimeSettings) this.getOwnerSettings().get(systemOwner);
        ret = (FstxTimeSettings) this.getOwnerSettings().get(key);

        if (ret == null && systemOwner != null) {
            ret = new UserSystemSettingsWrapperBuilder().build(user, systemOwner);
//            ret = new FstxTimeSettingsInitializer().initialize(systemOwner);
            this.getOwnerSettings().put(key, ret);
        }

        if (ret == null) {
            return this.getDefaultSettings();
        }

        return ret;
    }
    
    public synchronized UserSystemSettingsWrapper getUserSettings(SystemOwner systemOwner, User user) {
        
        UserSystemOwner key = new UserSystemOwner(user, systemOwner);
        
        UserSystemSettingsWrapper ret = null;

        //ret = (FstxTimeSettings) this.getOwnerSettings().get(systemOwner);
        ret = (UserSystemSettingsWrapper) this.getOwnerSettings().get(key);

        if (ret == null && systemOwner != null) {
            ret = new UserSystemSettingsWrapperBuilder().build(user, systemOwner);
//            ret = new FstxTimeSettingsInitializer().initialize(systemOwner);
            this.getOwnerSettings().put(key, ret);
        }

        if (ret == null) {
            return (UserSystemSettingsWrapper) this.getDefaultSettings();
        }

        return ret;
    }

    public synchronized FstxTimeSettings getSettings() {
        return this.getDefaultSettings();
    }

    public synchronized SystemRights getRights(SystemOwner systemOwner) {
        SystemRights ret = null;

        ret = (SystemRights) this.getOwnerRights().get(systemOwner);

        if (ret == null) {
            ret = new SettingsInitializer(systemOwner).getRights(systemOwner);
            this.getOwnerRights().put(systemOwner, ret);
        }

        return ret;
    }

    public synchronized void removeSettings(SystemOwner systemOwner) {
        this.getOwnerSettings().remove(systemOwner);
        
        Collection c = this.getOwnerSettings().keySet();
        
        Collection toRemove = new ArrayList();
        
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            
            Object element = (Object) iter.next();
            
            if (element instanceof UserSystemOwner) {
                UserSystemOwner userSystemOwner = (UserSystemOwner) element;
                if (userSystemOwner.getSystemOwner().getKey().equals(systemOwner.getKey())) {
                    toRemove.add(userSystemOwner);
                }
            }
            
        }
        
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            UserSystemOwner element = (UserSystemOwner) iter.next();
            this.getOwnerSettings().remove(element);
        }
        this.getOwnerRights().remove(systemOwner);
    }

    public synchronized void resetBroker() {
        this.setOwnerSettings(new HashMap());
        this.setOwnerRights(new HashMap());
    }

    /**
     * @return Returns the ownerRights.
     */
    private HashMap getOwnerRights() {
        return ownerRights;
    }

    /**
     * @param ownerRights
     *            The ownerRights to set.
     */
    private void setOwnerRights(HashMap ownerRights) {
        this.ownerRights = ownerRights;
    }

    /**
     * @return Returns the defaultSettings.
     */
    private FstxTimeSettings getDefaultSettings() {
        if (defaultSettings == null) {
            SettingsInitializer init = new SettingsInitializer(DefaultSystemOwnerBroker
                    .getDefaultSystemOwner());
            
            defaultSettings = init.initialize();
        }
        return defaultSettings;
    }

//    /**
//     * @param defaultSettings
//     *            The defaultSettings to set.
//     */
//    private void setDefaultSettings(FstxTimeSettings defaultSettings) {
//        this.defaultSettings = defaultSettings;
//    }
}