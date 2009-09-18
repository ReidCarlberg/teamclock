/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import com.fivesticks.time.register.v2.RegistrationPlan;

public class SelectPlanAction extends AbstractRegistrationContextAwareAction {

    private String submitPlan;
    
    private String planCode;
    
    public String execute() throws Exception {
        
        if (this.getSubmitPlan() ==null)
            return INPUT;
        
        if (this.getPlanCode() == null) {
            this.addActionMessage("Please select a plan to continue.");
        }
        
        RegistrationPlan plan = null;
        try {
            plan = RegistrationPlan.getPlan(this.getPlanCode());
        } catch (Exception e) {
            this.addActionMessage("Please select a plan to continue.");            
        }
        
        if (this.hasActionMessages())
            return INPUT;
        
        this.getRegistrationContext().setPlan(plan);
            
        return SUCCESS;
    }

    /**
     * @return Returns the planCode.
     */
    public String getPlanCode() {
        return planCode;
    }

    /**
     * @param planCode The planCode to set.
     */
    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    /**
     * @return Returns the submitPlan.
     */
    public String getSubmitPlan() {
        return submitPlan;
    }

    /**
     * @param submitPlan The submitPlan to set.
     */
    public void setSubmitPlan(String submitPlan) {
        this.submitPlan = submitPlan;
    }
}
