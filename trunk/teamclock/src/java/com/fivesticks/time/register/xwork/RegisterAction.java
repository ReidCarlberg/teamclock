/*
 * Created on Oct 1, 2004 by Reid
 */
package com.fivesticks.time.register.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.common.util.TimeZoneListBuilder;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.config.DatabaseContent_ValidateVersionSettingsCommand;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.register.events.RegisterEventPublisher;
import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyGenerator;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.useradmin.UserServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class RegisterAction extends ActionSupport {

    private SystemOwner systemOwner = new SystemOwner();

    private User user = new User();

    private String email2;

    private String submitRegister;

    private String timezone;

    // private String country;

    private boolean agreeToTerms;

    public String execute() throws Exception {

        if (this.getSubmitRegister() == null) {
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        SystemOwnerServiceDelegate ownerbd = SystemOwnerServiceDelegateFactory.factory
                .build();

        this.getSystemOwner().setActivated(false);
        this.getSystemOwner().setKey(
                SystemOwnerKeyGenerator.singleton.getGeneratedKey());
        this.getSystemOwner().setAccount(AccountType.DEMO.getName());
        
        this.setSystemOwner(ownerbd.save(this.getSystemOwner()));

        
        
        UserServiceDelegate sd = UserServiceDelegateFactory.factory.build(this
                .getSystemOwner());

        String password = new RandomStringBuilder().buildRandomString(10);

        User newUser = sd.createNewUser(this.getUser().getUsername(), password,
                this.getSystemOwner().getContactEmail(),
                UserTypeEnum.OWNERADMIN);

        DatabaseContent_ValidateVersionSettingsCommand command = new DatabaseContent_ValidateVersionSettingsCommand();

        command.execute(this.getSystemOwner());

        SystemSettingsServiceDelegate settingssd = SystemSettingsServiceDelegateFactory.factory
                .build(systemOwner);

        settingssd.updateSetting(SettingTypeEnum.SETTING_SYSTEM_NAME.getName(),
                "TeamClock.com");

        settingssd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE.getName(),
                this.getTimezone());

        // settingssd.updateSetting(SettingTypeEnum.SETTING_COUNTRY.getName(),
        // this.getCountry());

        settingssd.updateSetting(SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        settingssd.updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), 3);

        /*
         * default data...
         */

        Customer fstxCustomer = new Customer();
        fstxCustomer.setName("Default Customer");
        fstxCustomer.setStreet1("123 Default Street");
        fstxCustomer.setStreet2("");
        fstxCustomer.setCity("Defaultsville");
        fstxCustomer.setState("FL");
        fstxCustomer.setZip("00000");
        CustomerServiceDelegateFactory.factory.build(this.getSystemOwner())
                .save(fstxCustomer);

        Project fstxProject = new Project();
        fstxProject.setCustId(fstxCustomer.getId());
        fstxProject.setActive(true);
        fstxProject.setShortName("default");
        fstxProject.setLongName("default");
        ProjectServiceDelegateFactory.factory.build(this.getSystemOwner())
                .save(fstxProject);

        Task fstxTask = new Task();
        fstxTask.setNameLong("default");
        fstxTask.setNameShort("default");
        TaskServiceDelegateFactory.factory.build(this.getSystemOwner()).save(
                fstxTask);

        settingssd.updateSetting(
                SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_PROJECT.getName(),
                fstxProject.getId().toString());

        settingssd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK
                .getName(), fstxTask.getId().toString());

        settingssd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_PROJECT
                .getName(), fstxProject.getId().toString());

        settingssd.updateSetting(
                SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO.getName(),
                newUser.getUsername());

        settingssd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY
                .getName(), newUser.getUsername());

        settingssd.updateSetting(SettingTypeEnum.SYSTEM_IS_FREE_SYSTEM, true);

        /*
         * 2006-08-26 reid add a sample to do.
         */
        ToDo sampleToDo = new ToDo();
        sampleToDo.setAssignedToUser(newUser.getUsername());
        sampleToDo.setComplete(false);
        sampleToDo.setCreateTimestamp(new Date());
        sampleToDo.setDeadlineTimestamp(new Date());
        sampleToDo.setDetail("Get to know TeamClock.com--Employee Time Clock, To Do Manager, Activity Manager and more.");
        sampleToDo.setEnteredByUser(newUser.getUsername());
        sampleToDo.setEstimatedRemainingHours(new Double(0.0));
        sampleToDo.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        sampleToDo.setProjectId(fstxProject.getId());
        sampleToDo.setStatus("SAMPLE");
        sampleToDo.setOwnerKey(this.getSystemOwner().getKey());
        
        GenericDAOFactory.factory.build().save(sampleToDo);
        
        new RegisterEventPublisher().publishRegisterEvent(
                this.getSystemOwner(), newUser, password);

        return SUCCESS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.Validateable#validate()
     */
    public void validate() {

        validateRequiredFields();

        if (!this.hasErrors())
            validateContent();

    }

    private void validateRequiredFields() {

        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getSystemOwner().getContactName())) {
            this.addFieldError("systemOwner.contactName",
                    "Contact name is required.");
        }

        if (helper.isStringEmpty(this.getSystemOwner().getContactEmail())) {
            this.addFieldError("systemOwner.contactEmail",
                    "Contact email is required.");
        } else if (!helper.isEmailAddress(this.getSystemOwner()
                .getContactEmail())) {
            this.addFieldError("systemOwner.contactEmail",
                    "Address does not appear to be valid.");
        }

        
