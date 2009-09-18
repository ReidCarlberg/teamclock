/*
 * Created on Jun 22, 2006
 *
 */
package com.fivesticks.time.activity;

import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.util.LookupDecorateable;

public class ActivitySummaryByProjectClass extends AbstractActivitySummary implements LookupDecorateable {

    private Long projectClassId;
    
    private Lookup lookup;

    public ActivitySummaryByProjectClass(Long count, 
            Double elapsedChargeable, Double elapsed, Long projectClassId) {
        this.count = count;
        this.elapsed = elapsed;
        this.elapsedChargeable = elapsedChargeable;
        this.projectClassId = projectClassId;
        
    }

    /**
     * @return Returns the projectClassId.
     */
    public Long getProjectClassId() {
        return projectClassId;
    }

    /**
     * @param projectClassId
     *            The projectClassId to set.
     */
    public void setProjectClassId(Long projectClassId) {
        this.projectClassId = projectClassId;
    }

    public Long getLookupId() {
        
        return this.getProjectClassId();
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    /**
     * @return Returns the lookup.
     */
    public Lookup getLookup() {
        return lookup;
    }

    public String getLabel() {
        if (this.getLookup() == null)
            return "[none]";
        
        return this.getLookup().getFullName();
    }

    public String getShortLabel() {
        if (this.getLookup() == null)
            return "NA";
        return this.getLookup().getShortName();
    }
}
