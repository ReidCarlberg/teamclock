/*
 * Created on Jun 27, 2006
 *
 */
package com.fivesticks.time.todo.util;

public class ToDoProjectVO {

    private final Long count;

    private final Long projectId;
    
    private final String projectShortName;

    public ToDoProjectVO(Long count, Long projectId, String projectShortName) {

        this.count = count;
        this.projectId = projectId;
        this.projectShortName = projectShortName;
        
    }
    /**
     * @return Returns the count.
     */
    public Long getCount() {
        return count;
    }


    /**
     * @return Returns the projectId.
     */
    public Long getProjectId() {
        return projectId;
    }


    /**
     * @return Returns the projectShortName.
     */
    public String getProjectShortName() {
        return projectShortName;
    }


    
    
}
