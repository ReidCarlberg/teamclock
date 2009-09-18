/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.account.xwork.AccountTransactionContextAwareSupport;
import com.fivesticks.time.employee.util.EmployeeAccountTransactionType;

/**
 * @author Reid
 */
public class ViewEmployeeAccountTransactionsAction extends
        AccountTransactionContextAwareSupport implements ModifyContextAware {

    private ModifyContext modifyContext;

    private String target;

    public String execute() throws Exception {

        EmployeeAccountTransactionType accountType = null;

        accountType = EmployeeAccountTransactionType.getType(this.getTarget());

        if (accountType == null)
            return INPUT;

        this.getAccountTransactionContext().setObjectTransactionType(
                accountType);
        
        this.getAccountTransactionContext().setSystemOwnerKeyAware(this.getModifyContext().getTarget());

        this.getAccountTransactionContext().setLabel(
                this.getModifyContext().getTarget().getNameLast() + ", "
                        + this.getModifyContext().getTarget().getNameFirst());

        this.getAccountTransactionContext()
                .setBackAction("employeeView.action");

        return SUCCESS;
    }

    /**
     * @return Returns the type.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setTarget(String type) {
        this.target = type;
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
}