//        if (helper.isStringEmpty(this.getSystemOwner().getName())) {
//            this.addFieldError("systemOwner.name", "Company name is required.");
//        }


        // if (helper.isStringEmpty(this.getSystemOwner().getContactPhone())) {
        // this.addFieldError("systemOwner.contactPhone",
        // "Contact phone number is required.");
        // }
        //
        //
        // if (helper.isStringEmpty(this.getSystemOwner().getAddress1())) {
        // this.addFieldError("systemOwner.address1", "Address is required.");
        // }
        //
        // if (helper.isStringEmpty(this.getSystemOwner().getCity())) {
        // this.addFieldError("systemOwner.city", "City is required.");
        // }
        //
        // if (helper.isStringEmpty(this.getSystemOwner().getState())) {
        // this.addFieldError("systemOwner.state",
        // "State/Province is required.");
        // }
        //
        // if (helper.isStringEmpty(this.getSystemOwner().getPostalCode())) {
        // this.addFieldError("systemOwner.postalCode",
        // "Zip/Postal code is required.");
        // }
        //
        // if (helper.isStringEmpty(this.getSystemOwner().getCountry())) {
        // this.addFieldError("systemOwner.country", "Country is required.");
        // }

        if (helper.isStringEmpty(this.getTimezone())) {
            this.addFieldError("timezone", "Timezone is required.");
        }

        if (helper.isStringEmpty(this.getUser().getUsername())) {
            this.addFieldError("user.username", "Username is required.");
        } else if (!helper.isValidUsername(this.getUser().getUsername())) {
            this.addFieldError("user.username",
                    "Username is not valid.  It must be 5 characters.");
        }

        // if (helper.isStringEmpty(this.getUser().getPassword())) {
        // this.addFieldError("user.password", "Password is required.");
        // } else if (helper.isStringEmpty(this.getPassword2())) {
        // this.addFieldError("password2", "Password (confirm) is required.");
        // } else if (!helper.isValidUsername(this.getUser().getUsername())) {
        // this.addFieldError("user.password",
        // "Password is not valid. It must be 5 characters.");
        // }

        if (!this.isAgreeToTerms()) {
            this.addFieldError("agreeToTerms",
                    "You must agree to terms to continue.");
        }
    }

    private void validateContent() {

        UserBD bd = UserBDFactory.factory.build();

        try {
            // username
            User testUser = bd.getByUsername(this.getUser().getUsername());
            this.addFieldError("user.username",
                    "That username is already in use.");
            return;
        } catch (UserBDException e) {
        }

        if (!this.getSystemOwner().getContactEmail().equals(this.getEmail2())) {
            this.addFieldError("systemOwner.contactEmail",
                    "Email and Email (confirm) must be the same.");
            return;
        }

        try {
            // email
            User testUser = bd.getUserByEmail(this.getSystemOwner()
                    .getContactEmail());
            this.addFieldError("systemOwner.contactEmail",
                    "That email address is already in use.");
            return;
        } catch (DAOException e1) {
        }

        // //password
        // if (!this.getUser().getPassword().equals(this.getPassword2())) {
        // this.addFieldError("user.password",
        // "Password and Password (confirm) must be the same.");
        // }

    }

    public Collection getTimeZones() {
        return TimeZoneListBuilder.singleton.build();

    }

    public Collection getCountries() {
        return new GeographicCollection().getCountries();

    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    // /**
    // * @return Returns the password2.
    // */
    // public String getPassword2() {
    // return password2;
    // }
    //
    // /**
    // * @param password2
    // * The password2 to set.
    // */
    // public void setPassword2(String password2) {
    // this.password2 = password2;
    // }

    /**
     * @return Returns the email2.
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2
     *            The email2 to set.
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return Returns the submitRegister.
     */
    public String getSubmitRegister() {
        return submitRegister;
    }

    /**
     * @param submitRegister
     *            The submitRegister to set.
     */
    public void setSubmitRegister(String submitRegister) {
        this.submitRegister = submitRegister;
    }

    /**
     * @return Returns the timezone.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * @param timezone
     *            The timezone to set.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * @return Returns the agreeToTerms.
     */
    public boolean isAgreeToTerms() {
        return agreeToTerms;
    }

    /**
     * @param agreeToTerms
     *            The agreeToTerms to set.
     */
    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    // public String getCountry() {
    // return country;
    // }
    //
    // public void setCountry(String country) {
    // this.country = country;
    // }

}