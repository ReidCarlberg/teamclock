package com.fivesticks.time.useradmin;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.TransactionManagerAware;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;
import com.fivesticks.time.externaluser.ExternalUser;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegate;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.events.UseradminEventPublisher;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserDAOFactory;

/**
 * @author Reid
 *  
 */
public class UseradminDeleteCommand extends DeleteCommand implements
        TransactionManagerAware {

    private PlatformTransactionManager transactionManager;

    private User target;

    public UseradminDeleteCommand() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ycs.ems.common.delete.DeleteCommand#handleDelete()
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {

        try {
            handleDeleteInTransaction(sessionContext.getSystemOwner(), this
                    .getTarget());
        } catch (Exception e) {
            throw new DeleteCommandFailedException(e);
        }

        new UseradminEventPublisher().publishUserDeletedEvent(sessionContext
                .getSystemOwner(), sessionContext.getUser().getUser(), this
                .getTarget());

    }

    private void handleDeleteInTransaction(final SystemOwner systemOwner,
            final User userTarget) throws Exception {
        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    try {
                        /*
                         * needs to delete the external user associations
                         */
                        ExternalUserServiceDelegate exsd = ExternalUserServiceDelegateFactory.factory
                                .build(systemOwner);

                        Collection extUsers = exsd.getCustomers(userTarget
                                .getUsername());

                        for (Iterator iter = extUsers.iterator(); iter
                                .hasNext();) {
                            ExternalUser element = (ExternalUser) iter.next();
                            exsd.delete(element);
                        }

                        /*
                         * needs to delete the system owner associations
                         */
                        SystemUserServiceDelegate susd = SystemUserServiceDelegateFactory.factory
                                .build();
                        susd.disassociate(systemOwner, userTarget);

                        /*
                         * Do we delete the user? If there are other system
                         * owner associations then no.
                         */

                        Collection otherAssoc = susd.list(userTarget
                                .getUsername());

                        if (otherAssoc.size() == 0) {
                            UserDAOFactory.factory.build().delete(userTarget);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Failed. " + e);
                    }

                }

            });

        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ycs.ems.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        if (target == null)
            throw new DeleteCommandFailedException();

        return target.getUsername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ycs.ems.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {
        return "Deleting a user will remove the user but not the associated consultant (if any).";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return "delete-success-user-list";
    }

    /**
     * @return Returns the target.
     */
    public User getTarget() {
        return target;
    }

    /**
     * @return Returns the transactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * @param transactionManager
     *            The transactionManager to set.
     */
    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(User target) {
        this.target = target;
    }
}