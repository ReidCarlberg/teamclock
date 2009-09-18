/*
 * Created on Nov 17, 2004 by Reid
 */
package com.fivesticks.time.account.xwork;

import java.util.Collection;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionDeleteCommand;
import com.fivesticks.time.account.AccountTransactionDeleteCommandBuilder;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.account.AccountTransactionTypeEnum;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class ModifyAccountTransactionAction extends
        AccountTransactionContextAwareSupport implements
        ModifyAccountTransactionContextAware {

    //    private SessionContext sessionContext;

    private ModifyAccountTransactionContext modifyAccountTransactionContext;

    //    private AccountTransactionContext accountTransactionContext;

    private String submitTxn;

    private String cancelTxn;

    private String deleteTxn;

//    private String dateString;

    private Long target;

    private AccountTransaction accountTransaction;

    public String execute() throws Exception {

        if (this.getAccountTransactionContext().getSystemOwnerKeyAware() == null) {
            return ERROR;
        }

        if (this.getCancelTxn() != null) {
            return SUCCESS;
        }

        if (this.getTarget() != null) {
            AccountTransaction temp = null;

            try {
                temp = AccountTransactionServiceDelegateFactory.factory.build(
                        this.getSessionContext().getSystemOwner(),
                        this.getAccountTransactionContext()
                                .getObjectTransactionType()).get(
                        this.getTarget());
            } catch (AccountTransactionException e) {
                //do nothing;
            }

            if (temp == null) {
                return ERROR;
            }

            this.getModifyAccountTransactionContext().setAccountTransaction(
                    temp);

            this.setAccountTransaction(temp);
            return INPUT;
        } else if (this.getTarget() == null && this.getSubmitTxn() == null
                && this.getDeleteTxn() == null) {
            this.getModifyAccountTransactionContext().setAccountTransaction(
                    null);
            this.setAccountTransaction(new AccountTransaction());
            this.getAccountTransaction().setEnteredBy(
                    this.getSessionContext().getUser().getUsername());
            return INPUT;
        } else if (this.getDeleteTxn() != null
                && this.getModifyAccountTransactionContext()
                        .getAccountTransaction() != null) {
            AccountTransactionDeleteCommand command = AccountTransactionDeleteCommandBuilder.singleton
                    .build(this.getSessionContext().getSystemOwner(), this
                            .getModifyAccountTransactionContext()
                            .getAccountTransaction());
            DeleteContext deleteContext = DeleteContextFactory.factory.build(command);
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        }

        validate();

        if (this.hasActionErrors()) {
            return INPUT;
        }

        AccountTransaction toSave = new AccountTransaction();

        if (this.getModifyAccountTransactionContext().getAccountTransaction() != null) {
            toSave = this.getModifyAccountTransactionContext()
                    .getAccountTransaction();
            this.getModifyAccountTransactionContext().setAccountTransaction(
                    null);
        } else {
            /*
             * 2005-06-20 Reid
             */
            //            toSave.setCustomerId(this.getAccountTransactionContext()
            //                    .getFstxCustomer().getId());
        }

        toSave.setAmount(this.getAccountTransaction().getAmount());
        toSave.setComments(this.getAccountTransaction().getComments());
        toSave.setDate(this.getAccountTransaction().getDate());
        toSave.setEnteredBy(this.getAccountTransaction().getEnteredBy());
        toSave.setType(this.getAccountTransaction().getType());

        try {
        AccountTransactionServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner(),
                this.getAccountTransactionContext().getObjectTransactionType())
                .save(
                        toSave,
                        this.getAccountTransactionContext()
                                .getSystemOwnerKeyAware());
        } catch (AccountTransactionException e) {
            if (toSave.getType().equals(AccountTransactionTypeEnum.BALANCE_SET.getName())) {
                this.addFieldError("accountTransaction.type","Failed to save. Balance set transactions must be the only transaction on a given day.");
            } else {
                this.addFieldError("accountTransaction.type","A balance set transaction exists for that date.  You must save on a different date.");
            }
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * @return Returns the accountTransaction.
     */
    public AccountTransaction getAccountTransaction() {
        return accountTransaction;
    }

    /**
     * @param accountTransaction
     *            The accountTransaction to set.
     */
    public void setAccountTransaction(AccountTransaction accountTransaction) {
        this.accountTransaction = accountTransaction;
    }

    /**
     * @return Returns the cancelModify.
     */
    public String getCancelTxn() {
        return cancelTxn;
    }

    /**
     * @param cancelModify
     *            The cancelModify to set.
     */
    public void setCancelTxn(String cancelModify) {
        this.cancelTxn = cancelModify;
    }

    /**
     * @return Returns the deleteModify.
     */
    public String getDeleteTxn() {
        return deleteTxn;
    }

    /**
     * @param deleteModify
     *            The deleteModify to set.
     */
    public void setDeleteTxn(String deleteModify) {
        this.deleteTxn = deleteModify;
    }

    /**
     * @return Returns the modifyAccountTransactionContext.
     */
    public ModifyAccountTransactionContext getModifyAccountTransactionContext() {
        return modifyAccountTransactionContext;
    }

    /**
     * @param modifyAccountTransactionContext
     *            The modifyAccountTransactionContext to set.
     */
    public void setModifyAccountTransactionContext(
            ModifyAccountTransactionContext modifyAccountTransactionContext) {
        this.modifyAccountTransactionContext = modifyAccountTransactionContext;
    }

    //    /**
    //     * @return Returns the sessionContext.
    //     */
    //    public SessionContext getSessionContext() {
    //        return sessionContext;
    //    }
    //
    //    /**
    //     * @param sessionContext
    //     * The sessionContext to set.
    //     */
    //    public void setSessionContext(SessionContext sessionContext) {
    //        this.sessionContext = sessionContext;
    //    }

    /**
     * @return Returns the submitModify.
     */
    public String getSubmitTxn() {
        return submitTxn;
    }

    /**
     * @param submitModify
     *            The submitModify to set.
     */
    public void setSubmitTxn(String submitModify) {
        this.submitTxn = submitModify;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    public Collection getUsers() throws Exception {
        return SystemUserServiceDelegateFactory.factory.build().list(
                this.getSessionContext().getSystemOwner());
    }

    public Collection getTypes() throws Exception {
        return AccountTransactionTypeEnum.getAllTypes();
    }

    //    /**
    //     * @return Returns the accountTransactionContext.
    //     */
    //    public AccountTransactionContext getAccountTransactionContext() {
    //        return accountTransactionContext;
    //    }
    //
    //    /**
    //     * @param accountTransactionContext
    //     * The accountTransactionContext to set.
    //     */
    //    public void setAccountTransactionContext(
    //            AccountTransactionContext accountTransactionContext) {
    //        this.accountTransactionContext = accountTransactionContext;
    //    }

    /**
     * @return Returns the dateString.
     */
    public String getDateString() {
        String ret = null;

        if (this.getAccountTransaction() != null
                && this.getAccountTransaction().getDate() != null)
            ret = SimpleDate.factory.build(
                    this.getAccountTransaction().getDate()).getMmddyyyy();
        else
            ret = SimpleDate.factory.build().getMmddyyyy();

        return ret;
    }

    /**
     * @param dateString
     *            The dateString to set.
     */
    public void setDateString(String dateString) {
        this.getAccountTransaction().setDate(
                SimpleDate.factory.buildMidnight(dateString).getDate());
    }
}