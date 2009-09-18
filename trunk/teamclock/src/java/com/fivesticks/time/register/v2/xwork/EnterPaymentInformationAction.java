/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import com.fivesticks.time.common.util.ValidationHelper;

public class EnterPaymentInformationAction extends
        AbstractRegistrationContextAwareAction {

    private String submitPayment;
    

    public String execute() throws Exception {

        if (this.getSubmitPayment() == null)
            return INPUT;

        validate();

        if (this.hasActionErrors())
            return INPUT;
        
        validateContent();

        return SUCCESS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.ActionSupport#validate()
     */
    public void validate() {

        super.validate();

        ValidationHelper helper = new ValidationHelper();

        //name first
        helper.validateNonEmpty(this.getRegistrationContext()
                .getPaymentInformation().getNameOnCard(), this,
                "registrationContext.paymentInformation.nameFirst",
                "First name is required.");
        //name last
//        helper.validateNonEmpty(this.getRegistrationContext()
//                .getPaymentInformation().getNameLast(), this,
//                "registrationContext.paymentInformation.nameLast",
//                "Last name is required.");

        //type
//        helper.validateNonEmpty(this.getRegistrationContext()
//                .getPaymentInformation().getType(), this,
//                "registrationContext.paymentInformation.type",
//                "Type is required.");
        
        //number
        helper.validateNonEmpty(this.getRegistrationContext()
                .getPaymentInformation().getNumber(), this,
                "registrationContext.paymentInformation.number",
                "Number is required.");
        
        //month
        helper.validateNonEmpty(this.getRegistrationContext()
                .getPaymentInformation().getExpiresMonth(), this,
                "registrationContext.paymentInformation.expiresMonth",
                "Expiration month is required.");

        //year
        helper.validateNonEmpty(this.getRegistrationContext()
                .getPaymentInformation().getExpiresYear(), this,
                "registrationContext.paymentInformation.expiresYear",
                "Expiration is required.");

        //cvv
        helper.validateNonEmpty(this.getRegistrationContext()
                .getPaymentInformation().getCvv(), this,
                "registrationContext.paymentInformation.cvv",
                "CVV is required.");

        //address
        if (!this.getRegistrationContext().isBillingSameAsContact()) {
            //street
            helper.validateNonEmpty(this.getRegistrationContext()
                    .getPaymentInformation().getStreet(), this,
                    "registrationContext.paymentInformation.street",
                    "Street address is required.");
            //city
            helper.validateNonEmpty(this.getRegistrationContext()
                    .getPaymentInformation().getCity(), this,
                    "registrationContext.paymentInformation.city",
                    "City is required.");
            //state
            helper.validateNonEmpty(this.getRegistrationContext()
                    .getPaymentInformation().getState(), this,
                    "registrationContext.paymentInformation.state",
                    "State is required.");
            //zip
            helper.validateNonEmpty(this.getRegistrationContext()
                    .getPaymentInformation().getZip(), this,
                    "registrationContext.paymentInformation.zip",
                    "Zip is required.");
            //country
            helper.validateNonEmpty(this.getRegistrationContext()
                    .getPaymentInformation().getCountry(), this,
                    "registrationContext.paymentInformation.country",
                    "Country is required.");
            
        }
        

    }

    public void validateContent() { 
    
        int length = 16;
        boolean invalid=false;
        /*
         * CCNumber
         */
        String ccNum = this.getRegistrationContext().getPaymentInformation().getNumber();
//        if (ccNum.startsWith("4"))
//            this.getRegistrationContext().getPaymentInformation().setType("Visa");
//        else if (ccNum.startsWith("5"))
//            this.getRegistrationContext().getPaymentInformation().setType("MasterCard");
//        else if (ccNum.startsWith("3")) {
//            this.getRegistrationContext().getPaymentInformation().setType("Amex");
//            length=15;
//        }
//        else if (ccNum.startsWith("6"))
//            this.getRegistrationContext().getPaymentInformation().setType("Discover");
//        else
//            invalid = true;
            
        
        if (ccNum.length() != length) {
            invalid = true;
        }
        
        if (invalid) {
            this.addFieldError("registrationContext.paymentInformation.number", "Number appears to be invalid.");
        }
        
    }
    /**
     * @return Returns the submitPayment.
     */
    public String getSubmitPayment() {
        return submitPayment;
    }

    /**
     * @param submitPayment
     *            The submitPayment to set.
     */
    public void setSubmitPayment(String submitPayment) {
        this.submitPayment = submitPayment;
    }



}
