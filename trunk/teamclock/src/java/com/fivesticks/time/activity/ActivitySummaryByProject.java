/*
 * Created on Jun 22, 2006
 *
 */
package com.fivesticks.time.activity;


public class ActivitySummaryByProject extends AbstractActivitySummary  {

    private Long projectId;
    private String projectShortName;
    private String projectName;


    public ActivitySummaryByProject(Long count, 
            Double elapsedChargeable, 
            Double elapsed, 
            Long projectId, String projectShortName, String projectName) {
        this.count = count;
        this.elapsed = elapsed;
        this.elapsedChargeable = elapsedChargeable;
        this.projectId = projectId;
        this.projectShortName = projectShortName;
        this.projectName = projectName;
        
    }


    /**
     * @return Returns the projectId.
     */
    public Long getProjectId() {
        return projectId;
    }


    /**
     * @param projectId The projectId to set.
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


    /**
     * @return Returns the projectName.
     */
    public String getProjectName() {
        return projectName;
    }


    /**
     * @param projectName The projectName to set.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    /**
     * @return Returns the projectShortName.
     */
    public String getProjectShortName() {
        return projectShortName;
    }


    /**
     * @param projectShortName The projectShortName to set.
     */
    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }


    public String getLabel() {
        
        return this.getProjectShortName() + " (" + this.getProjectName() + ")";
    }


    public String getShortLabel() {
        
        return this.getProjectShortName();
    }


}
