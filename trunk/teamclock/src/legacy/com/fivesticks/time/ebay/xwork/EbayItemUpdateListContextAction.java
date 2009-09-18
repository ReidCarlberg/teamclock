/*
 * Created on Mar 28, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemFilter;

/**
 * @author Reid
 */
public class EbayItemUpdateListContextAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, CustomerModifyContextAware {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static Log log = LogFactory
            .getLog(EbayItemUpdateListContextAction.class);

    private EbayItemListContext ebayItemListContext;

    private CustomerModifyContext customerModifyContext;

    // private String targetStatus;

     private Long targetCustomerId;

    private String openClosed;

    private String view;
    
    private String submitReset;

    public String execute() throws Exception {

        /*
         * RSC 2005-08-13 
         */
        if (this.getEbayItemListContext().getFilter() == null)
            this.getEbayItemListContext().setFilter(new EbayItemFilter());
        
        if (this.getOpenClosed() != null
                && this.getOpenClosed().trim().length() > 0)
            this.getEbayItemListContext().setOpenClosedType(
                    OpenClosedType.getByName(this.getOpenClosed()));
        else
            this.getEbayItemListContext().setOpenClosedType(null);

        if (this.getView() != null && this.getView().trim().length() > 0)
            this.getEbayItemListContext().setViewType(
                    ViewType.getByName(this.getView()));
        else
            this.getEbayItemListContext().setViewType(null);

        // this.getEbayItemListContext().setStatus(this.getTargetStatus());
        // this.getEbayItemListContext().setCustomerId(this.getTargetCustomerId());

        if (this.getTargetCustomerId() != null) {
            this.getEbayItemListContext().getFilter().setCustId(this.getTargetCustomerId());
        }
        
        if (this.getEbayItemListContext().getFilter().getCustId() != null
                && this.getEbayItemListContext().getFilter().getCustId()
                        .longValue() > 0) {
            this.getCustomerModifyContext().setTargetCustomer(
                    CustomerServiceDelegate.factory.build(this.getSessionContext())
                            .getFstxCustomer(
                                    this.getEbayItemListContext().getFilter()
                                            .getCustId()));
        } else {
            this.getCustomerModifyContext().setTargetCustomer(null);
        }

        if (this.getSubmitReset() != null) {
            
            log.info("reseting the filters for item list...");
            
            this.getEbayItemListContext().setFilter(null);
            this.getCustomerModifyContext().setTargetCustomer(null);
            this.getCustomerModifyContext().setTargetContact(null);
        }
        
        return SUCCESS;

    }

    /**
     * @return Returns the customerModifyContextAware.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContextAware
     *            The customerModifyContextAware to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContextAware) {
        this.customerModifyContext = customerModifyContextAware;
    }

    /**
     * @return Returns the ebayItemListContext.
     */
    public EbayItemListContext getEbayItemListContext() {
        return ebayItemListContext;
    }

    /**
     * @param ebayItemListContext
     *            The ebayItemListContext to set.
     */
    public void setEbayItemListContext(EbayItemListContext ebayItemListContext) {
        this.ebayItemListContext = ebayItemListContext;
    }

    // /**
    // * @return Returns the targetCustomerId.
    // */
    // public Long getTargetCustomerId() {
    // return targetCustomerId;
    // }
    // /**
    // * @param targetCustomerId The targetCustomerId to set.
    // */
    // public void setTargetCustomerId(Long targetCustomerId) {
    // this.targetCustomerId = targetCustomerId;
    // }
    // /**
    // * @return Returns the targetStatus.
    // */
    // public String getTargetStatus() {
    // return targetStatus;
    // }
    // /**
    // * @param targetStatus The targetStatus to set.
    // */
    // public void setTargetStatus(String targetStatus) {
    // this.targetStatus = targetStatus;
    // }
    /**
     * @return Returns the openClosed.
     */
    public String getOpenClosed() {
        return openClosed;
    }

    /**
     * @param openClosed
     *            The openClosed to set.
     */
    public void setOpenClosed(String openClosed) {
        this.openClosed = openClosed;
    }

    /**
     * @return Returns the view.
     */
    public String getView() {
        return view;
    }

    /**
     * @param view
     *            The view to set.
     */
    public void setView(String view) {
        this.view = view;
    }

    /**
     * @return Returns the targetCustomerId.
     */
    public Long getTargetCustomerId() {
        return targetCustomerId;
    }

    /**
     * @param targetCustomerId The targetCustomerId to set.
     */
    public void setTargetCustomerId(Long targetCustomerId) {
        this.targetCustomerId = targetCustomerId;
    }

    /**
     * @return Returns the submitReset.
     */
    public String getSubmitReset() {
        return submitReset;
    }

    /**
     * @param submitReset The submitReset to set.
     */
    public void setSubmitReset(String submitReset) {
        this.submitReset = submitReset;
    }
}
