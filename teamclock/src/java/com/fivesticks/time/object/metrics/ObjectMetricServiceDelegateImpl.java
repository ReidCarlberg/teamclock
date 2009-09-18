/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.object.metrics;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

/**
 * @author REID
 */
public class ObjectMetricServiceDelegateImpl extends AbstractServiceDelegate
        implements ObjectMetricServiceDelegate {

    // private SystemOwner systemOwner;
    //
    // private ObjectMetricDAO dao;
    //
    // private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.metric.SystemOwnerMetricServiceDelegate#getMetrics()
     */
    public Collection getMetrics() throws ObjectMetricServiceDelegateException {

        ObjectMetricFilter filter = new ObjectMetricFilter();
        filter.setOwnerKey(this.getSystemOwner().getKey());

        Collection ret = null;

        ret = this.getDao().find(filter);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.metric.SystemOwnerMetricServiceDelegate#getMetric(com.fivesticks.time.systemowner.metric.SystemOwnerMetricTypeEnum)
     */
    public ObjectMetric getMetric(Class clazz, Long id,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws 
            ObjectMetricNotFoundException {

        ObjectMetric ret = null;

        ObjectMetricFilter filter = new ObjectMetricFilter();
        filter.setObjectType(clazz.getName());
        filter.setObjectId(id);
        filter.setOwnerKey(this.getSystemOwner().getKey());
        filter.setMetricType(systemOwnerMetricTypeEnum.getName());

        Collection r;
        r = this.getDao().find(filter);
        if (r != null && r.size() == 1) {
            ret = (ObjectMetric) r.toArray()[0];
        } else {
            throw new ObjectMetricNotFoundException(systemOwnerMetricTypeEnum
                    .getName());
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.metric.SystemOwnerMetricServiceDelegate#getMetricValue(com.fivesticks.time.systemowner.metric.SystemOwnerMetricTypeEnum)
     */
    public String getMetricValue(Class clazz, Long id,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws 
            ObjectMetricNotFoundException {

        ObjectMetric systemOwnerMetric = this.getMetric(clazz, id,
                systemOwnerMetricTypeEnum);

        return systemOwnerMetric.getMetricValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.metric.SystemOwnerMetricServiceDelegate#setValue(com.fivesticks.time.systemowner.metric.SystemOwnerMetricTypeEnum,
     *      java.lang.String)
     */
    public ObjectMetric setValue(Class clazz, Long id,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum, String metricValue)
            throws ObjectMetricServiceDelegateException {

        ObjectMetric current = null;

        try {
            current = this.getMetric(clazz, id, systemOwnerMetricTypeEnum);
        }  catch (ObjectMetricNotFoundException e) {
            current = new ObjectMetric();
            current.setObjectType(clazz.getName());
            current.setObjectId(id);
            current.setMetricType(systemOwnerMetricTypeEnum.getName());
            try {
                this.getOwnerKeyValidatorAndDecorator().decorate(current,
                        this.getSystemOwner());
            } catch (OwnerKeyValidatorAndDecoratorException e1) {
                throw new ObjectMetricServiceDelegateException(e1);
            }
        }

        current.setMetricValue(metricValue);

        this.getDao().save(current);

        return current;
    }

    public void delete(ObjectMetric systemOwnerMetric)
            throws ObjectMetricServiceDelegateException {
        try {
            this.handleValidate(systemOwnerMetric);
        } catch (AbstractServiceDelegateException e) {
            throw new ObjectMetricServiceDelegateException(e);
        }
        this.getDao().delete(systemOwnerMetric);
    }



    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#getMetric(com.fivesticks.time.common.IdReadable,
     *      com.fivesticks.time.metrics.ObjectMetricTypeEnum)
     */
    public ObjectMetric getMetric(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException {
        return this.getMetric(idReadable.getClass(), idReadable.getId(),
                systemOwnerMetricTypeEnum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#getMetricValue(com.fivesticks.time.common.IdReadable,
     *      com.fivesticks.time.metrics.ObjectMetricTypeEnum)
     */
    public String getMetricValue(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException {
        return this.getMetricValue(idReadable.getClass(), idReadable.getId(),
                systemOwnerMetricTypeEnum);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#setValue(com.fivesticks.time.common.IdReadable,
     *      com.fivesticks.time.metrics.ObjectMetricTypeEnum, java.lang.String)
     */
    public ObjectMetric setValue(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum, String metricValue)
            throws ObjectMetricServiceDelegateException {
        return this.setValue(idReadable.getClass(), idReadable.getId(),
                systemOwnerMetricTypeEnum, metricValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#getMetrics(com.fivesticks.time.common.IdReadable)
     */
    public Collection getMetrics(IdReadable idReadable)
            throws ObjectMetricServiceDelegateException {

        ObjectMetricFilter filter = new ObjectMetricFilter();
        filter.setObjectType(idReadable.getClass().getName());
        filter.setObjectId(idReadable.getId());

//        handleDecoration(filter);

        return handleFind(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate#getMatchingObjectsByMetricValue(java.lang.Class,
     *      com.fivesticks.time.customer.CustomerSettingType, java.lang.String)
     */
    public Collection getMatchingObjectsByMetricValue(Class clazz,
            CustomerSettingType is_auction_customer, String string)
            throws ObjectMetricServiceDelegateException {

        ObjectMetricFilter filter = new ObjectMetricFilter();
        filter.setObjectType(clazz.getName());
        filter.setMetricType(is_auction_customer.getName());
        filter.setMetricValue(string);

//        handleDecoration(filter);

        return handleFind(filter);

    }

    private Collection handleFind(ObjectMetricFilter filter)
            throws ObjectMetricServiceDelegateException {

        try {
            this.handleDecorate(filter);
        } catch (AbstractServiceDelegateException e) {
            throw new ObjectMetricServiceDelegateException(e);
        }
        
        return this.getDao().find(filter);
    }

    public void save(ObjectMetric target) throws ObjectMetricServiceDelegateException {
        this.getDao().save(target);
    }

//    private void handleDecoration(SystemOwnerKeyAware target)
//            throws ObjectMetricServiceDelegateException {
//        try {
//            this.getOwnerKeyValidatorAndDecorator().decorate(target,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ObjectMetricServiceDelegateException(e);
//        }
//    }
}