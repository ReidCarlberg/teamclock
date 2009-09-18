/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import java.util.Collection;

import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.register.v2.xwork.RegistrationContext;
import com.fivesticks.time.register.v2.xwork.RegistrationContextTestFactory;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.payments.PaymentMethodCriteriaParameters;
import com.fivesticks.time.systemowner.payments.PaymentMethodServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class RegistrationProcessCommandTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        int originalSystemOwnerSize = SystemOwnerServiceDelegateFactory.factory
                .build().findAll().size();
        int originalUserSize = UserBDFactory.factory.build().getAll().size();

        RegistrationContext context = RegistrationContextTestFactory.build();

        RegistrationProcessCommand command = new RegistrationProcessCommand();

        /*
         * set the dependencies
         */
        command
                .setSystemOwnerServiceDelegate(SystemOwnerServiceDelegateFactory.factory
                        .build());
        command
                .setUserServiceDelegate(UserServiceDelegateFactory.factory
                        .buildEmpty());
        command
                .setPaymentMethodServiceDelegate(PaymentMethodServiceDelegateFactory.factory
                        .buildEmpty());
        command
                .setSystemSettingsServiceDelegate(SystemSettingsServiceDelegateFactory.factory
                        .buildEmpty());

        command.setCustomerServiceDelegate(CustomerServiceDelegateFactory.factory.buildEmpty());

        command.setFstxProjectBD(ProjectServiceDelegateFactory.factory.buildEmpty());

        command.setFstxTaskBD(TaskServiceDelegateFactory.factory.buildEmpty());

        /*
         * set the context
         */
        command.setRegistrationContext(context);
        command.execute();

        /*
         * we should have a new system owner the system owner should have a key
         * and an id.
         */
        assertEquals(originalSystemOwnerSize + 1,
                SystemOwnerServiceDelegateFactory.factory.build().findAll().size());
        assertNotNull(context.getSystemOwner().getKey());
        assertNotNull(context.getSystemOwner().getId());

        /*
         * we should have a new user the new user should belong to the new
         * system owner the new user type should be an Owner.
         */
        assertEquals(originalUserSize + 1, UserBDFactory.factory.build().getAll()
                .size());
        Collection newUsers = SystemUserServiceDelegateFactory.factory.build().list(
                context.getSystemOwner());
        assertEquals(1, newUsers.size());
        SystemUser user = (SystemUser) newUsers.toArray()[0];
        assertEquals(UserTypeEnum.OWNERADMIN.getName(), user.getUserType());

        /*
         * we should have a new payment method
         */
        assertEquals(1, PaymentMethodServiceDelegateFactory.factory.build(
                context.getSystemOwner()).search(
                new PaymentMethodCriteriaParameters()).size());

        /*
         * we should have some new system settings.
         */
        SystemSettingsServiceDelegate settings = SystemSettingsServiceDelegateFactory.factory.build(context.getSystemOwner());
      
        assertEquals(context.getSettings().getSystemTimeZone(),
                SystemSettingsServiceDelegateFactory.factory.build(
                        context.getSystemOwner()).getSettingAsString(
                        SettingTypeEnum.SETTING_TIMEZONE));

        assertEquals(context.getPlan().getCode(), settings.getSettingAsString(SettingTypeEnum.REGISTRATION_PLAN));
        /*
         * verify that we have some default data.
         */
        assertEquals(1, CustomerServiceDelegateFactory.factory.build(context.getSystemOwner())
                .getAll().size());
        assertEquals(1, ProjectServiceDelegateFactory.factory.build(context.getSystemOwner())
                .getAllActive().size());
        assertEquals(1, TaskServiceDelegateFactory.factory.build(context.getSystemOwner())
                .getAllTaskTypes().size());

    }
}
