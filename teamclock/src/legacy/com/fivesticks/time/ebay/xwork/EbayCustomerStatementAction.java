/*
 * Created on May 19, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.ebay.util.EbayItemCollectionStatistics;
import com.fivesticks.time.ebay.util.EbayItemCollectionStatisticsBuilder;

/**
 * @author Reid
 */
public class EbayCustomerStatementAction extends SessionContextAwareSupport {

    private Long target;
    
    private FstxCustomer customer;
    
    private Collection ready;
    
    private EbayItemCollectionStatistics readyStats;
    
    private Collection open;
    
    private EbayItemCollectionStatistics openStats;
    
    private boolean readyDiscounted;
    
    private boolean readyDeductions;
    
    private boolean openDiscounted;
    
    private boolean openDeductions;
    
    public String execute() throws Exception {
        
        FstxCustomer customer = CustomerServiceDelegate.factory.build(this.getSystemOwner()).getFstxCustomer(this.getTarget());

        this.setCustomer(customer);
        
        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(this.getSessionContext());
        
        EbayItemListContext context = new EbayItemListContext();
        
        context.setFilter(new EbayItemFilter());
        
        context.getFilter().setCustId(customer.getId());
        
//        context.setStatus(ItemStatusType.READY_TO_PAY_NET.getName());
        
        /*
         * RSC 2005-08-17 Updated 
         */
//        context.setOpenClosedType(OpenClosedType.READY_OR_WAITING);
        
        context.getFilter().setItemStatus(ItemStatusType.POSTED_TO_ACCOUNT.getName());
        
        new EbayItemFilterDecorator().build(context);
        

        this.setReady(sd.find(context.getFilter()));
        
        this.setReadyStats(new EbayItemCollectionStatisticsBuilder().build(this.getReady()));
        
        this.setReadyDiscounted(hasDiscount(this.getReady()));
        
        this.setReadyDeductions(hasDeduction(this.getReady()));
        
        
        context.getFilter().setItemStatus(null);
        
        context.setOpenClosedType(OpenClosedType.OPEN_NOT_READY);
        
        new EbayItemFilterDecorator().build(context);
        

        
        this.setOpen(sd.find(context.getFilter()));
        
        this.setOpenDiscounted(hasDiscount(this.getOpen()));
        
        
        this.setOpenDeductions(hasDeduction(this.getOpen()));
        
        
        this.setOpenStats(new EbayItemCollectionStatisticsBuilder().build(this.getOpen()));
        
        return this.getSuccess();
    }
    
    public boolean hasDiscount(Collection c) {
        
        boolean ret = false;
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            EbayItem element = (EbayItem) iter.next();
            if (element.getCommissionDiscount() != null && element.getCommissionDiscount().doubleValue() > 0.0) {
                ret = true;
                break;
            }
            
        }
        
        return ret;
    }
    
    public boolean hasDeduction(Collection c) {
        
        boolean ret = false;
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            EbayItem element = (EbayItem) iter.next();
            if (element.getCustomerNetDeduction() != null && element.getCustomerNetDeduction().doubleValue() > 0.0) {
                ret = true;
                break;
            }
            
        }
        
        return ret;
    }
    /**
     * @return Returns the customer.
     */
    public FstxCustomer getCustomer() {
        return customer;
    }
    /**
     * @param customer The customer to set.
     */
    public void setCustomer(FstxCustomer customer) {
        this.customer = customer;
    }
    /**
     * @return Returns the open.
     */
    public Collection getOpen() {
        return open;
    }
    /**
     * @param open The open to set.
     */
    public void setOpen(Collection open) {
        this.open = open;
    }
    /**
     * @return Returns the ready.
     */
    public Collection getReady() {
        return ready;
    }
    /**
     * @param ready The ready to set.
     */
    public void setReady(Collection ready) {
        this.ready = ready;
    }
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
    /**
     * @return Returns the openStats.
     */
    public EbayItemCollectionStatistics getOpenStats() {
        return openStats;
    }
    /**
     * @param openStats The openStats to set.
     */
    public void setOpenStats(EbayItemCollectionStatistics openStats) {
        this.openStats = openStats;
    }
    /**
     * @return Returns the readyStats.
     */
    public EbayItemCollectionStatistics getReadyStats() {
        return readyStats;
    }
    /**
     * @param readyStats The readyStats to set.
     */
    public void setReadyStats(EbayItemCollectionStatistics readyStats) {
        this.readyStats = readyStats;
    }
    /**
     * @return Returns the discounted.
     */
    public boolean isReadyDiscounted() {
        return readyDiscounted;
    }
    /**
     * @param discounted The discounted to set.
     */
    public void setReadyDiscounted(boolean discounted) {
        this.readyDiscounted = discounted;
    }
    /**
     * @return Returns the openDiscounted.
     */
    public boolean isOpenDiscounted() {
        return openDiscounted;
    }
    /**
     * @param openDiscounted The openDiscounted to set.
     */
    public void setOpenDiscounted(boolean openDiscounted) {
        this.openDiscounted = openDiscounted;
    }
    /**
     * @return Returns the openDeductions.
     */
    public boolean isOpenDeductions() {
        return openDeductions;
    }
    /**
     * @param openDeductions The openDeductions to set.
     */
    public void setOpenDeductions(boolean openDeductions) {
        this.openDeductions = openDeductions;
    }
    /**
     * @return Returns the readyDeductions.
     */
    public boolean isReadyDeductions() {
        return readyDeductions;
    }
    /**
     * @param readyDeductions The readyDeductions to set.
     */
    public void setReadyDeductions(boolean readyDeductions) {
        this.readyDeductions = readyDeductions;
    }
}
