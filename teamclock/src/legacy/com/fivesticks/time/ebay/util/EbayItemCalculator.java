/*
 * Created on May 19, 2005 by Reid
 */
package com.fivesticks.time.ebay.util;

import java.text.DecimalFormat;
import java.util.Collection;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.ebay.CommissionDiscountType;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.ItemShippingServiceDelegate;
import com.fivesticks.time.ebay.ItemShippingStats;
import com.fivesticks.time.ebay.ItemShippingStatsBuilder;
import com.fivesticks.time.ebay.setup.commission.SimpleCommission;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class EbayItemCalculator {

    DecimalFormat df = new DecimalFormat("#.##");

//    private Double convertToDecimal(Double doub) {
//
//        Double ret = new Double(df.format(doub.doubleValue()));
//
//        return ret;
//    }

    private double convertToDecimal(double doub) {

        double ret = new DoubleRounder().get2(doub);

        return ret;
    }

    public void handleCalculate(EbayItem target, SystemOwner systemOwner, long ebayDefaultCommission) throws Exception {
        
        FstxCustomer customer = CustomerServiceDelegate.factory.build(systemOwner).getFstxCustomer(target.getCustId());
        
        handleCalculate(target,systemOwner,customer,ebayDefaultCommission);
        
    }
    
    public void handleCalculate(EbayItem target, SystemOwner systemOwner, FstxCustomer customer, long ebayDefaultCommission) throws Exception {
        
        Long over = null;

        if (target.getSimpleCommissionId() != null) {
            //2005-08-10 Each item should be able to have it's own commission if necessary
            over = target.getSimpleCommissionId();
        } else {
	        try {
		        over = new Long(ObjectMetricServiceDelegate.factory.build(
		                systemOwner).getMetricValue(
		                customer,
		                CustomerSettingType.AUCTION_COMMISSION_OVERRIDE));
	        } catch (Exception e) {
	            
	        }
        }
	     
        if (over == null || over.longValue() == 0) {
            //over = new Long(MasterSettingsBroker.singleton.getSettings(systemOwner).getEbayDefaultCommission());
            over = new Long(ebayDefaultCommission);
        }
        
        SimpleCommission comm = SimpleCommissionServiceDelegate.factory.build(systemOwner).get(over);
        
        handleCalculate(target, systemOwner, comm);
        
    }
    public void handleCalculate(EbayItem target, SystemOwner systemOwner, SimpleCommission comm)
            throws Exception {

        ItemShippingStats stats = this.getStats(target, systemOwner);

        if (target.getLockedShipping() != null
                && target.getLockedShipping().booleanValue()) {
        } else {

            target.setShippingWeight(new Integer((int) stats.getWeight()));

            target.setHandlingCharge(new Double(stats.getHandling()));

        }

        if (target.getLockedCosts() != null
                && target.getLockedCosts().booleanValue())
            return;

        double discountAmount = 0.0;
        double commission = 0.0;
        double ebayFinalValue = 0.0;
        double customerNet = 0.0;

        if (target.getPriceFinal() != null
                && target.getPriceFinal().doubleValue() > 0) {
            /*
             * determine the commission
             */
            double priceFinal = target.getPriceFinal().doubleValue();

            commission = new CommissionCalculator().getCommission(new CommissionBuilder().build(comm),priceFinal);
            /*
             * determine ebay costs
             */
            if (priceFinal <= 25.0) {
                ebayFinalValue = priceFinal * .0525;
            } else if (priceFinal <= 1000.0) {
                ebayFinalValue = 1.31 + ((priceFinal - 25.0) * .0275);
            } else {
                ebayFinalValue = 1.31 + 26.81 + ((priceFinal - 1000.0) * .0150);
            }

            commission = this.convertToDecimal(commission);

            ebayFinalValue = this.convertToDecimal(ebayFinalValue);
        }

        /*
         * handle the discounts
         */
        if (commission > 0.0 && target.getCommissionDiscountMethod() != null
                && target.getCommissionDiscountMethod().trim().length() > 0) {

            CommissionDiscountType type = CommissionDiscountType
                    .getByName(target.getCommissionDiscountMethod());

            if (type == CommissionDiscountType.FLAT) {
                discountAmount = target.getCommissionDiscountBase()
                        .doubleValue();
            } else if (type == CommissionDiscountType.PERCENT) {
                /*
                 * 2005-06-03 RSC We assume that the percent is entered in whole
                 * numbers 20.0 = 20%
                 */

                double discountRate = target.getCommissionDiscountBase()
                        .doubleValue() / 100.0;

                discountAmount = discountRate * commission;
            } else {
                //nothing
            }

            discountAmount = this.convertToDecimal(discountAmount);

            commission = commission - discountAmount;

        }

        target.setCommission(new Double(this.convertToDecimal(commission)));

        target.setCommissionDiscount(new Double(this.convertToDecimal(discountAmount)));

        target.setFeeEbayFinal(new Double(this.convertToDecimal(ebayFinalValue)));

        if (target.getPriceFinal() != null
                && target.getPriceFinal().doubleValue() > 0) {

            customerNet = target.getPriceFinal().doubleValue()
                    - target.getCommission().doubleValue()
                    + target.getPrepayAmount().doubleValue();

            /*
             * make sure we handle customer net deductions.
             */
            if (target.getCustomerNetDeduction() != null
                    && target.getCustomerNetDeduction().doubleValue() > 0.0) {

                customerNet = customerNet - target.getCustomerNetDeduction().doubleValue();
                
            }
        }

        double gross = target.getCommission().doubleValue();

        if (gross > 0.0) {
            if (target.getShippingCharge() != null)
                gross = gross + target.getShippingCharge().doubleValue();

            gross = gross - stats.getShipping();

            if (target.getFeeEbayListing() != null)
                gross = gross - target.getFeeEbayListing().doubleValue();

            if (target.getFeeEbayFinal() != null)
                gross = gross - target.getFeeEbayFinal().doubleValue();

            if (target.getFeePayPal() != null)
                gross = gross - target.getFeePayPal().doubleValue();

            if (target.getFeeOther() != null)
                gross = gross - target.getFeeOther().doubleValue();
        }
        target.setCustomerNet(new Double(this.convertToDecimal(customerNet)));

        target.setGrossProfit(new Double(this.convertToDecimal(this.convertToDecimal(gross))));

    }

    private ItemShippingStats getStats(EbayItem target, SystemOwner systemOwner)
            throws Exception {
        //shipping weight
        Collection shipping = ItemShippingServiceDelegate.factory.build(
                systemOwner).findByEbayItem(target);

        ItemShippingStats stats = ItemShippingStatsBuilder.build(shipping);

        return stats;

    }
}