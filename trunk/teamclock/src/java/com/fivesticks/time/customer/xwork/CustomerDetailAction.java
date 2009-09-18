/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerSettingVO;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateException;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;

/**
 * @author shuji
 */
public class CustomerDetailAction extends SessionContextAwareSupport implements
        CustomerModifyContextAware {

//    private FstxTimeSettings fs;

    private CustomerModifyContext customerModifyContext;

    private Long target;

    private Customer customer;

    private Lookup status;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride("customer");
        
        CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory.build(this
                .getSessionContext());

        if (this.getTarget() != null) {
            customer = customerBD.getFstxCustomer(this.getTarget());
            this.customerModifyContext.setTargetCustomer(customer);
            CustomerSettingVO vo = this.getCustomerSettingServiceDelegate()
                    .find(this.getCustomerModifyContext().getTargetCustomer());

            this.setCustomerSettingVO(vo);
            
        } else if (this.customerModifyContext.getTargetCustomer() != null) {
            /*
             * Refresh the customer
             * 
             * 2005-09-22 RSC Don't have to worry about refreshing everything in
             * there.
             */
            Long id = this.customerModifyContext.getTargetCustomer().getId();
            customer = customerBD.getFstxCustomer(id);
            this.customerModifyContext.setTargetCustomer(customer);

            CustomerSettingVO vo = this.getCustomerSettingServiceDelegate()
                    .find(this.getCustomerModifyContext().getTargetCustomer());

            this.setCustomerSettingVO(vo);

        } else {
            this.addActionError("Unknown Customer.");
            return ERROR;
        }

//        this.getCustomerModifyContext().setContacts(
//                ContactServiceDelegateFactory.factory.build(
//                        this.getSessionContext().getSystemOwner())
//                        .getByCustomer(
//                                this.getCustomerModifyContext()
//                                        .getTargetCustomer()));

        /*
         * 9/1/06 reid added.
         */
        this.getCustomerModifyContext().setContacts(
                ContactV2ServiceDelegateFactory.factory.build(
                        this.getSessionContext().getSystemOwner())
                        .getByCustomer(
                                this.getCustomerModifyContext()
                                        .getTargetCustomer()));
        
        this.getCustomerModifyContext().setTargetCustomer(customer);
        
        this.getCustomerModifyContext().setDisplayCustomer(
                new CustomerDisplayWrapperBuilder().buildSingle(this
                        .getSessionContext(), customer));

        this.getCustomerModifyContext().setProjects(
                ProjectServiceDelegateFactory.factory.build(this.getSystemOwner())
                        .getAllForCustomer(customer));

        this.getCustomerModifyContext().setTimeAccountBalance(
                CustomerAccountTransactionServiceDelegate.factory
                        .buildTimeAccount(this.getSystemOwner())
                        .getCurrentBalance(
                                this.getCustomerModifyContext()
                                        .getTargetCustomer()));

        return SUCCESS;
    }

    public Collection getComments() {
        try {
            return ObjectCommentServiceDelegateFactory.factory.build(
                    this.getSessionContext().getSystemOwner()).getComments(
                    this.getCustomer());
        } catch (ObjectCommentServiceDelegateException e) {
            this.addActionError("Error Finding Comments");
            e.printStackTrace();
            return null;

        }
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return Returns the status.
     */
    public Lookup getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(Lookup status) {
        this.status = status;
    }

    public CustomerSettingVO getCustomerSettingVO() {
        return customerModifyContext.getCustomerSettingVO();
    }

    public void setCustomerSettingVO(CustomerSettingVO customerSettingVO) {
        customerModifyContext.setCustomerSettingVO(customerSettingVO);
    }

    private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    public CustomerSettingServiceDelegate getCustomerSettingServiceDelegate() {
        if (this.customerSettingServiceDelegate == null) {
            this.customerSettingServiceDelegate = CustomerSettingServiceDelegateFactory.factory
                    .build(this.getSystemOwner());
        }

        return this.customerSettingServiceDelegate;
    }

    public void setCustomerSettingServiceDelegate(
            CustomerSettingServiceDelegate customerSettingServiceDelegate) {
        this.customerSettingServiceDelegate = customerSettingServiceDelegate;
    }
}