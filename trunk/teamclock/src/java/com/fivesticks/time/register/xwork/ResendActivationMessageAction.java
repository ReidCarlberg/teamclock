/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.register.xwork;

import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.register.events.RegisterEventPublisher;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ResendActivationMessageAction extends ActionSupport {

    private String email;

    public String execute() throws Exception {

        if (this.getEmail() == null)
            return INPUT;

        SystemOwner owner = null;

        try {
            owner = SystemOwnerServiceDelegateFactory.factory.build().getByEmail(
                    this.getEmail());
        } catch (RuntimeException e) {
            // do nothing.
        }

        if (owner == null) {
            this
                    .addActionError("Could not find a system owner with that email address.");
            return INPUT;
        }

        if (owner.isActivated()) {
            this
                    .addActionError("This owner is already activated.  No additional activation message can be sent.");
            return INPUT;
        }

       
            

        UserBD bd = UserBDFactory.factory.build();

        User user = bd.getUserByEmail(this.getEmail());

        String pw = new RandomStringBuilder().buildRandomString(10);

        bd.changePassword(user, pw);

        new RegisterEventPublisher().publishRegisterEvent(owner, user, pw);

        this.addActionError("Replacement activation message sent.  Please check your email.");
        
        return INPUT;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}