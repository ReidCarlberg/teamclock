/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.Employee;
import com.fivesticks.time.employee.EmployeeServiceDelegate;
import com.fivesticks.time.employee.EmployeeServiceDelegateFactory;

/**
 * @author Reid
 *  
 */
public class ModifyAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;

    private String saveEmployee;

    private String cancelEmployee;

    private String deleteEmployee;

    private Long target;

    private Employee targetEmployee = new Employee();

    public String execute() throws Exception {

        //cancel
        if (this.getCancelEmployee() != null)
            return SUCCESS;

        EmployeeServiceDelegate sd = EmployeeServiceDelegateFactory.factory.build(this
                .getSystemOwner());

        //delete
        if (this.getDeleteEmployee() != null && this.getModifyContext().getTarget() != null && this.getConfirm() != null) {
            sd.delete(this.getModifyContext().getTarget());
            return SUCCESS;
        }

        //modify
        if (this.getTarget() != null) {
            Employee newTarget = sd.get(this.getTarget());
            this.setTargetEmployee(newTarget);
            this.getModifyContext().setTarget(newTarget);
            return INPUT;
        }

        //add
        if (this.getSaveEmployee() == null) {
            this.setTargetEmployee(new Employee());
            this.getModifyContext().setTarget(null);
            return INPUT;
        }

        //validate
        validate();

        if (this.hasErrors())
            return INPUT;

        //save
        if (this.getModifyContext().getTarget() != null) {
            this.getTargetEmployee().setId(
                    this.getModifyContext().getTarget().getId());

        }

        sd.save(this.getTargetEmployee());

        return SUCCESS;
    }

    public void validate() {

        ValidationHelper helper = new ValidationHelper();

        helper.validateNonEmpty(this.getTargetEmployee().getCode(), this,
                "targetEmployee.code", "Employee code is required.");

        helper.validateNonEmpty(this.getTargetEmployee().getNameFirst(), this,
                "targetEmployee.nameFirst", "First name is required.");

        helper.validateNonEmpty(this.getTargetEmployee().getNameLast(), this,
                "targetEmployee.nameLast", "Last name is required.");

        helper.validateNonEmpty(this.getTargetEmployee().getUsername(), this,
                "targetEmployee.username", "Username is required.");

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
     * @return Returns the cancelEmployee.
     */
    public String getCancelEmployee() {
        return cancelEmployee;
    }

    /**
     * @param cancelEmployee
     *            The cancelEmployee to set.
     */
    public void setCancelEmployee(String cancelEmployee) {
        this.cancelEmployee = cancelEmployee;
    }

    /**
     * @return Returns the deleteEmployee.
     */
    public String getDeleteEmployee() {
        return deleteEmployee;
    }

    /**
     * @param deleteEmployee
     *            The deleteEmployee to set.
     */
    public void setDeleteEmployee(String deleteEmployee) {
        this.deleteEmployee = deleteEmployee;
    }

    /**
     * @return Returns the saveEmployee.
     */
    public String getSaveEmployee() {
        return saveEmployee;
    }

    /**
     * @param saveEmployee
     *            The saveEmployee to set.
     */
    public void setSaveEmployee(String saveEmployee) {
        this.saveEmployee = saveEmployee;
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
     * @return Returns the targetEmployee.
     */
    public Employee getTargetEmployee() {
        return targetEmployee;
    }

    /**
     * @param targetEmployee
     *            The targetEmployee to set.
     */
    public void setTargetEmployee(Employee targetEmployee) {
        this.targetEmployee = targetEmployee;
    }
}
