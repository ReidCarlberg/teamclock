package com.fivesticks.time.timeclock;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 *  
 */
public class UserShiftCollectionBuilderFactory {

    public static final UserShiftCollectionBuilderFactory factory = new UserShiftCollectionBuilderFactory();

    public UserShiftCollectionBuilder build(SystemOwner systemOwner) {
        return new UserShiftCollectionBuilderImpl(systemOwner);
    }

}