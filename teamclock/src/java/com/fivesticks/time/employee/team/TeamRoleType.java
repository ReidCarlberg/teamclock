/*
 * Created on Aug 21, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.common.util.DescriptiveEnum;

public class TeamRoleType extends DescriptiveEnum {

    public static final TeamRoleType STANDARD = new TeamRoleType("STANDARD", "Standard");
    
    public static final TeamRoleType LEADER = new TeamRoleType("LEADER", "Leader");
    
    private static Collection all;
    
    public TeamRoleType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        addToCollection(this);
    }

    private static void addToCollection(TeamRoleType target) {
        if (getAll() == null)
            all = new ArrayList();

        all.add(target);
    }

    /**
     * @return Returns the all.
     */
    public static Collection getAll() {
        return all;
    }

    /**
     * @param all The all to set.
     */
    public static void setAll(Collection all) {
        TeamRoleType.all = all;
    }

    public static TeamRoleType getByName(String name) {
        
        return (TeamRoleType) TeamRoleType.getEnum(TeamRoleType.class, name);
    }
    
}
