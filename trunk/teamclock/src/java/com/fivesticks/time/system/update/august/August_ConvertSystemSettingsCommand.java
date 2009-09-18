/*
 * Created on Oct 29, 2004 by Reid
 */
package com.fivesticks.time.system.update.august;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettings;
import com.fivesticks.time.settings.SystemSettingsDao;
import com.fivesticks.time.settings.SystemSettingsDaoFactory;
import com.fivesticks.time.settings.SystemSettingsParameters;
import com.fivesticks.time.systemowner.DefaultSystemOwnerBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class August_ConvertSystemSettingsCommand {

    private SystemOwner systemOwner;

    public August_ConvertSystemSettingsCommand(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public void execute() throws Exception {
        handleConversion();
    }

    /**
     *  
     */
    private void handleConversion() {

        //handle settings

        SystemSettingsDao dao = SystemSettingsDaoFactory.factory.build();

        Collection current = dao.find(new SystemSettingsParameters());

        /*
         * do the default first...
         */
        SystemOwner defaultOwner = DefaultSystemOwnerBroker
                .getDefaultSystemOwner();

        for (Iterator iter = current.iterator(); iter.hasNext();) {
            SystemSettings element = (SystemSettings) iter.next();

            element.setOwnerKey(defaultOwner.getKey());

            dao.save(element);

        }

        for (Iterator iter = current.iterator(); iter.hasNext();) {
            SystemSettings element = (SystemSettings) iter.next();

            SettingTypeEnum currentType = SettingTypeEnum.getByName(element
                    .getSettingKey());

            if (currentType != null && !currentType.isDefaultOnly()) {
                element.setOwnerKey(systemOwner.getKey());
                element.setId(null);
                dao.save(element);
            }

        }

    }
}