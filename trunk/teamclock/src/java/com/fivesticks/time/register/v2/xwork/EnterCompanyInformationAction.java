/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import java.util.Collection;

import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.util.TimeZoneListBuilder;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;

public class EnterCompanyInformationAction extends
        AbstractRegistrationContextAwareAction {

    private String email2;

    private String submitCompany;

    private boolean agreeToTerms;
    
    private String timezone;

    public String execute() throws Exception {
        
        if (this.getSubmitCompany() == null) {
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }
        
        /*
         * add data to registration context
         */
        this.getRegistrationContext().getSettings().setSystemTimeZone(this.getTimezone());
        
        return SUCCESS;
    }
    /**
     * @return Returns the agreeToTerms.
     */
    public boolean isAgreeToTerms() {
        return agreeToTerms;
    }

    /**
     * @param agreeToTerms The agreeToTerms to set.
     */
    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    /**
     * @return Returns the email2.
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 The email2 to set.
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return Returns the submitCompany.
     */
    public String getSubmitCompany() {
        return submitCompany;
    }

    /**
     * @param submitCompany The submitCompany to set.
     */
    public void setSubmitCompany(String submitCompany) {
        this.submitCompany = submitCompany;
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

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getContactName())) {
            this.addFieldError("registrationContext.systemOwner.contactName",
                    "Contact name is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getContactEmail())) {
            this.addFieldError("registrationContext.systemOwner.contactEmail",
                    "Contact email is required.");
        } else if (!helper.isEmailAddress(this.getRegistrationContext().getSystemOwner()
                .getContactEmail())) {
            this.addFieldError("registrationContext.systemOwner.contactEmail",
                    "Address does not appear to be valid.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getContactPhone())) {
            this.addFieldError("registrationContext.systemOwner.contactPhone",
                    "Contact phone number is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getName())) {
            this.addFieldError("registrationContext.systemOwner.name", "Company name is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getAddress1())) {
            this.addFieldError("registrationContext.systemOwner.address1", "Address is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getCity())) {
            this.addFieldError("registrationContext.systemOwner.city", "City is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getState())) {
            this.addFieldError("registrationContext.systemOwner.state",
                    "State/Province is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getPostalCode())) {
            this.addFieldError("registrationContext.systemOwner.postalCode",
                    "Zip/Postal code is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getSystemOwner().getCountry())) {
            this.addFieldError("registrationContext.systemOwner.country", "Country is required.");
        }

        if (helper.isStringEmpty(this.getTimezone())) {
            this.addFieldError("timezone", "Timezone is required.");
        }

        if (helper.isStringEmpty(this.getRegistrationContext().getUser().getUsername())) {
            this.addFieldError("registrationContext.user.username", "Username is required.");
        } else if (!helper.isValidUsername(this.getRegistrationContext().getUser().getUsername())) {
            this.addFieldError("registrationContext.user.username",
                    "Username is not valid.  It must be 5 characters.");
        }
       
        if (!this.isAgreeToTerms()) {
            this.addFieldError("agreeToTerms",
                    "You must agree to terms to continue.");
        }
    }

    private void validateContent() {

        UserBD bd = UserBDFactory.factory.build();

        try {
            // username
            User testUser = bd.getByUsername(this.getRegistrationContext().getUser().getUsername());
            this.addFieldError("registrationContext.user.username",
                    "That username is already in use.");
            return;
        } catch (UserBDException e) {
        }

        if (!this.getRegistrationContext().getSystemOwner().getContactEmail().equals(this.getEmail2())) {
            this.addFieldError("registrationContext.systemOwner.contactEmail",
                    "Email and Email (confirm) must be the same.");
            return;
        }

        try {
            // email
            User testUser = bd.getUserByEmail(this.getRegistrationContext().getSystemOwner()
                    .getContactEmail());
            this.addFieldError("registrationContext.systemOwner.contactEmail",
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
    
}
