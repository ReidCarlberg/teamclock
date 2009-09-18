/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegate;
import com.fivesticks.time.register.v2.xwork.RegistrationContext;
import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.payments.PaymentMethodServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserTypeEnum;

/*
 * takes a registration context and persists it.
 * also throws events.
 */
public class RegistrationProcessCommand implements RegistrationProcessCommandIF {

    private RegistrationContext registrationContext;

    private SystemOwnerServiceDelegate systemOwnerServiceDelegate;

    private UserServiceDelegate userServiceDelegate;

    private PaymentMethodServiceDelegate paymentMethodServiceDelegate;

    private SystemSettingsServiceDelegate systemSettingsServiceDelegate;
    
    private CustomerServiceDelegate customerServiceDelegate;
    
    private ProjectServiceDelegate fstxProjectBD;
    
    private TaskServiceDelegate fstxTaskBD;
    
    private PaymentProcessor paymentProcessor;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.register.v2.RegistrationProcessCommandIF#process(com.fivesticks.time.register.v2.xwork.RegistrationContext)
     */
    public void execute() throws Exception {

        /*
         * save the new systemowner
         */
        try {
            this.getSystemOwnerServiceDelegate().decorateWithKeyAndSave(
                    this.getRegistrationContext().getSystemOwner());
        } catch (SystemOwnerServiceDelegateException e) {
            throw new RuntimeException("failed in systemownerservicedelegate",
                    e);
        }

        /*
         * save the user
         */
        this.getUserServiceDelegate().setSystemOwner(
                this.getRegistrationContext().getSystemOwner());
        try {
            this.getUserServiceDelegate().createNewUser(
                    this.getRegistrationContext().getUser().getUsername(),
                    this.getRegistrationContext().getUser().getPassword(),
                    this.getRegistrationContext().getUser().getEmail(),
                    UserTypeEnum.OWNERADMIN);
        } catch (UserServiceDelegateException e) {
            throw new RuntimeException("failed in user service delegate");
        }

        /*
         * save the payment method
         */
        this.getPaymentMethodServiceDelegate().setSystemOwner(
                this.getRegistrationContext().getSystemOwner());
        try {
            this.getPaymentMethodServiceDelegate().save(
                    this.getRegistrationContext().getPaymentInformation());
        } catch (Exception e) {
            throw new RuntimeException(
                    "failed in payment method service delegate", e);
        }

        /*
         * build some default objects
         */
        /*
         * default data...
         */
        this.getCustomerServiceDelegate().setSystemOwner(this.getRegistrationContext().getSystemOwner());
        Customer fstxCustomer = new Customer();
        fstxCustomer.setName("Default Customer");
        fstxCustomer.setStreet1("123 Default Street");
        fstxCustomer.setStreet2("");
        fstxCustomer.setCity("Defaultsville");
        fstxCustomer.setState("FL");
        fstxCustomer.setZip("00000");
        this.getCustomerServiceDelegate().save(fstxCustomer);

        this.getFstxProjectBD().setSystemOwner(this.getRegistrationContext().getSystemOwner());
        Project fstxProject = new Project();
        fstxProject.setCustId(fstxCustomer.getId());
        fstxProject.setActive(true);
        fstxProject.setShortName("default");
        fstxProject.setLongName("default");
        this.getFstxProjectBD().save(fstxProject);

        this.getFstxTaskBD().setSystemOwner(this.getRegistrationContext().getSystemOwner());
        Task fstxTask = new Task();
        fstxTask.setNameLong("default");
        fstxTask.setNameShort("default");
        this.getFstxTaskBD().save(fstxTask);
        
        /*
         * save the relevant settings
         */
        this.getSystemSettingsServiceDelegate().setSystemOwner(
                this.getRegistrationContext().getSystemOwner());

        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.REGISTRATION_PLAN.getName(),
                this.getRegistrationContext().getPlan().getCode());
        
        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_SYSTEM_NAME.getName(),
                this.getRegistrationContext().getSystemOwner().getName() + " TeamClock");

        
        /*
         * 2005-12-21 RSC These are legacy.  Do we need them for anything?
         */
        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), 3);
        
        /*
         * 2005-12-21 RSC Default data.
         */
        this.getSystemSettingsServiceDelegate().updateSetting(
                SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_PROJECT.getName(),
                fstxProject.getId().toString());

        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK
                .getName(), fstxTask.getId().toString());

        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_PROJECT
                .getName(), fstxTask.getId().toString());

        this.getSystemSettingsServiceDelegate().updateSetting(
                SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO.getName(),
                this.getRegistrationContext().getUser().getUsername());

        this.getSystemSettingsServiceDelegate().updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY
                .getName(), this.getRegistrationContext().getUser().getUsername());
        
        this.getSystemSettingsServiceDelegate()
                .updateSetting(
                        SettingTypeEnum.SETTING_TIMEZONE,
                        this.getRegistrationContext().getSettings()
                                .getSystemTimeZone());



    }

    /**
     * @return Returns the paymentProcessor.
     */
    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    /**
     * @param paymentProcessor
     *            The paymentProcessor to set.
     */
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    /**
     * @return Returns the systemOwnerServiceDelegate.
     */
    public SystemOwnerServiceDelegate getSystemOwnerServiceDelegate() {
        return systemOwnerServiceDelegate;
    }

    /**
     * @param systemOwnerServiceDelegate
     *            The systemOwnerServiceDelegate to set.
     */
    public void setSystemOwnerServiceDelegate(
            SystemOwnerServiceDelegate systemOwnerServiceDelegate) {
        this.systemOwnerServiceDelegate = systemOwnerServiceDelegate;
    }

    /**
     * @return Returns the userServiceDelegate.
     */
    public UserServiceDelegate getUserServiceDelegate() {
        return userServiceDelegate;
    }

    /**
     * @param userServiceDelegate
     *            The userServiceDelegate to set.
     */
    public void setUserServiceDelegate(UserServiceDelegate userServiceDelegate) {
        this.userServiceDelegate = userServiceDelegate;
    }

    /**
     * @return Returns the registrationContext.
     */
    public RegistrationContext getRegistrationContext() {
        return registrationContext;
    }

    /**
     * @param registrationContext
     *            The registrationContext to set.
     */
    public void setRegistrationContext(RegistrationContext registrationContext) {
        this.registrationContext = registrationContext;
    }

    /**
     * @return Returns the paymentMethodServiceDelegate.
     */
    public PaymentMethodServiceDelegate getPaymentMethodServiceDelegate() {
        return paymentMethodServiceDelegate;
    }

    /**
     * @param paymentMethodServiceDelegate
     *            The paymentMethodServiceDelegate to set.
     */
    public void setPaymentMethodServiceDelegate(
            PaymentMethodServiceDelegate paymentMethodServiceDelegate) {
        this.paymentMethodServiceDelegate = paymentMethodServiceDelegate;
    }

    /**
     * @return Returns the systemSettingsServiceDelegate.
     */
    public SystemSettingsServiceDelegate getSystemSettingsServiceDelegate() {
        return systemSettingsServiceDelegate;
    }

    /**
     * @param systemSettingsServiceDelegate
     *            The systemSettingsServiceDelegate to set.
     */
    public void setSystemSettingsServiceDelegate(
            SystemSettingsServiceDelegate systemSettingsServiceDelegate) {
        this.systemSettingsServiceDelegate = systemSettingsServiceDelegate;
    }

    /**
     * @return Returns the fstxCustomerBD.
     */
    public CustomerServiceDelegate getCustomerServiceDelegate() {
        return customerServiceDelegate;
    }

    /**
     * @param fstxCustomerBD The fstxCustomerBD to set.
     */
    public void setCustomerServiceDelegate(CustomerServiceDelegate fstxCustomerBD) {
        this.customerServiceDelegate = fstxCustomerBD;
    }

    /**
     * @return Returns the fstxProjectBD.
     */
    public ProjectServiceDelegate getFstxProjectBD() {
        return fstxProjectBD;
    }

    /**
     * @param fstxProjectBD The fstxProjectBD to set.
     */
    public void setFstxProjectBD(ProjectServiceDelegate fstxProjectBD) {
        this.fstxProjectBD = fstxProjectBD;
    }

    /**
     * @return Returns the fstxTaskBD.
     */
    public TaskServiceDelegate getFstxTaskBD() {
        return fstxTaskBD;
    }

    /**
     * @param fstxTaskBD The fstxTaskBD to set.
     */
    public void setFstxTaskBD(TaskServiceDelegate fstxTaskBD) {
        this.fstxTaskBD = fstxTaskBD;
    }
}
