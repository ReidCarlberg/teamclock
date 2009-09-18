/*
 * Created on Jun 30, 2005 by Reid
 */
package com.fivesticks.time.account;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.account.util.BalanceDecorator;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class AccountTransactionServiceDelegateImpl extends
        AbstractServiceDelegate implements AccountTransactionServiceDelegate {

    private ObjectTransactionType objectTransactionType;

    private GenericDAO dao;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#delete(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public void delete(AccountTransaction prepayTransaction)
            throws AccountTransactionException {

        try {
            handleValidate(prepayTransaction);
        } catch (AbstractServiceDelegateException e) {
            throw new AccountTransactionException(e);
        }

        // this.getAccountTransactionDAO().delete(prepayTransaction);
        /*
         * 2007-01-09 reid
         */
        if (prepayTransaction.getActivityId() != null
                && prepayTransaction.getActivityId().longValue() != 0) {
            GenericDAO dao = GenericDAOFactory.factory.build();

            Activity activity = (Activity) dao.get(Activity.class,
                    prepayTransaction.getActivityId());

            if (activity != null) {
                activity.setAcctId(null);
                dao.save(activity);
            }
        }

        this.getDao().delete(prepayTransaction);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#find(com.fivesticks.time.prepay.PrepayTransactionFilter)
     */
    public Collection findAll(AccountTransactionCriteriaParameters filter)
            throws AccountTransactionException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(filter,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new AccountTransactionException(e1);
        }

        Collection ret = null;

        ret = this.getDao().find(filter);

        return ret;
    }

    public Collection find(SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException {

        AccountTransactionCriteriaParameters filter = new AccountTransactionCriteriaParameters();

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new AccountTransactionException(e1);
        }

        this.decorateForIdReadableAndTransactionType(filter,
                systemOwnerKeyAware);

        // Collection ret = null;
        //
        // try {
        // ret = this.getDao().find(filter);
        // catch (RuntimeException e) {
        // throw new AccountTransactionException(e);
        // }

        // return ret;

        return this.findAll(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#get(java.lang.Long)
     */
    public AccountTransaction get(Long id) throws AccountTransactionException {

        AccountTransaction ret = null;

        try {
            ret = (AccountTransaction) this.getDao().get(id);
        } catch (RuntimeException e) {
            throw new AccountTransactionException(e);
        }

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(ret,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new AccountTransactionException(e1);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#save(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public AccountTransaction save(AccountTransaction prepayTransaction)
            throws AccountTransactionException {

        /*
         * 2005-06-20 reid build into the database but vestigal at this point.
         */
        prepayTransaction.setCustomerId(new Long(-1));
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(prepayTransaction,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new AccountTransactionException(e1);
        }

        // AccountTransaction ret = null;

        this.getDao().save(prepayTransaction);
        return prepayTransaction;
    }

    protected AccountTransaction createTransaction(
            AccountTransactionTypeEnum type, SimpleDate date,
            IdReadable idReadable, String code, Double amount, String comments,
            String username, Long activityId)

    throws AccountTransactionException {
        AccountTransaction ret = new AccountTransaction();

        /*
         * reid 2005-06-20
         */
        this.decorateForIdReadableAndTransactionType(ret, idReadable);

        /*
         * rsc 2005-06-30
         */
        // ret.setObjectType(idReadable.getClass().getName());
        // ret.setObjectId(idReadable.getId());
        // ret
        // .setObjectTransactionType(objectTransactionType.getName());
        // ret.setCustomerId(customer.getId());
        ret.setAmount(amount);
        ret.setComments(comments);
        ret.setType(type.getName());
        ret.setEnteredBy(username);

        ret.setActivityId(activityId);

        ret.setArchived(false);
        ret.setDate(date.getDate());

        ret.setObjectTransactionCode(code);

        ret = this.save(ret);

        return ret;

    }

    // private AccountTransactionDAO accountTransactionDAO;

    // private PlatformTransactionManager transactionManager;

    // /**
    // * @return Returns the prepayTransactionDAO.
    // */
    // public AccountTransactionDAO getAccountTransactionDAO() {
    // return accountTransactionDAO;
    // }
    //
    // /**
    // * @return Returns the transactionManager.
    // */
    // public PlatformTransactionManager getTransactionManager() {
    // return transactionManager;
    // }
    //
    // /**
    // * @param accountTransactionDAO
    // * The prepayTransactionDAO to set.
    // */
    // public void setAccountTransactionDAO(
    // AccountTransactionDAO accountTransactionDAO) {
    // this.accountTransactionDAO = accountTransactionDAO;
    // }
    //
    // /**
    // * @param transactionManager
    // * The transactionManager to set.
    // */
    // public void setTransactionManager(
    // PlatformTransactionManager transactionManager) {
    // this.transactionManager = transactionManager;
    // }

    /**
     * @return Returns the objectTransactionType.
     */
    public ObjectTransactionType getObjectTransactionType() {
        return objectTransactionType;
    }

    /**
     * @param objectTransactionType
     *            The objectTransactionType to set.
     */
    public void setObjectTransactionType(
            ObjectTransactionType objectTransactionType) {
        this.objectTransactionType = objectTransactionType;
    }

    protected void decorateForIdReadableAndTransactionType(
            AccountTransaction target, IdReadable idReadableTarget) {

        target.setObjectType(idReadableTarget.getClass().getName());
        target.setObjectId(idReadableTarget.getId());
        target.setObjectTransactionType(this.getObjectTransactionType()
                .getName());

    }

    /**
     * @param customer
     * @param amount
     * @param comments
     * @param username
     * @throws AccountTransactionException
     */
    protected void validateSimpleTransaction(SystemOwnerKeyAware ownerKeyAware,
            Double amount, String username) throws AccountTransactionException {
        if (ownerKeyAware == null)
            throw new AccountTransactionException(
                    "OwnerKeyAware cannot be null.");

        if (amount.doubleValue() == 0.0)
            throw new AccountTransactionException(
                    "Cannot add a zero transaction.");

        if (username == null || username.trim().length() == 0) {
            throw new AccountTransactionException("Username must not be empty.");
        }

        try {
            // validate(customer);
            this.getOwnerKeyValidatorAndDecorator().validate(ownerKeyAware,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AccountTransactionException(e);
        }

    }

    protected Double getBalanceAtDate(SystemOwnerKeyAware systemOwnerKeyAware,
            SimpleDate lastDate) throws AccountTransactionException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AccountTransactionException(e);
        }

        AccountTransactionCriteriaParameters filter = new AccountTransactionCriteriaParameters();
        filter.setOwnerKey(this.getSystemOwner().getKey());
        // filter.setCustomerId(fstxCustomer.getId());
        filter.setArchived(new Boolean(false));
        filter.setDateRangeStop(lastDate);
        filter.setSortDateAscending(true);

        /*
         * 2005-06-20 Reid for object transactions
         */
        decorateForIdReadableAndTransactionType(filter, systemOwnerKeyAware);

        /*
         * 2005-06-30 Reid transaction type
         */
        filter.setObjectTransactionType(this.getObjectTransactionType()
                .getName());

        Collection openTransactions = this.findAll(filter);

        /*
         * 2006-06-01 RSC useful as a way of tracking balance as it accrues.
         */
        Double ret = new BalanceDecorator()
                .getAndDecorateBalance(openTransactions);

        return ret;

    }

    public Collection findUnarchived(SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException {

        try {
            // validate(fstxCustomer);
            this.getOwnerKeyValidatorAndDecorator().validate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new AccountTransactionException(e1);
        }

        AccountTransactionCriteriaParameters filter = new AccountTransactionCriteriaParameters();
        filter.setArchived(new Boolean(false));
        /*
         * 2005-06-20 reid
         */
        // filter.setCustomerId(fstxCustomer.getId());
        decorateForIdReadableAndTransactionType(filter, systemOwnerKeyAware);

        try {
            // decorate(filter);
            this.getOwnerKeyValidatorAndDecorator().decorate(filter,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AccountTransactionException(e);
        }

        return this.findAll(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#add(com.fivesticks.time.customer.FstxCustomer,
     *      java.lang.Double, java.lang.String, java.lang.String)
     */
    public AccountTransaction increase(SystemOwnerKeyAware customer,
            Double amount, String comments, String username, String code)
            throws AccountTransactionException {

        validateSimpleTransaction(customer, amount, username);

        AccountTransaction ret = createTransaction(
                AccountTransactionTypeEnum.ADD_VALUE, SimpleDate.factory
                        .buildMidnight(), customer, code, amount, comments,
                username, null);

        /*
         * not returning anything. 2007-01-09 that's changed.
         */
        return ret;
    }

    public AccountTransaction increase(SystemOwnerKeyAware customer,
            Double amount, String comments, String username)
            throws AccountTransactionException {

        AccountTransaction ret = increase(customer, amount, comments, username,
                null);
        return ret;
    }

    public AccountTransaction adjust(SystemOwnerKeyAware customer,
            Double amount, String comments, String username)
            throws AccountTransactionException {
        AccountTransaction ret = adjust(customer, amount, comments, username,
                null);
        return ret;
    }

    public AccountTransaction adjust(SystemOwnerKeyAware customer,
            Double amount, String comments, String username, String code)
            throws AccountTransactionException {
        validateSimpleTransaction(customer, amount, username);
        AccountTransaction ret = adjustForDate(SimpleDate.factory.build(),
                customer, amount, comments, username, code);
        return ret;
    }

    private AccountTransaction adjustForDate(SimpleDate date,
            SystemOwnerKeyAware customer, Double amount, String comments,
            String username, String code) throws AccountTransactionException {
        AccountTransaction ret = createTransaction(
                AccountTransactionTypeEnum.BALANCE_SET, date, customer, code,
                amount, comments, username, null);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.account.AccountTransactionServiceDelegate#archiveByDateRange(com.fivesticks.time.customer.FstxCustomer,
     *      com.fstx.stdlib.common.simpledate.SimpleDate,
     *      com.fstx.stdlib.common.simpledate.SimpleDate)
     */
    public void archiveThroughDate(SystemOwnerKeyAware systemOwnerKeyAware,
            SimpleDate dateRangeEnd, String username)
            throws AccountTransactionException {

        try {
            // validate the customer
            // validate(fstxCustomer);
            this.getOwnerKeyValidatorAndDecorator().validate(
                    systemOwnerKeyAware, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new AccountTransactionException(e);
        }

        // determine balance as of that day.
        dateRangeEnd = SimpleDate.factory.buildMidnight(dateRangeEnd.getDate());
        dateRangeEnd.advanceDay();
        dateRangeEnd.advanceMilliseconds(-1);
        Double balanceAsOf = this.getBalanceAtDate(systemOwnerKeyAware,
                dateRangeEnd);

        // find all unarchived through that date.
        AccountTransactionCriteriaParameters filter = new AccountTransactionCriteriaParameters();
        // filter.setCustomerId(systemOwnerKeyAware.getId());

        /*
         * 2005-06-20 reid
         */
        decorateForIdReadableAndTransactionType(filter, systemOwnerKeyAware);

        filter.setArchived(new Boolean(false));
        filter.setDateRangeStop(dateRangeEnd);

        // mark as archived.
        Collection matching = this.findAll(filter);

        for (Iterator iter = matching.iterator(); iter.hasNext();) {
            AccountTransaction element = (AccountTransaction) iter.next();

            element.setArchived(true);
            this.save(element);

        }

        // insert balance set as of that day
        this.adjustForDate(dateRangeEnd, systemOwnerKeyAware, balanceAsOf,
                "Archived through " + dateRangeEnd.getMmddyyyy()
                        + ".  Auto Balance Adjustment.", username, null);

    }

    public Double getCurrentBalance(SystemOwnerKeyAware fstxCustomer)
            throws AccountTransactionException {

        SimpleDate endOfToday = SimpleDate.factory.buildMidnight();
        endOfToday.advanceDay();
        endOfToday.advanceMilliseconds(-1);
        return this.getBalanceAtDate(fstxCustomer, endOfToday);

    }

    // private SystemOwner systemOwner;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#save(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public AccountTransaction save(AccountTransaction prepayTransaction,
            SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException {

        decorateForIdReadableAndTransactionType(prepayTransaction,
                systemOwnerKeyAware);

        /*
         * 2006-06-01 RSC check to see that there are not other transactions on
         * this date
         */
        if (prepayTransaction.getType().equals(
                AccountTransactionTypeEnum.BALANCE_SET.getName())) {
            validateBalanceSetTransaction(prepayTransaction,
                    systemOwnerKeyAware);
        } else {
            validateNonBalanceSetTransaction(prepayTransaction,
                    systemOwnerKeyAware);
        }
        save(prepayTransaction);

        return prepayTransaction;
    }

    protected void validateBalanceSetTransaction(
            AccountTransaction prepayTransaction,
            SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException {

        AccountTransactionCriteriaParameters p = new AccountTransactionCriteriaParameters();

        this.decorateForIdReadableAndTransactionType(p, systemOwnerKeyAware);

        SimpleDate dStart = SimpleDate.factory.buildMidnight(prepayTransaction
                .getDate());

        p.setDateRangeStart(dStart);

        SimpleDate dEnd = SimpleDate.factory.buildEndOfDay(p.getDate());

        p.setDateRangeStop(dEnd);

        Collection m = this.findAll(p);

        if (m != null && m.size() > 0) {
            throw new AccountTransactionException(
                    "already have a transaction on this date.");
        }
    }

    protected void validateNonBalanceSetTransaction(
            AccountTransaction prepayTransaction,
            SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException {

        AccountTransactionCriteriaParameters p = new AccountTransactionCriteriaParameters();

        this.decorateForIdReadableAndTransactionType(p, systemOwnerKeyAware);

        SimpleDate dStart = SimpleDate.factory.buildMidnight(prepayTransaction
                .getDate());

        p.setDateRangeStart(dStart);

        SimpleDate dEnd = SimpleDate.factory.buildEndOfDay(p.getDate());

        p.setDateRangeStop(dEnd);

        p.setType(AccountTransactionTypeEnum.BALANCE_SET.getName());

        Collection m = this.findAll(p);

        if (m != null && m.size() > 0) {
            throw new AccountTransactionException(
                    "balance set exists on this date.");
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#use(com.fivesticks.time.customer.FstxCustomer,
     *      java.lang.Double, java.lang.String, java.lang.String)
     */
    public AccountTransaction decrease(SystemOwnerKeyAware customer,
            Double amount, String comments, String username)
            throws AccountTransactionException {
        validateSimpleTransaction(customer, amount, username);

        AccountTransaction ret = createTransaction(
                AccountTransactionTypeEnum.USE_VALUE, SimpleDate.factory
                        .buildMidnight(), customer, null, amount, comments,
                username, null);

        return ret;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionServiceDelegate#use(com.fivesticks.time.customer.FstxCustomer,
     *      java.lang.Double, java.lang.String, java.lang.String)
     */
    public AccountTransaction decrease(SystemOwnerKeyAware customer,
            Double amount, String comments, String username, Long activityId)
            throws AccountTransactionException {
        validateSimpleTransaction(customer, amount, username);

        /*
         * 2007-01-09 should make sure that this activity id isn't posted more
         * than once.
         */
        Collection c = this.getDao().find(" from " + AccountTransaction.class.getName() 
                + " where acctActivityId = " + activityId);
        
        if (c != null && c.size() > 0) {
            return null;
        }

        AccountTransaction ret = createTransaction(
                AccountTransactionTypeEnum.USE_VALUE, SimpleDate.factory
                        .buildMidnight(), customer, null, amount, comments,
                username, activityId);

        return ret;

    }

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
}