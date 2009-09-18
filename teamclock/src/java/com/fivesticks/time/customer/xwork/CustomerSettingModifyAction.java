/*
 * Created on May 26, 2005 by REID
 */
package com.fivesticks.time.customer.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerSettingVO;

/**
 * @author REID
 */
public class CustomerSettingModifyAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private String customerSettingSubmit;

    private String customerSettingCancel;

    private boolean auctionCustomer;

    private boolean tracksAccountTransactions;

    private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    public String execute() throws Exception {

        if (this.getCustomerModifyContext().getTargetCustomer() == null) {
            return ERROR;
        }

        /*
         * If we are submiting setting...
         */
        if (StringUtils.hasText(this.getCustomerSettingSubmit())) {
            if (this.getCustomerSettingVO() == null) {
                return ERROR;

            }
            this.getCustomerSettingServiceDelegate().save(
                    this.getCustomerModifyContext().getTargetCustomer(),
                    this.getCustomerSettingVO());
        } else if (StringUtils.hasText(this.getCustomerSettingCancel())) {
            this.setCustomerSettingVO(null);
        } else {
            /*
             * If we havn't submit yet...
             */
            CustomerSettingVO vo = this.getCustomerSettingServiceDelegate()
                    .find(this.getCustomerModifyContext().getTargetCustomer());

            this.setCustomerSettingVO(vo);
            return INPUT;
        }

        return SUCCESS;
    }

    public boolean isAuctionCustomer() {
        return auctionCustomer;
    }

    public void setAuctionCustomer(boolean auctionCustomer) {
        this.auctionCustomer = auctionCustomer;
    }

    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public boolean isTracksAccountTransactions() {
        return tracksAccountTransactions;
    }

    public void setTracksAccountTransactions(boolean tracksAccountTransactions) {
        this.tracksAccountTransactions = tracksAccountTransactions;
    }

    public String getCustomerSettingSubmit() {
        return customerSettingSubmit;
    }

    public void setCustomerSettingSubmit(String customerSettingSubmit) {
        this.customerSettingSubmit = customerSettingSubmit;
    }

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

    public String getCustomerSettingCancel() {
        return customerSettingCancel;
    }

    public void setCustomerSettingCancel(String customerSettingCancel) {
        this.customerSettingCancel = customerSettingCancel;
    }

    public CustomerSettingVO getCustomerSettingVO() {
        return customerModifyContext.getCustomerSettingVO();
    }

    public void setCustomerSettingVO(CustomerSettingVO customerSettingVO) {
        customerModifyContext.setCustomerSettingVO(customerSettingVO);
    }
}
