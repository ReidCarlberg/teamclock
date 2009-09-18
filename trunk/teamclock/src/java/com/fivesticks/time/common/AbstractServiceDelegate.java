/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.common;

import org.springframework.transaction.PlatformTransactionManager;

import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.common.util.Rounder;
import com.fivesticks.time.common.util.TimezoneDateTimeResolver;
import com.fivesticks.time.systemowner.SystemOwnerAwareSupport;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public abstract class AbstractServiceDelegate extends SystemOwnerAwareSupport
        implements TransactionManagerAware {

    private GenericDAO dao;

    private PlatformTransactionManager transactionManager;

    private TimezoneDateTimeResolver resolverUtil;

    private Rounder rounderUtil;

    private String timezone;

    private String rounder;

    /**
     * @return Returns the dao.
     */
    public GenericDAO getDao() {
        return dao;
    }

    /**
     * @param dao
     *            The dao to set.
     */
    public void setDao(GenericDAO dao) {
        this.dao = dao;
    }

    protected void handleDecorate(SystemOwnerKeyAware target)
            throws AbstractServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AbstractServiceDelegateException(e);
        }
    }

    protected void handleValidate(SystemOwnerKeyAware target)
            throws AbstractServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AbstractServiceDelegateException(e);
        }
    }

    protected void handleDecorateRuntime(SystemOwnerKeyAware target) {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new RuntimeException(e);
        }
    }

    protected void handleValidateRuntime(SystemOwnerKeyAware target)
             {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(target,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Returns the platformTransactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null)
            throw new RuntimeException(
                    "Requested platform transaction manager but it is null");

        return transactionManager;
    }

    /**
     * @param platformTransactionManager
     *            The platformTransactionManager to set.
     */
    public void setTransactionManager(
            PlatformTransactionManager platformTransactionManager) {
        this.transactionManager = platformTransactionManager;
    }

    /**
     * @return Returns the resolver.
     */
    public TimezoneDateTimeResolver getResolverUtil() {
        if (this.resolverUtil == null) {
            resolverUtil = new TimezoneDateTimeResolver();
            resolverUtil.setTimezone(this.getTimezone());
        }
        return resolverUtil;
    }

    /**
     * @return Returns the rounder.
     */
    public Rounder getRounderUtil() {
        if (rounderUtil == null) {
            rounderUtil = DateTimeRounderFactory.factory.build(this.getRounder());
        }
        return rounderUtil;
    }

    /**
     * @return Returns the timeclockRounder.
     */
    public String getRounder() {
        if (rounder == null) {
            throw new RuntimeException("Rounder is unexpectedly null");
        }
        return rounder;
    }

    /**
     * @param timeclockRounder
     *            The timeclockRounder to set.
     */
    public void setRounder(String timeclockRounder) {
        this.rounder = timeclockRounder;
    }

    /**
     * @return Returns the timezone.
     */
    public String getTimezone() {
        if (timezone == null) {
            throw new RuntimeException("Timezone is unexpectedly null");
        }
        return timezone;
    }

    /**
     * @param timezone
     *            The timezone to set.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public SimpleDate resolveAndRound(SimpleDate target) {
        return this.getRounderUtil().round(
                this.getResolverUtil().resolve(target));
    }
    

}
