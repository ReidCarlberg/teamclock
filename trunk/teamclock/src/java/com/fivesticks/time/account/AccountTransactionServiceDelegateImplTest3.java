/*
 * Created on Jun 1, 2006
 *
 */
package com.fivesticks.time.account;

import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/*
 * 2006-06-01 RSC exercises a problem with setting the account balance.
 * 
 */
public class AccountTransactionServiceDelegateImplTest3 extends
        AbstractTimeTestCase {

    public void testBasic() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(this.systemOwner,
                        CustomerAccountTransactionType.TIME_ACCOUNT);

        Double d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(0.0));

        SimpleDate today = SimpleDate.factory.build("06/01/2006");

        doTransaction(today, new Double(120.0),
                AccountTransactionTypeEnum.ADD_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(120.0));

        doTransaction(today, new Double(40.0),
                AccountTransactionTypeEnum.USE_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(80.0));

        try {
            doTransaction(today, new Double(0.0),
                    AccountTransactionTypeEnum.BALANCE_SET);
            assertTrue(false);
        } catch (Exception e) {
            // do nothing
        }
    }

    public void testBasicSucceeds() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(this.systemOwner,
                        CustomerAccountTransactionType.TIME_ACCOUNT);

        Double d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(0.0));

        SimpleDate today = SimpleDate.factory.build("05/15/2006");

        doTransaction(today, new Double(120.0),
                AccountTransactionTypeEnum.ADD_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(120.0));

        doTransaction(today, new Double(40.0),
                AccountTransactionTypeEnum.USE_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(80.0));

        today.advanceDay();

        doTransaction(today, new Double(0.0),
                AccountTransactionTypeEnum.BALANCE_SET);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(0.0));
    }

    public void testBasicAddAndUseFail() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(this.systemOwner,
                        CustomerAccountTransactionType.TIME_ACCOUNT);

        Double d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(0.0));

        SimpleDate today = SimpleDate.factory.build("05/15/2006");

        doTransaction(today, new Double(120.0),
                AccountTransactionTypeEnum.ADD_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(120.0));

        doTransaction(today, new Double(40.0),
                AccountTransactionTypeEnum.USE_VALUE);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(80.0));

        today.advanceDay();

        doTransaction(today, new Double(0.0),
                AccountTransactionTypeEnum.BALANCE_SET);

        d = sd.getCurrentBalance(this.customer);

        assertEquals(d, new Double(0.0));

        try {
            doTransaction(today, new Double(120.0),

            AccountTransactionTypeEnum.ADD_VALUE);
            assertTrue(false);
        } catch (Exception e) {
            // hooray it failed.
        }

        try {
            doTransaction(today, new Double(40.0),
                    AccountTransactionTypeEnum.USE_VALUE);
            assertTrue(false);
        } catch (Exception e) {
            // hooray it failed
        }
    }

    private void doTransaction(SimpleDate d1, Double amount,
            AccountTransactionTypeEnum type) throws Exception {

        AccountTransaction toSave = new AccountTransaction();

        toSave.setAmount(amount);
        toSave.setComments("comments");
        toSave.setDate(d1.getDate());
        toSave.setEnteredBy("reid");
        toSave.setType(type.getName());

        AccountTransactionServiceDelegateFactory.factory.build(
                this.systemOwner, CustomerAccountTransactionType.TIME_ACCOUNT)
                .save(toSave, this.customer);
    }
}
