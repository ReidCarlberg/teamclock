/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.activity;

public class ActivitySummaryByLabel extends AbstractActivitySummary {

    private String label;

    /**
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    public String getShortLabel() {
        if (getLabel().length() < 10)
            return this.getLabel();
        
        return this.getLabel().substring(0,9);
    }
    
    
    
    

}
