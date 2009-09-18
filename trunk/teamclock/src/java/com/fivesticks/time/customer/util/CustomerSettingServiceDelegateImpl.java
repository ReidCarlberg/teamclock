package com.fivesticks.time.customer.util;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * 2005-10-6 This is a VO to contain all the settings.
 * 
 * @author Nick
 */
public class CustomerSettingServiceDelegateImpl implements
        CustomerSettingServiceDelegate {

    private ObjectMetricServiceDelegate objectMetricServiceDelegate;

    private SystemOwner systemOwner;

    public CustomerSettingServiceDelegateImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.util.CustomerSettingServiceDelegate#find(com.fivesticks.time.customer.FstxCustomer)
     */
    public CustomerSettingVO find(Customer customer)
            throws ObjectMetricServiceDelegateException {
        ObjectMetricServiceDelegate omsd = this
                .getObjectMetricServiceDelegate();

        CustomerSettingVO customerSettingVO = new CustomerSettingVO();

        ObjectMetric omEmail;

        try {
            omEmail = omsd.getMetric(customer,
                    CustomerSettingType.ACCOUNT_BALANCE_NOTIFY_EMAIL_ADDRESS);
        } catch (ObjectMetricNotFoundException e) {
            omEmail = null;
        }

        ObjectMetric omFreq;
        try {
            omFreq = omsd.getMetric(customer,
                    CustomerSettingType.ACCOUNT_BALANCE_NOTIFY_FREQUENCY);
        } catch (ObjectMetricNotFoundException e) {
            omFreq = null;
        }

        if (omEmail != null) {
            customerSettingVO.setAccountBalanceNotifyEmailAddress(omEmail
                    .getMetricValue());
        }
        if (omFreq != null) {
            customerSettingVO.setAccountBalanceNotifyFrequency(omFreq
                    .getMetricValue());
        }
        return customerSettingVO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.util.CustomerSettingServiceDelegate#save(com.fivesticks.time.customer.FstxCustomer,
     *      com.fivesticks.time.customer.util.CustomerSettingVO)
     */
    public void save(Customer customer, CustomerSettingVO vo)
            throws ObjectMetricServiceDelegateException {

        ObjectMetricServiceDelegate omsd = this
                .getObjectMetricServiceDelegate();

       // if (StringUtils.hasText(vo.getAccountBalanceNotifyEmailAddress())) {
            omsd.setValue(customer,
                    CustomerSettingType.ACCOUNT_BALANCE_NOTIFY_EMAIL_ADDRESS,
                    vo.getAccountBalanceNotifyEmailAddress());
      //  }

        if (vo.getAccountBalanceNotifyFrequency() != null) {
            omsd.setValue(customer,
                    CustomerSettingType.ACCOUNT_BALANCE_NOTIFY_FREQUENCY, vo
                            .getAccountBalanceNotifyFrequency().toString());
       }else{
           omsd.setValue(customer,
                    CustomerSettingType.ACCOUNT_BALANCE_NOTIFY_FREQUENCY,"");
           
       }
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public ObjectMetricServiceDelegate getObjectMetricServiceDelegate() {
        if (objectMetricServiceDelegate instanceof SystemOwnerAware) {
            SystemOwnerAware soa = (SystemOwnerAware) objectMetricServiceDelegate;
            soa.setSystemOwner(this.systemOwner);

        }
        return objectMetricServiceDelegate;
    }

    public void setObjectMetricServiceDelegate(
            ObjectMetricServiceDelegate objectMetricServiceDelegate) {
        this.objectMetricServiceDelegate = objectMetricServiceDelegate;
    }

}
