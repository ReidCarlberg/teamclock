/*
 * Created on Nov 25, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import com.fivesticks.time.messages.MessageTestFactory;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class ConfirmSendActionTest extends AbstractTimeTestCase {



    public void testBasic() throws Exception {

        SystemSettingsServiceDelegateFactory.factory.build(systemOwner)
        .updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_NAME,
                "from nameo");
        
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner)
        .updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_ADDRESS,
                "from address");
        
        SendMessageContext sendMessageContext = new SendMessageContext();

        sendMessageContext.setMessage(MessageTestFactory
                .getPersisted(this.systemOwner));
        
        ContactAndCustomerVO contactAndCustomerVO = new ContactAndCustomerVO(this.contact,this.customer);
        sendMessageContext.getRecipients().add(contactAndCustomerVO);

        ConfirmSendAction action = new ConfirmSendAction();

        action.setSessionContext(this.sessionContext);
        action.setSendMessageContext(sendMessageContext);

        String s = action.execute();
        assertEquals(ActionSupport.INPUT, s);

        action.setSubmitConfirm("submit");
        String s2 = action.execute();

        assertEquals(ActionSupport.SUCCESS, s2);
    }

    public void testNoRecipients() throws Exception {

        SendMessageContext sendMessageContext = new SendMessageContext();

        sendMessageContext.setMessage(MessageTestFactory
                .getPersisted(this.systemOwner));
        //sendMessageContext.getRecipients().add(this.customer);

        ConfirmSendAction action = new ConfirmSendAction();

        action.setSessionContext(this.sessionContext);
        action.setSendMessageContext(sendMessageContext);
        action.setSubmitConfirm("confirm");
        
        String s = action.execute();
        assertEquals(ActionSupport.INPUT, s);

    }
}
