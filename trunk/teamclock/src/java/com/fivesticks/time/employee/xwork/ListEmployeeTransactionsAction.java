/*
 * Created on Jun 30, 2005 by Reid
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.Employee;
import com.fivesticks.time.employee.EmployeeServiceDelegateFactory;
import com.fivesticks.time.employee.util.EmployeeAccountTransactionType;

/**
 * @author Reid
 */
public class ListEmployeeTransactionsAction extends SessionContextAwareSupport
        implements ModifyContextAware {

    private ModifyContext modifyContext;

    private Long target;

    private String type;

    public String execute() throws Exception {

        if (this.getTarget() == null
                && this.getModifyContext().getTarget() == null)
            return INPUT;

        if (this.getTarget() != null) {
            Employee targetEmployee = EmployeeServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).get(this.getTarget());
            this.getModifyContext().setTarget(targetEmployee);
        }
        
        if (this.getType() != null ) {
            EmployeeAccountTransactionType txType = EmployeeAccountTransactionType.getType(this.getType());
            this.getModifyContext().setEmployeeAccountTransactionType(txType);
        }
        
        if (this.getModifyContext().getEmployeeAccountTransactionType() == null)
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
     * @param modifyContext
     *            The modifyContext to set.
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
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
}