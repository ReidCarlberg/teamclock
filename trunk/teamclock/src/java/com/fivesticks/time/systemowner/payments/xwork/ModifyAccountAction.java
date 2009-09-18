/*
 * Created on Aug 22, 2006
 *
 */
package com.fivesticks.time.systemowner.payments.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.BillingFrequencyType;
import com.fivesticks.time.systemowner.RequiresUpdateAccountReasonType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.payments.PaymentMethod;
import com.fivesticks.time.systemowner.payments.PaymentMethodServiceDelegateFactory;
import com.opensymphony.xwork.Action;

public class ModifyAccountAction extends SessionContextAwareSupport implements
        AccountManagementContextAware {

    private AccountManagementContext accountManagementContext;

    private PaymentMethod paymentMethod = new PaymentMethod();

    private String actionName = "updateAccount.action";

    private String methodSubmit;

    private String methodCancel;

    private String account;

    private String billing;

    private String reasonExplanation;

    public String execute() throws Exception {

        if (this.hasText(this.getMethodCancel())) {
            return Action.SUCCESS;

        } else if (!this.hasText(this.getMethodSubmit())) {
            PaymentMethod lu = PaymentMethodServiceDelegateFactory.factory
                    .build(this.getSystemOwner()).getCurrent();
            if (lu == null) {
                lu = new PaymentMethod();
                lu.setNameOnCard(this.getSystemOwner().getContactName());
                this.getAccountManagementContext().setPaymentMethod(null);
            } else {
                this.getAccountManagementContext().setPaymentMethod(lu);
            }
            this.setBilling(this.getSystemOwner().getBillingFrequency());
            this.setAccount(this.getSystemOwner().getAccount());
            this.setPaymentMethod(lu);

            if (this.getSystemOwner().isRequiresAccountUpdate()
                    && this.getSystemOwner().getRequiresAccountUpdateReason() != null) {

                if (this.getSystemOwner().getRequiresAccountUpdateReason().equals(RequiresUpdateAccountReasonType.DEMO_OVER.getName())) {
                    this.setReasonExplanation("Your Demo account will expire shortly.  Please selected either a time clock only account or a standard account.");
                }
            }
            return INPUT;

        } else {
            if (!this.isValid()) {
                return INPUT;
            }
        }

        /*
         * only save if this is not a time clock only account
         */
        if (this.getAccount().equalsIgnoreCase(AccountType.STANDARD.getName())) {
            if (this.getAccountManagementContext().getPaymentMethod() != null
                    && this.getPaymentMethod().getNumber().equalsIgnoreCase(
                            this.getAccountManagementContext()
                                    .getPaymentMethod().getObscureNumber())) {
                this.getPaymentMethod().setNumber(
                        this.getAccountManagementContext().getPaymentMethod()
                                .getNumber());
            }

            this.getPaymentMethod().setCurrent(true);

            if (this.getAccountManagementContext().getPaymentMethod() != null) {
                this.getPaymentMethod().setId(
                        this.getAccountManagementContext().getPaymentMethod()
                                .getId());
            }

            PaymentMethodServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).save(this.getPaymentMethod());
        }

        this.getAccountManagementContext().setPaymentMethod(null);

        if (!this.getAccount().equalsIgnoreCase(
                this.getSystemOwner().getAccount())
                || !this.getBilling().equalsIgnoreCase(
                        this.getSystemOwner().getBillingFrequency())
                || this.getSystemOwner().isRequiresAccountUpdate()) {

            this.getSystemOwner().setAccount(this.getAccount());

            this.getSystemOwner().setBillingFrequency(this.billing);

            this.getSystemOwner().setRequiresAccountUpdate(false);

            this.getSystemOwner().setRequiresAccountUpdateReason(null);

            SystemOwnerServiceDelegateFactory.factory.build().save(
                    this.getSystemOwner());

            this.announceAccountUpdate(this.getSystemOwner());
        }

        this.getSessionContext().setFadeMessage("Account information updated.");

        return Action.SUCCESS;
    }

    public boolean isValid() {
        validate();
        return !this.getSessionContext().isMessagePresent();
    }

    public void validate() {

        AccountType accountType = AccountType.getByName(this.getAccount());

        BillingFrequencyType billingFrequencyType = BillingFrequencyType
                .getByName(this.getBilling());

        StringBuffer message = new StringBuffer();

        if (accountType == null) {
            decorateMessage("Account Type", message);
        } else if (accountType == AccountType.STANDARD) {

            if (billingFrequencyType == null) {
                decorateMessage("billing", message);
            }

            validateFieldHasText(this.getPaymentMethod().getNameOnCard(),
                    "Name on card", message);

            validateFieldHasText(this.getPaymentMethod().getStreet(), "Street",
                    message);
            validateFieldHasText(this.getPaymentMethod().getCity(), "City",
                    message);
            validateFieldHasText(this.getPaymentMethod().getState(), "State",
                    message);
            validateFieldHasText(this.getPaymentMethod().getCountry(),
                    "Country", message);
            validateFieldHasText(this.getPaymentMethod().getZip(), "Zip",
                    message);
            validateFieldHasText(this.getPaymentMethod().getNumber(), "Card",
                    message);
            validateFieldHasText(this.getPaymentMethod().getExpiresMonth(),
                    "Expiration month", message);
            validateFieldHasText(this.getPaymentMethod().getExpiresYear(),
                    "Expiration year", message);
            validateFieldHasText(this.getPaymentMethod().getCvv(), "CVV",
                    message);

            if (message.length() > 0) {
                this.getSessionContext().setMessage(
                        "Your form is incomplete.  Please enter data for "
                                + message.toString() + ".");
            } else {

                String ccNum = this.getPaymentMethod().getNumber().trim();

                if (this.getAccountManagementContext().getPaymentMethod() != null
                        && ccNum.equals(this.getAccountManagementContext()
                                .getPaymentMethod().getObscureNumber())) {
                    ccNum = this.getAccountManagementContext()
                            .getPaymentMethod().getNumber().trim();
                }
                if (ccNum.startsWith("4") || ccNum.startsWith("5")) {
                    if (ccNum.length() != 16) {
                        this.decorateMessage("Visa/MM number is too short.",
                                message);
                    }

                } else if (ccNum.startsWith("3")) {
                    if (ccNum.length() != 15) {
                        this.decorateMessage(
                                "Amex number is too long or short.", message);
                    }

                } else {
                    this
                            .decorateMessage(
                                    "Invalid CC Number.  TeamClock.com accepts Visa, MC and Amex.",
                                    message);
                }
            }
        }

        if (message.length() > 0) {
            this.getSessionContext().setMessage(
                    "Your form is invalid. " + message.toString() + ".");
        }

    }

    public void announceAccountUpdate(SystemOwner systemOwner) throws Exception {

        StringBuffer msg = new StringBuffer();

        msg.append("System Key: " + systemOwner.getKey());
        msg.append("\nContact Name: " + systemOwner.getContactName());
        msg.append("\nContact Email: " + systemOwner.getContactEmail());

        msg.append("\nAccount: "+systemOwner.getAccount());
        msg.append("\nFrequency: "+systemOwner.getBillingFrequency());
        
        msg.append("\n\n");
        
        SystemMessageBean activate = SpringBeanBroker
                .getCommonMessage("requiredAccountUpdateComplete");

        QueuedMessage qm = new QueuedMessageBuilder().build(activate);
        /*
         * 2006-08-31 updated so it's sending to me.
         */
        qm.setToAddress("reid@fivesticks.com");
        qm.setToName("Reid Carlberg");
        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}", msg.toString()));

        QueuedMessageServiceDelegateFactory.factory.build(qm.getOwnerKey())
                .addSystemMessage(qm);

    }

    public void validateFieldHasText(String text, String error,
            StringBuffer message) {
        if (!hasText(text)) {
            decorateMessage(error, message);
        }
    }

    public void decorateMessage(String text, StringBuffer message) {
        if (message.length() > 0) {
            message.append(", ");
        }
        message.append(text);

    }

    /**
     * @return Returns the methodCancel.
     */
    public String getMethodCancel() {
        return methodCancel;
    }

    /**
     * @param methodCancel
     *            The methodCancel to set.
     */
    public void setMethodCancel(String methodCancel) {
        this.methodCancel = methodCancel;
    }

    /**
     * @return Returns the methodSubmit.
     */
    public String getMethodSubmit() {
        return methodSubmit;
    }

    /**
     * @param methodSubmit
     *            The methodSubmit to set.
     */
    public void setMethodSubmit(String methodSubmit) {
        this.methodSubmit = methodSubmit;
    }

    /**
     * @return Returns the paymentMethod.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod
     *            The paymentMethod to set.
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return Returns the accountManagementContext.
     */
    public AccountManagementContext getAccountManagementContext() {
        return accountManagementContext;
    }

    /**
     * @param accountManagementContext
     *            The accountManagementContext to set.
     */
    public void setAccountManagementContext(
            AccountManagementContext accountManagementContext) {
        this.accountManagementContext = accountManagementContext;
    }

    public Collection getCountries() {
        return new GeographicCollection().getCountries();

    }

    /**
     * @return Returns the account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            The account to set.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return Returns the billing.
     */
    public String getBilling() {
        return billing;
    }

    /**
     * @param billing
     *            The billing to set.
     */
    public void setBilling(String billing) {
        this.billing = billing;
    }

    /**
     * @return Returns the actionName.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName
     *            The actionName to set.
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * @return Returns the reasonExplanation.
     */
    public String getReasonExplanation() {
        return reasonExplanation;
    }

    /**
     * @param reasonExplanation
     *            The reasonExplanation to set.
     */
    public void setReasonExplanation(String reasonExplanation) {
        this.reasonExplanation = reasonExplanation;
    }
}
