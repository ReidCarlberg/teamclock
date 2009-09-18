/*
 * Created on Aug 16, 2005
 *
 */
package com.fivesticks.time.customer.fwuc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectFilterVO;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegate;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateException;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

public class SendCustomerBalanceUpdatesCommand {

    private static final String BALANCE_UPDATE_MESSAGE_NAME = "fwucBalanceNotification";

    public void execute() throws SendCustomerBalanceUpdatesCommandException {

        SystemMessageBean message = SpringBeanBroker
                .getCommonMessage(BALANCE_UPDATE_MESSAGE_NAME);

        String fwucOwnerKey = "CXZASTPKGU";

        Set notifiedCustomerIds = new HashSet();

        SystemOwner fivesticks;
        try {
            fivesticks = SystemOwnerServiceDelegateFactory.factory.build().get(
                    fwucOwnerKey);
        } catch (SystemOwnerServiceDelegateException e1) {
            throw new SendCustomerBalanceUpdatesCommandException(e1);
        }

        CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory.build(fivesticks);

        AccountTransactionServiceDelegate accountSD = CustomerAccountTransactionServiceDelegate.factory
                .buildTimeAccount(fivesticks);

        ContactServiceDelegate contactSD = ContactServiceDelegateFactory.factory
                .build(fivesticks);

        QueuedMessageServiceDelegate messageSD = QueuedMessageServiceDelegateFactory.factory
                .build(fwucOwnerKey);

        ProjectFilterVO projectFilter = new ProjectFilterVO();
        projectFilter.setActive(true);
        projectFilter.setPostable(new Boolean(true));

        Collection projects;
        try {
            projects = ProjectServiceDelegateFactory.factory.build(fivesticks).searchByFilter(
                    projectFilter);
        } catch (ProjectBDException e1) {
            throw new SendCustomerBalanceUpdatesCommandException(e1);
        }

        for (Iterator iter = projects.iterator(); iter.hasNext();) {
            Project currentProject = (Project) iter.next();

            Customer cust;
            try {
                cust = customerBD.getFstxCustomer(currentProject.getCustId());
            } catch (CustomerServiceDelegateException e1) {
                throw new SendCustomerBalanceUpdatesCommandException(e1);
            }

            if (!notifiedCustomerIds.contains(cust.getId())) {
                notifiedCustomerIds.add(cust.getId());

                Double currentBalance;
                try {
                    currentBalance = accountSD.getCurrentBalance(cust);
                } catch (AccountTransactionException e1) {
                    throw new SendCustomerBalanceUpdatesCommandException(e1);

                }

                Collection contacts;
                try {
                    contacts = contactSD.getByCustomer(cust);
                } catch (ContactServiceDelegateException e1) {
                    throw new SendCustomerBalanceUpdatesCommandException(e1);

                }

                for (Iterator iterator = contacts.iterator(); iterator
                        .hasNext();) {
                    Contact currentContact = (Contact) iterator.next();

                    if (currentContact.getPrimaryEmail() != null) {

                        /*
                         * merge and send the message
                         */

                        QueuedMessage qm = new QueuedMessageBuilder()
                                .build(message);
                        qm.setToName(currentContact.getNameFirst() + " "
                                + currentContact.getNameLast());

                        /*
                         * testing only
                         */
                        qm.setToAddress("rsc1@fivesticks.com");

                        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",
                                currentBalance.toString()));

                        qm.setMessage(qm.getMessage().replaceAll("\\{1\\}",
                                currentContact.getPrimaryEmail()));

                        try {
                            messageSD.addSystemMessage(qm);
                        } catch (QueuedMessageServiceDelegateException e) {
                            throw new SendCustomerBalanceUpdatesCommandException(
                                    e);

                        }

                    }
                }

            }
        }

    }

}
