/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users.old;

import com.fivesticks.time.useradmin.FstxTimeSystemRights;
import com.fstx.stdlib.authen.users.UserGroup;

/**
 * @author Reid
 *  
 */
public interface GroupRightBD extends GroupRightsDAOAware {

    public static final String SPRING_BEAN_NAME = "groupRightBD";

    public static final GroupRightBDFactory factory = new GroupRightBDFactory();

    public abstract void add(FstxTimeSystemRights right, UserGroup group)
            throws GroupRightBDException;

    public abstract void remove(FstxTimeSystemRights right, UserGroup group)
            throws GroupRightBDException;
}