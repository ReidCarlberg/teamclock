/*
 * Created on Jul 3, 2006
 *
 */
package com.fivesticks.time.dashboard.util;

public class DashboardMiscellaneousRecentAction implements DashboardRecentAction {

    private String typeName;
    private Long id;
    private String description;
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Returns the typeName.
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * @param typeName The typeName to set.
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    


}
