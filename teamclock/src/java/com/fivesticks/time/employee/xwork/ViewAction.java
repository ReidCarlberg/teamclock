/*
 * Created on Jun 30, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.EmployeeServiceDelegate;
import com.fivesticks.time.employee.EmployeeServiceDelegateFactory;

/**
 * @author Reid
 *
 */
public class ViewAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;
    
    private Long target;
    
    private double balanceSick;
    
    private double balancePersonal;
    
    private double balanceVacation;
    
    public String execute() throws Exception {
        
        EmployeeServiceDelegate sd = EmployeeServiceDelegateFactory.factory.build(this.getSystemOwner());
        
        if (this.getTarget() != null) {
            this.getModifyContext().setTarget(sd.get(this.getTarget()));
        }
        
        if (this.getModifyContext().getTarget() == null)
            return INPUT;
        
        return SUCCESS;
    }
    /**
     * @return Returns the modifyContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }
    /**
     * @param modifyContext The modifyContext to set.
     */
    public void setModifyContext(ModifyContext modifyContext) {
        this.modifyContext = modifyContext;
    }
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
}
